package com.example.administrator.storepathdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.storepathdemo.utils.ImageUtils;

/**
 * Created by Administrator on 2017/10/13.
 */

public class ExternalFilesDirActivity extends BaseActivity {
    private String mSdFileAbsolutePath = SMApp.getContext().getExternalFilesDir(null).getAbsolutePath();
    private ImageView iv_imageView;
    private TextView tv_click;
    private Bitmap  mBitmap=null;

    @Override
    protected void initVariables() {

        mBitmap = ImageUtils.intToBitmap(getApplicationContext(), R.mipmap.banner1);

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_externalfiles);
        iv_imageView = (ImageView) findViewById(R.id.iv_imageView);
        tv_click = (TextView) findViewById(R.id.tv_click);
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
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
                ImageUtils.savePhotoToSDCard(mBitmap, mSdFileAbsolutePath + "/Picture", "ceshiphoto");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = BitmapFactory.decodeFile(mSdFileAbsolutePath + "/Picture" + "/" + "ceshiphoto" + ".png");
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
}
