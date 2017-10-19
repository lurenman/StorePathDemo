package com.example.administrator.storepathdemo;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/10/17.
 */

public class CacheDirActivity extends BaseActivity {
    private static final String TAG = "CacheDirActivity";
    private TextView tv_content;
    private TextView tv_put;
    private TextView tv_get;
    private File cacheDir;
    private String writestr;

    @Override
    protected void initVariables() {
        cacheDir = new File(this.getCacheDir(), "ceshiCacheDir");
        Log.e(TAG,"----"+cacheDir.getAbsolutePath());
        writestr = "dsakldsadasdsadlsadsl;cmxdcsas";


    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_cachedir);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_put = (TextView) findViewById(R.id.tv_put);
        tv_get = (TextView) findViewById(R.id.tv_get);

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        tv_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = new FileOutputStream(cacheDir);
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
                File myfile = new File(getApplicationContext().getCacheDir(), "ceshiCacheDir");
                try {
                    FileInputStream fis = new FileInputStream(myfile);
                    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                    char[] input = new char[fis.available()];
                    isr.read(input);
                    isr.close();
                    fis.close();
                    String readed = new String(input);
                    Log.e(TAG,"-----------"+readed);
                    if (!TextUtils.isEmpty(readed)) {
                        tv_content.setText(readed);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.e(TAG,"-----------"+e.toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e(TAG,"-----------"+e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG,"-----------"+e.toString());
                }

            }
        });
    }

    @Override
    protected void loadData() {

    }
}
