package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.common.Constants;
import com.example.jinsu.work2.databinding.ActivityInputinfoBinding;
import com.example.jinsu.work2.dialog.ImageSelectDialog;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class InputInfoActivity extends AppCompatActivity implements CallonClick {

    private ActivityInputinfoBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();

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
                mainViewModel.setUser();
                break;
            }
            case R.id.inputInfo_select_image_btn:
            {
                ImageSelectDialog dialog = new ImageSelectDialog(this, new ImageSelectDialog.onBtnCallback() {
                    @Override
                    public void takePicture()
                    {
                        takePicture1();
                    }

                    @Override
                    public void photoAlbum() {
                        photoAlbum1();
                    }
                });
                dialog.show();
                break;
            }
            case R.id.inputInfo_address_btn:
            {
                Intent i = new Intent(InputInfoActivity.this, AddressActivity.class);
                startActivityForResult(i, Constants.SEARCH_ADDRESS_ACTIVITY);
                break;
            }
        }
    }

    private void takePicture1()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //File file = new File(Environment.getExternalStorageDirectory(), SAMPLEIMG);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

        startActivityForResult(intent, Constants.REQUEST_PICTURE);
    }

    private void photoAlbum1()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, Constants.REQUEST_PHOTO_ALBUM);

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == Constants.REQUEST_PICTURE)
            {
                mainViewModel.setImage(binding.inputInfoImProfile, data.getData());
            }
            else if (requestCode == Constants.REQUEST_PHOTO_ALBUM)
            {
                mainViewModel.setImage(binding.inputInfoImProfile, data.getData());
            }
            else if (requestCode == Constants.SEARCH_ADDRESS_ACTIVITY)
            {
                mainViewModel.setAddr(data.getExtras().getString("data"));
            }
        }
    }


    @Override
    public void textChanged(int flag) {
        startActivity(new Intent(this,SignActivity.class));
    }

}
