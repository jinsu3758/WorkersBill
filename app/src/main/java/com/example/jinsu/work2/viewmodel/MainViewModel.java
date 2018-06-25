package com.example.jinsu.work2.viewmodel;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.activity.CertActivity;
import com.example.jinsu.work2.activity.SelectActivity;
import com.example.jinsu.work2.model.CalcContent;
import com.example.jinsu.work2.model.EmployerPlace;
import com.example.jinsu.work2.model.User;
import com.example.jinsu.work2.network.user.UserSource;
import com.example.jinsu.work2.repositories.EmployerRepository;
import com.example.jinsu.work2.repositories.MainRepository;
import com.example.jinsu.work2.thread.SignThread;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.util.Dlog;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

import io.realm.Realm;

public class MainViewModel extends ViewModel {
    public final ObservableField<String> sign_txt = new ObservableField<>();
//    JoinActivity
    public final ObservableField<String> join_edit_email = new ObservableField<>();
//    CertActivity
    public final ObservableField<String> one = new ObservableField<>();
    public final ObservableField<String> two = new ObservableField<>();
    public final ObservableField<String> three = new ObservableField<>();
    public final ObservableField<String> four = new ObservableField<>();
//    LoginActivity
    public final ObservableField<String> login_edit_email = new ObservableField<>();
//    InputInfoActivity
    public final ObservableField<String> inputinfo_edit_name = new ObservableField<>();
    public final ObservableField<String> inputinfo_edit_registerNum = new ObservableField<>();
    public final ObservableField<String> inputinfo_edit_postcode = new ObservableField<>();
    public final ObservableField<String> inputinfo_address = new ObservableField<>();

//    EmployerPlaceActivity

//    EmployerCreatePlaceActivity
    public final ObservableField<String> place_name = new ObservableField<>();
    public final ObservableField<String> place_num = new ObservableField<>();
    public final ObservableField<String> place_owner = new ObservableField<>();
    public final ObservableField<String> place_phone = new ObservableField<>();
    public final ObservableField<String> place_addr_num = new ObservableField<>();
    public final ObservableField<String> place_addr = new ObservableField<>();
    public final ObservableField<String> place_realaddr_num = new ObservableField<>();
    public final ObservableField<String> place_realaddr = new ObservableField<>();
    public final ObservableField<String> place_wifi = new ObservableField<>();
    private EmployerPlace employerPlace;

//    EmployerCalcActivity
    public final ObservableField<String> calc_day = new ObservableField<>();
    public final ObservableField<String> calc_money = new ObservableField<>();
    public final ObservableField<String> calc_total_time = new ObservableField<>();
    public final ObservableField<String> calc_total_money = new ObservableField<>();
    public final ObservableField<String> calc_urgency = new ObservableField<>();
    public final ObservableField<String> calc_wage = new ObservableField<>();
    public final ObservableField<String> calc_week_wage= new ObservableField<>();
    public final ObservableField<String> calc_plus_wage = new ObservableField<>();
    public final ObservableField<String> calc_night_wage = new ObservableField<>();

//    EmployerCalcListActivity
    public final ObservableField<String> calc_num = new ObservableField<>();

//    EmployerContractWiteActivity
    public final ObservableField<String> contract_write_owner = new ObservableField<>();
    public final ObservableField<String> contract_write_worker = new ObservableField<>();
    public final ObservableField<String> contract_write_place = new ObservableField<>();
    public final ObservableField<String> contract_write_content = new ObservableField<>();
    public final ObservableField<String> contract_write_term1 = new ObservableField<>();
    public final ObservableField<String> contract_write_term2 = new ObservableField<>();
    public final ObservableField<String> contract_write_day = new ObservableField<>();
    public final ObservableField<String> contract_write_holiday = new ObservableField<>();
    public final ObservableField<String> contract_write_wage = new ObservableField<>();
    public final ObservableField<String> contract_write_bonus = new ObservableField<>();
    public final ObservableField<String> contract_write_excess = new ObservableField<>();

    private String id;
    private User user;
    private String email;

    private CallonClick callback ;
    private MainRepository mainRepository;
    private android.os.Handler handler;
    private EmployerRepository employerRepository;
    private Realm realm;


    // private UserDao userDao;



    @Inject
    public MainViewModel(MainRepository mainRepository, CallonClick click)
    {
        this.callback = click;
        this.mainRepository = mainRepository;
        this.employerRepository = EmployerRepository.getInstance();
    }

    public MainViewModel(CallonClick onClick)
    {
        mainRepository = new MainRepository();
        handler = new Handler() ;
        this.callback = onClick;
        realm = Realm.getDefaultInstance();
        initListener(onClick.getClass().getSimpleName());
    }

    public void initListener(String view)
    {
        switch (view)
        {
            case  "SignActivity":
            {
                join_edit_email.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {
                    }
                });
                break;
            }

            case "LoginActivity":
            {
                login_edit_email.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                break;
            }
            case "InputInfoActivity":
            {
                inputinfo_edit_name.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                inputinfo_edit_registerNum.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                inputinfo_edit_postcode.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                inputinfo_address.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                break;
            }
            case "EmployerCreatePlaceActivity":
            {
                place_name.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                place_owner.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                place_addr.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                place_addr_num.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                place_phone.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                place_realaddr.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                place_realaddr_num.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                place_wifi.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
            }

            case "EmployerCalcActivity":
            {
                calc_day.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                calc_money.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });

                break;
            }
            case "EmployerContractWriteActivity":
            {
                contract_write_owner.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_worker.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_place.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_content.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_term1.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_term2.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_day.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_holiday.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_wage.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_bonus.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                contract_write_excess.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });

                break;
            }
            default:
                break;
        }
    }

    /*public void initId(String id)
    {
        this.id = id;
        user = mainRepository.initUser();
        user = mainRepository.getUser(id);
    }*/
