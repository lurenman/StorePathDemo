package com.example.administrator.storepathdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//参考文件http://www.cnblogs.com/zxxiaoxia/p/6857466.html
public class MainActivity extends AppCompatActivity {

    private TextView tv_ExternalFilesDir;
    private TextView tv_cacheDir;
    private TextView tv_filesDir;
    private TextView tv_externalCacheDir;
    private TextView tv_externalStorageDirectory;
    private TextView tv_externalStoragePublicDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();


    }

    private void initViews() {
        tv_cacheDir = (TextView) findViewById(R.id.tv_cacheDir);
        tv_filesDir = (TextView) findViewById(R.id.tv_filesDir);
        tv_externalCacheDir = (TextView) findViewById(R.id.tv_externalCacheDir);
        tv_externalStorageDirectory = (TextView) findViewById(R.id.tv_externalStorageDirectory);
        tv_externalStoragePublicDirectory = (TextView) findViewById(R.id.tv_externalStoragePublicDirectory);
        tv_ExternalFilesDir = (TextView) findViewById(R.id.tv_ExternalFilesDir);
    }


    private void initEvents() {
        tv_cacheDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CacheDirActivity.class);
                startActivity(intent);
            }
        });
        tv_filesDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FilesDirActivity.class);
                startActivity(intent);
            }
        });
        tv_externalCacheDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ExternalCacheDirActivity.class);
                startActivity(intent);
            }
        });
        tv_externalStorageDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ExternalStorageDirectoryActivity.class);
                startActivity(intent);
            }
        });
        tv_externalStoragePublicDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ExternalStoragePublicDirectoryActivity.class);
                startActivity(intent);
            }
        });

        tv_ExternalFilesDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExternalFilesDirActivity.class);
                startActivity(intent);
            }
        });

    }

}
