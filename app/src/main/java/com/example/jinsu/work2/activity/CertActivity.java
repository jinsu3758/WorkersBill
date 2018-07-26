package com.example.jinsu.work2.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.databinding.ActivityCertBinding;
import com.example.jinsu.work2.manager.TaskManager;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

import java.net.URLEncoder;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CertActivity extends ParentActivity implements CallonClick{

    private ActivityCertBinding binding;
    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        //인증번호 요청

    }


    private void initActivity()
    {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cert);
        vIewModelFactory = new VIewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,vIewModelFactory).get(MainViewModel.class);
        binding.setCert(mainViewModel);
        email = BaseApplication.join.email;
        mainViewModel.onCert(email);
        //비밀번호 EditText의 Focus설정
        /*binding.editOne.setNextFocusDownId(R.id.edit_two);
        binding.editTwo.setNextFocusDownId(R.id.edit_three);
        binding.editThree.setNextFocusDownId(R.id.edit_four);*/

    }

    //다음 단계 버튼
    @Override
    public void onBtnClick(View view) {
        int code = mainViewModel.getCertCode();
        switch (view.getId())
        {
            case R.id.cert_btn_again:
            {
                //재전송
                //TODO email 넣어주셔야할듯
                mainViewModel.onCert(email);
                break;
            }
            case R.id.cert_btn_next:
            {
                //검증
                //TODO email,입력코드 넣어주셔야할듯
                TaskManager.api_verify_email(URLEncoder.encode(email), code, new Callback<HashMap<String, Object>>() {

                    @Override
                    public void success(HashMap<String, Object> stringObjectHashMap, Response response) {
                        boolean result = (boolean) stringObjectHashMap.get("result");
                        if(result) {
                            startActivity(new Intent(CertActivity.this,InputInfoActivity.class));
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

                /**
                 * 테스트용도 위에 입력값 제대로 넣으시면 성공했을때에만 넘어가도록 지워주세요
                 */
                startActivity(new Intent(CertActivity.this,InputInfoActivity.class));
                break;
            }
        }
        //startActivity(new Intent(this,SignActivity.class));

    }

    @Override
    public void textChanged(int flag) {
            switch (flag)
            {
                case 1:
                {
                    binding.editTwo.requestFocus();
                    break;
                }
                case 2:
                {
                    binding.editThree.requestFocus();
                    break;
                }
                case 3:
                {
                    binding.editFour.requestFocus();
                    break;

                }
                case 4:
                {
                    /*binding.editOne.setFocusable(false);
                    binding.editTwo.setFocusable(false);
                    binding.editThree.setFocusable(false);
                    binding.editFour.setFocusable(false);*/
                    break;
                }
            }
    }


}