//    체크박스 함수
    public void onCheckBoxChecked(View view, boolean ischeckd)
    {
        switch (view.getId())
        {
            case R.id.employer_create_place_check:
            {
                if(ischeckd)
                {
                    Log.d("gogo","체크됨");
                }
                else
                {
                    Log.d("gogo","체크x");
                }
                break;
            }
            case R.id.employer_create_place_switch:
            {
                if(ischeckd)
                {
                    Log.d("gogo","sw체크됨");
                }
                else
                {
                    Log.d("gogo","sw체크x");
                }
                break;
            }
            case R.id.employer_calc_ch_hour:
            {
                if(ischeckd)
                {
                    Log.d("gogo","hour체크됨");
                }
                else
                {
                    Log.d("gogo","hour체크x");
                }
                break;
            }
            case R.id.employer_calc_ch_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","mon체크됨");
                }
                else
                {
                    Log.d("gogo","mon체크x");
                }
                break;
            }
            case R.id.employer_contract_write_ch_hour:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_month:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_pay_day:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_pay_month:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_pay_how1:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_pay_how2:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_insure1:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_insure2:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_insure3:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
            case R.id.employer_contract_write_ch_insure4:
            {
                if(ischeckd)
                {
                }
                else
                {
                }
                break;
            }
        }


    }

    public void getUsers(Context context)
    {
        employerRepository.getUsers(context, new UserSource.LoadDataCallback() {
            @Override
            public void onUserLoad(ArrayList<User> list) {
                if(list != null)
                {

                }
            }
        });
    }

    /*public User getUser() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                user = mainRepository.getUser();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        sign_txt.set(user.getLogin());
                    }
                });
            }

        });
        thread.start();

            return user;
    }*/

    /**
     * LoginActivity
     */

    /**
     * Login 시도 시 호출
     */
    public void onLogin()
    {

    }


    /**
     * 버튼 클릭 처리 함수
     *
     * @param view Button
     */
    public void onBtnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.employer_create_place_btn_create:
            {
                employerPlace = new EmployerPlace(place_name.get(),place_addr.get(),place_phone.get(),place_owner.get());
                EmployerRepository.getInstance().addEmployerPlace(employerPlace);
                callback.onBtnClick(view);
                break;
            }
            case R.id.employer_create_place_btn_wifi:
            {

                callback.onBtnClick(view);
                break;
            }
            default:
            {
                callback.onBtnClick(view);
                break;
            }
        }


        Dlog.d(id);
        /*if(id.contains("next"))
        {
            callback.onBtnClick(v);
        }*/
    }

    public void oneChanged(CharSequence s, int start, int before, int count)
    {
        if(s.length() != 0)
        callback.textChanged(1);
    }

    public void twoChanged(CharSequence s, int start, int before, int count)
    {
        if(s.length() != 0)
        callback.textChanged(2);
    }

    public void threeChanged(CharSequence s, int start, int before, int count)
    {
        callback.textChanged(3);
    }

    public void fourChanged(CharSequence s, int start, int before, int count)
    {
        if(s.length() != 0)
        callback.textChanged(4);
    }

    /**
     * SignActivity
     * 서명 이미지를 저장하는 함수
     *
     * @param context SignActivity
     * @param bitmap Sign Bitmap
     */
    public void savdSign(Context context, Bitmap bitmap)
    {
        SignThread signThread = new SignThread(bitmap,context);
        signThread.start();
        try {
            Log.d("SignActivity!","스레드 종료");
            signThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.startActivity(new Intent(context, SelectActivity.class));

    }


    /**
     *
     * JoinActivity
     * 회원가입 activity
     *
     */


    /**
     * 회원가입 시작 버튼 클릭 처리 함수
     *
     * @param context JoinActivity
     * @return 가입여부
     */
    public void onJoinUser(Context context)
    {
        email = join_edit_email.get();
        if(!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+$"))
        {
           //
        }
//        employerRepository.postUser(email);
        //다이얼로그 생성
        AlertDialog.Builder alert= new AlertDialog.Builder(context);
        alert.setTitle("인증코드 발송").setMessage("'" + email + "'로 인증코드가 전송되었습니다.\n이메일 확인 후에 작성해주세요" )
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(context,CertActivity.class));

            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return ;
            }
        });
        alert.create();
        alert.show();
        //return true;
    }



    /**
     * InputInfoActivity
     * 기본정보 입력 activity
     */

    public void onCancel(View v)
    {

    }

    /**
     * EmployerCreatePlaceActivity
     * 사업장 생성 activity
     */

//    5인 이상 체크 함수
    public void onPlaceCheckd(RadioGroup radioGroup, int id)
    {
        if(id == R.id.employer_create_place_radio_one)
        {
            //5인 미만
        }
        else
        {
            //5인 이상
        }
    }



    /**
     *
     * EmployerCalcActivity
     * 인건비 계산 activity
     *
     */
    //인건비 계산한 데이터 저장
    public void onSaveCalc(String name)
    {
        Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        //데이터 삭제
        /*realm.beginTransaction();
        RealmResults<CalcContent> userList = realm.where(CalcContent.class).findAll();
        userList.deleteAllFromRealm();
        realm.commitTransaction();*/



        realm.beginTransaction();
        CalcContent calc = realm.createObject(CalcContent.class);
        calc.setName(name);
        calc.setTotal_wage("1000");
        calc.setDate(month + "월 " + date + "일 " + hour + ":" + min);
        realm.commitTransaction();

    }


    /**
     *
     * EmployerCalcListActivity
     * 인건비 계산 목록 activity
     *
     */
    public void setNum(int num)
    {
        calc_num.set(num+ "");
    }

}
