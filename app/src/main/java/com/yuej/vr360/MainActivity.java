package com.yuej.vr360;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private VrPanoramaView mVRPanoramaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVRPanoramaView = (VrPanoramaView) findViewById(R.id.vrPanoramaView);
//        mVRPanoramaView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);//全屏模式，弹出一个全屏的Dialog
        mVRPanoramaView.setInfoButtonEnabled(false);//隐藏信息按钮
        mVRPanoramaView.setStereoModeButtonEnabled(false);//隐藏cardboard按钮
        mVRPanoramaView.setFullscreenButtonEnabled(false);//隐藏全屏按钮
        //1.网络图片

//        Bitmap bitmap = Glide.with(this)
//                .asBitmap()
//                .load("")
//                .submit(360, 480)
//                .get();
//
//        VrPanoramaView.Options options = new VrPanoramaView.Options();
//        //设置图片类型为单通道图片
//        options.inputType = VrPanoramaView.Options.TYPE_MONO;
//        mVRPanoramaView.loadImageFromBitmap(bitmap, options);


//2.本地图片
        loadPhotoSphere();
    }
    private void loadPhotoSphere() {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getAssets();
        try {
            inputStream = assetManager.open("image.jpg");
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            mVRPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        mVRPanoramaView.pauseRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVRPanoramaView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        mVRPanoramaView.shutdown();
        super.onDestroy();
    }
}