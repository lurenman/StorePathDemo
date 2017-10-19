package com.example.administrator.storepathdemo;

import android.graphics.Bitmap;
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
 * Created by Administrator on 2017/10/18.
 * http://www.cnblogs.com/LiHuiGe8/p/5604725.html
 */

public class ExternalCacheDirActivity extends BaseActivity {
    private static final String TAG = "ExternalCacheDirActivit";
    private TextView tv_content;
    private TextView tv_put;
    private TextView tv_get;
    private File externalCacheDir;
    private String writestr;
    private Bitmap mBitmap = null;

    @Override
    protected void initVariables() {
        externalCacheDir = new File(this.getExternalCacheDir(), "ceshiexternalCacheDir.txt");
        writestr = "ExternalCacheDirExternalCacheDirExternalCacheDirExternalCacheDir";
        Log.e(TAG, externalCacheDir.getAbsolutePath());
        // mBitmap = ImageUtils.intToBitmap(getApplicationContext(), R.mipmap.banner1);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_externalcachedir);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_put = (TextView) findViewById(R.id.tv_put);
        tv_get = (TextView) findViewById(R.id.tv_get);
        //ImageUtils.savePhotoToSDCard(mBitmap,externalCacheDir.getAbsolutePath() + "/HAHA", "ceshiphoto");
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        tv_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//
//                    //FileOutputStream fout = openFileOutput(externalCacheDir.getName(), MODE_PRIVATE);
//                    FileOutputStream fout = new FileOutputStream(externalCacheDir);
//                    byte[] bytes = writestr.getBytes();
//                    fout.write(bytes);
//                    fout.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                try {
                    FileOutputStream fos = new FileOutputStream(externalCacheDir);
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
                //                读写内部存储的文件数据
//                程序一旦在虚拟机或真机运行后，程序就会在固定地方创建一个文件夹。
//                应用程序的数据文件默认位置保存在：
//                    java.io.FileNotFoundException: /data/user/0/com.example.administrator.storepathdemo/files/ceshiexternalCacheDir.txt:
//                    open failed: ENOENT (No such file or directory)
//                try{
//                    FileInputStream fis=openFileInput(externalCacheDir.getName());
//                    InputStreamReader is=new InputStreamReader(fis,"UTF-8");
//                    //fis.available()文件可用长度
//                    char input[]=new char[fis.available()];
//                    is.read(input);
//                    is.close();
//                    fis.close();
//                    String readed=new String(input);
//                    if (!TextUtils.isEmpty(readed))
//                        tv_content.setText(readed);
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                    Log.e(TAG,"----------------"+e.toString());
//                }
                File myfile = new File(getApplicationContext().getExternalCacheDir(), "ceshiexternalCacheDir.txt");
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
