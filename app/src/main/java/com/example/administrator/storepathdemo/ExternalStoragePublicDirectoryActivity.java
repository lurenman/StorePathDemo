package com.example.administrator.storepathdemo;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.storepathdemo.utils.ImageUtils;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/10/18.
 *类型如下： DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES, DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS,
 * DIRECTORY_PICTURES, DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS, DIRECTORY_DCIM, or DIRECTORY_DOCUMENTS
 */

public class ExternalStoragePublicDirectoryActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = "ExternalStoragePublicDi";
    File externalStoragePublicDirectory;
    private ImageView iv_imageView;
    private TextView tv_click;
    private Bitmap mBitmap=null;
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_externalstoragepublicdirectory);
        iv_imageView = (ImageView) findViewById(R.id.iv_imageView);
        tv_click = (TextView) findViewById(R.id.tv_click);
        initPermission();


    }
    private void initPermission() {
        if (!EasyPermissions.hasPermissions(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(this,"需要读写权限", 200,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            initSomeing();
        }
    }
    private void initSomeing() {
        externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        mBitmap = ImageUtils.intToBitmap(getApplicationContext(), R.mipmap.banner1);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSDPhoto();
            }
        });
    }

    private void getSDPhoto()
    {
        if (null==mBitmap)
        {
            mBitmap = ImageUtils.intToBitmap(getApplicationContext(), R.mipmap.banner1);
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ImageUtils.savePhotoToSDCard(mBitmap,externalStoragePublicDirectory.getAbsolutePath(), "ceshiphoto");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = BitmapFactory.decodeFile(externalStoragePublicDirectory.getAbsolutePath() + "/" + "ceshiphoto" + ".png");
                            if (null!=bitmap)
                            iv_imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }).start();
        }

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
            if (perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                initSomeing();
            }

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == 200)
            if (perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                Toast.makeText(getApplicationContext(),"没有读写权限无法使用存储功能",Toast.LENGTH_SHORT).show();
    }
}
