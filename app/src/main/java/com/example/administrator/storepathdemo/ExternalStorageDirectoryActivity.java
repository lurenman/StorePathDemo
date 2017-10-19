package com.example.administrator.storepathdemo;

import android.Manifest;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/10/18.
 */

public class ExternalStorageDirectoryActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = "ExternalStorageDirector";
    private TextView tv_content;
    private TextView tv_put;
    private TextView tv_get;
    private File externalStorageDirectory;
    private String writestr;

    @Override
    protected void initVariables() {
        externalStorageDirectory = new File(Environment.getExternalStorageDirectory(), "HaHaStorageDirector.txt");
        writestr = "TAG_ExternalStorageDirectorExternalStorageDirectorExternalStorageDirectorExternalStorageDirector";
        Log.e(TAG, externalStorageDirectory.getAbsolutePath());
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_externalstoragedirectory);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_put = (TextView) findViewById(R.id.tv_put);
        tv_get = (TextView) findViewById(R.id.tv_get);
        initPermission();
    }

    private void initPermission() {
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(this, "需要读写权限", 200, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            initSomeing();
        }
    }

    private void initSomeing() {

        tv_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = new FileOutputStream(externalStorageDirectory);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write(writestr);
                    osw.flush();
                    osw.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        tv_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File myfile = new File(Environment.getExternalStorageDirectory(), "HaHaStorageDirector.txt");
                try {
                    FileInputStream fis = new FileInputStream(myfile);
                    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                    char[] input = new char[fis.available()];
                    isr.read(input);
                    isr.close();
                    fis.close();
                    String readed = new String(input);
                    Log.e(TAG, "-----------" + readed);
                    if (!TextUtils.isEmpty(readed)) {
                        tv_content.setText(readed);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.e(TAG, "-----------" + e.toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e(TAG, "-----------" + e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "-----------" + e.toString());
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == 200)
            if (perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                initSomeing();
            }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == 200)
            if (perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                Toast.makeText(getApplicationContext(), "没有读写权限无法使用存储功能", Toast.LENGTH_SHORT).show();
    }
}

