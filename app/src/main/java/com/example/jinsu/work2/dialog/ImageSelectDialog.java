package com.example.jinsu.work2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;

import com.example.jinsu.work2.R;

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
        setContentView(R.layout.dialog_select_image);
        btn_camera = (Button)findViewById(R.id.dialog_select_image_btn_camera);
        btn_photoAlbum = (Button)findViewById(R.id.dialog_select_image_btn_album);
        btn_camera.setOnClickListener(v ->
        {
            callback.takePicture();
            dismiss();
        });
        btn_photoAlbum.setOnClickListener(v ->
        {
            callback.photoAlbum();
            dismiss();
        });

    }


}
