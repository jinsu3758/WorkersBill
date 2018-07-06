package com.example.jinsu.work2.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.jinsu.work2.dialog.ImageSelectDialog;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.databinding.ActivityInputinfoBinding;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.io.File;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class InputInfoActivity extends AppCompatActivity implements CallonClick {

    private ActivityInputinfoBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;

    ImageView iv;
    private EditText et_address;
    //사진으로 전송시 되돌려 받을 번호
    private Uri mImageCaptureUri;
    static int REQUEST_PICTURE = 1;
    //앨범으로 전송시 돌려받을 번호
    static int REQUEST_PHOTO_ALBUM = 2;
    //첫번째 이미지 아이콘 샘플 이다.
    public static final int SEARCH_ADDRESS_ACTIVITY = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        iv = (ImageView) findViewById(R.id.imgView);
    }

    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_inputinfo);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setInputInfo(mainViewModel);

    }


    @Override
    public void onBtnClick(View view) {
        switch (view.getId())
        {
            case R.id.inputInfo_next_btn:
            {
                startActivity(new Intent(this,SignActivity.class));
                finish();
            }
            case R.id.inputInfo_select_image_btn:
            {   Log.v("태그", "선택버튼 클릭");
                ImageSelectDialog dialog = new ImageSelectDialog(this, new ImageSelectDialog.onBtnCallback() {
                    @Override
                    public void takePicture()
                    {Log.v("태그", "콜백 받음 카메라");
                        takePicture1();
                    }

                    @Override
                    public void photoAlbum() {
                        Log.v("태그", "콜백 받음 앨범");
                        photoAlbum1();
                    }
                });
                dialog.show();
                break;

            }
            case R.id.inputInfo_address_btn:
            {   Log.v("태그", "주소 검색 클릭");
                Intent i = new Intent(InputInfoActivity.this, AddressWebviewActivity.class);
                Log.v("태그", "인텐트까지는 간다");
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);

                Log.v("태그", "여기까지9");
            }
        }
    }

    private void takePicture1()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //File file = new File(Environment.getExternalStorageDirectory(), SAMPLEIMG);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

        startActivityForResult(intent, REQUEST_PICTURE);
    }

    private void photoAlbum1()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, REQUEST_PHOTO_ALBUM);

        Log.v("태그", "여기까지2"+intent.getData());
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Log.v("태그", "onActivityResult() requestCode : " + requestCode);
        Log.v("태그", "onActivityResult()  RESULT_OK : " + RESULT_OK);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == REQUEST_PICTURE)
            {
                Glide.with(InputInfoActivity.this)
                        .load(data.getData())
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(iv);

                Log.v("태그", "Bundle extras = data.getExtras()");
                Log.v("태그", "카매라 끝");

            }
            else if (requestCode == REQUEST_PHOTO_ALBUM)
            {
                Log.v("태그", "앨범사진 위치 url : " + data.getData());

                Glide.with(InputInfoActivity.this)
                        .load(data.getData())
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(iv);

                Log.v("태그", "앨범끝");
            }
            else if (requestCode == SEARCH_ADDRESS_ACTIVITY)
            {
                Log.v("태그", "address data connect");
                Log.v("태그", "주소값 data = "+data);

                String dat = data.getExtras().getString("data"); Log.v("태그", "주소값 dat = "+dat);

                if (data != null)
                {
                    Log.v("태그", "주소값 dat null 아니다"+ dat);
                    String postcode = dat.substring(0,5);   Log.v("태그", "우편번호"+postcode);
                    String add = dat.substring(7,dat.length());   Log.v("태그", "나머지 주소"+add);
                    binding.etAdress.setText(postcode);
                    binding.etAddressRest.setText(add);
                }

            }
        }
    }


    @Override
    public void textChanged(int index) {

    }
}
