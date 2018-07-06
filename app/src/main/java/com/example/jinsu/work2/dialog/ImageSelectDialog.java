package com.example.jinsu.work2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.widget.Button;

import com.example.jinsu.work2.R;

import java.io.File;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class ImageSelectDialog extends Dialog {

    private Button btn_photoAlbum;
    private Button btn_camera;

    private onBtnCallback callback;


    public ImageSelectDialog(@NonNull Context context, onBtnCallback callback) {
        super(context);
        this.callback = callback;
    }

    public interface onBtnCallback
    {
        void takePicture();
        void photoAlbum();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_image_select);
        btn_camera = (Button)findViewById(R.id.dialog_image_select_btn_camera);
        btn_photoAlbum = (Button)findViewById(R.id.dialog_image_select_btn_photoAlbum);


        btn_camera.setOnClickListener(v ->
        {
            Log.v("태그", "카메라 다이어롤그");
            callback.takePicture();
            dismiss();
        });
        btn_photoAlbum.setOnClickListener(v ->
        {
            Log.v("태그", "앨범다이얼로그");
            callback.photoAlbum();
            dismiss();
        });

    }


}
