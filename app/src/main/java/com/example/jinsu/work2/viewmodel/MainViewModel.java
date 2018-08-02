package com.example.jinsu.work2.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.jinsu.work2.R;
import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.common.Constants;
import com.example.jinsu.work2.enums.WorkTimeType;
import com.example.jinsu.work2.manager.InfoManager;
import com.example.jinsu.work2.manager.TaskManager;
import com.example.jinsu.work2.model.Contract;
import com.example.jinsu.work2.model.ContractWork;
import com.example.jinsu.work2.model.EmployerPlace;
import com.example.jinsu.work2.model.RestTime;
import com.example.jinsu.work2.model.User;
import com.example.jinsu.work2.model.WorkTime;
import com.example.jinsu.work2.model.Worker;
import com.example.jinsu.work2.network.CommonClass;
import com.example.jinsu.work2.network.SignThread;
import com.example.jinsu.work2.network.contract.ContractSource;
import com.example.jinsu.work2.network.model.Company;
import com.example.jinsu.work2.network.model.CompanyContract;
import com.example.jinsu.work2.network.model.Join;
import com.example.jinsu.work2.network.model.Login;
import com.example.jinsu.work2.network.model.LoginResponse;
import com.example.jinsu.work2.network.model.PersonnalCost;
import com.example.jinsu.work2.network.model.PersonnalCostRequest;
import com.example.jinsu.work2.network.model.WorkSchedule;
import com.example.jinsu.work2.network.model.WorkScheduleItem;
import com.example.jinsu.work2.network.worker.WorkerSource;
import com.example.jinsu.work2.repositories.EmployerRepository;
import com.example.jinsu.work2.util.CallonClick;
import com.example.jinsu.work2.util.Dlog;
import com.example.jinsu.work2.util.ParsingIp;
import com.example.jinsu.work2.util.PreferenceUtil;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
    public final ObservableField<String> inputinfo_edit_rest_address = new ObservableField<>();
    public Uri uri;
    public String url;

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

    //    EmployerFixPlaceActivity
    public final ObservableField<String> fix_place_name = new ObservableField<>();
    public final ObservableField<String> fix_place_num = new ObservableField<>();
    public final ObservableField<String> fix_place_owner = new ObservableField<>();
    public final ObservableField<String> fix_place_phone = new ObservableField<>();
    public final ObservableField<String> fix_place_addr_num = new ObservableField<>();
    public final ObservableField<String> fix_place_addr = new ObservableField<>();
    public final ObservableField<String> fix_place_realaddr_num = new ObservableField<>();
    public final ObservableField<String> fix_place_realaddr = new ObservableField<>();
    public final ObservableField<String> fix_place_wifi = new ObservableField<>();
    //private EmployerPlace employerFixPlace;



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
    private String method_time;

    //    EmployerCalcListActivity
    public final ObservableField<String> calc_num = new ObservableField<>();

    //    EmployerContractWriteActivity
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
    public final ObservableField<String> contract_write_edit_month = new ObservableField<>();

    //    EmployerContractWriteFinActivity
    public final ObservableField<String> contract_write_fin_edit_month = new ObservableField<>();

    //     WorkerHomeActivity
    public final ObservableField<String> company_name = new ObservableField<>();
    public final ObservableField<String> goto_office_time = new ObservableField<>();
    public final ObservableField<String> leave_office_time = new ObservableField<>();

    //     WorkerFindPlaceActivity
    public final ObservableField<String> select_workplace_edittext = new ObservableField<>();
    public int text_len = -1;

    //      WorkerContractSendFinActivity
    public final ObservableField<String> contract_send_fin_email = new ObservableField<>();

    //      contract
    public final ObservableField<String> employerName = new ObservableField<>();    //0-1
    public final ObservableField<String> workerName = new ObservableField<>();      //0-2
    public final ObservableField<String> startDate = new ObservableField<>();       //1-1
    public final ObservableField<String> endDate = new ObservableField<>();         //1-2
    public final ObservableField<String> workPlace = new ObservableField<>();       //2
    public final ObservableField<String> workThing = new ObservableField<>();       //3
    public final ObservableField<String> workDay = new ObservableField<>();         //4
    public final ObservableField<String> workFreeDay = new ObservableField<>();     //6

    public final ObservableField<String> wage = new ObservableField<>();            //7-1
    public final ObservableField<String> excess = new ObservableField<>();          //7-2
    public final ObservableField<String> pay_when_month_date = new ObservableField<>(); //7-3



    //      WorkerContractFin
    public final ObservableField<String> contract_fin_employerName = new ObservableField<>();    //0-1
    public final ObservableField<String> contract_fin_workerName = new ObservableField<>();      //0-2
    public final ObservableField<String> contract_fin_startDate = new ObservableField<>();       //1-1
    public final ObservableField<String> contract_fin_endDate = new ObservableField<>();         //1-2
    public final ObservableField<String> contract_fin_workPlace = new ObservableField<>();       //2
    public final ObservableField<String> contract_fin_workThing = new ObservableField<>();       //3
    public final ObservableField<String> contract_fin_workDay = new ObservableField<>();         //4
    public final ObservableField<String> contract_fin_workFreeDay = new ObservableField<>();     //6

    public final ObservableField<String> contract_fin_wage = new ObservableField<>();
    public final ObservableField<String> contract_fin_excess = new ObservableField<>();
    public final ObservableField<String> contract_fin_pay_when_month_date = new ObservableField<>();

    public final ObservableField<String> contract_fin_final_date = new ObservableField<>();
    public final ObservableField<String> contract_fin_final_place = new ObservableField<>();
    public final ObservableField<String> contract_fin_final_employer_phone = new ObservableField<>();
    public final ObservableField<String> contract_fin_final_employer_address = new ObservableField<>();
    public final ObservableField<String> contract_fin_final_employer_name = new ObservableField<>();
    public final ObservableField<String> contract_fin_final_worker_phone = new ObservableField<>();
    public final ObservableField<String> contract_fin_final_worker_address = new ObservableField<>();
    public final ObservableField<String> contract_fin_final_worker_name = new ObservableField<>();


    //      WorkerContractRequest
    public final ObservableField<String> contract_request_employerName = new ObservableField<>();    //0-1
    public final ObservableField<String> contract_request_workerName = new ObservableField<>();      //0-2
    public final ObservableField<String> contract_request_startDate = new ObservableField<>();       //1-1
    public final ObservableField<String> contract_request_endDate = new ObservableField<>();         //1-2
    public final ObservableField<String> contract_request_workPlace = new ObservableField<>();       //2
    public final ObservableField<String> contract_request_workThing = new ObservableField<>();       //3
    public final ObservableField<String> contract_request_workDay = new ObservableField<>();         //4
    public final ObservableField<String> contract_request_workFreeDay = new ObservableField<>();     //6

    public final ObservableField<String> contract_request_wage = new ObservableField<>();
    public final ObservableField<String> contract_request_excess = new ObservableField<>();
    public final ObservableField<String> contract_request_pay_when_month_date = new ObservableField<>();

    public final ObservableField<String> contract_request_final_date = new ObservableField<>();
    public final ObservableField<String> contract_request_final_place = new ObservableField<>();
    public final ObservableField<String> contract_request_final_employer_phone = new ObservableField<>();
    public final ObservableField<String> contract_request_final_employer_address = new ObservableField<>();
    public final ObservableField<String> contract_request_final_employer_name = new ObservableField<>();
    public final ObservableField<String> contract_request_final_worker_phone = new ObservableField<>();
    public final ObservableField<String> contract_request_final_worker_address = new ObservableField<>();
    public final ObservableField<String> contract_request_final_worker_name = new ObservableField<>();


    //      WorkerContractSend
    public final ObservableField<String> contract_send_employerName = new ObservableField<>();    //0-1
    public final ObservableField<String> contract_send_workerName = new ObservableField<>();      //0-2
    public final ObservableField<String> contract_send_startDate = new ObservableField<>();       //1-1
    public final ObservableField<String> contract_send_endDate = new ObservableField<>();         //1-2
    public final ObservableField<String> contract_send_workPlace = new ObservableField<>();       //2
    public final ObservableField<String> contract_send_workThing = new ObservableField<>();       //3
    public final ObservableField<String> contract_send_workDay = new ObservableField<>();         //4
    public final ObservableField<String> contract_send_workFreeDay = new ObservableField<>();     //6

    public final ObservableField<String> contract_send_wage = new ObservableField<>();
    public final ObservableField<String> contract_send_excess = new ObservableField<>();
    public final ObservableField<String> contract_send_pay_when_month_date = new ObservableField<>();

    public final ObservableField<String> contract_send_final_date = new ObservableField<>();
    public final ObservableField<String> contract_send_final_place = new ObservableField<>();
    public final ObservableField<String> contract_send_final_employer_phone = new ObservableField<>();
    public final ObservableField<String> contract_send_final_employer_address = new ObservableField<>();
    public final ObservableField<String> contract_send_final_employer_name = new ObservableField<>();



    //      employer_ContractFin
    public final ObservableField<String> employer_contract_fin_employerName = new ObservableField<>();    //0-1
    public final ObservableField<String> employer_contract_fin_workerName = new ObservableField<>();      //0-2
    public final ObservableField<String> employer_contract_fin_startDate = new ObservableField<>();       //1-1
    public final ObservableField<String> employer_contract_fin_endDate = new ObservableField<>();         //1-2
    public final ObservableField<String> employer_contract_fin_workPlace = new ObservableField<>();       //2
    public final ObservableField<String> employer_contract_fin_workThing = new ObservableField<>();       //3
    public final ObservableField<String> employer_contract_fin_workDay = new ObservableField<>();         //4
    public final ObservableField<String> employer_contract_fin_workFreeDay = new ObservableField<>();     //6

    public final ObservableField<String> employer_contract_fin_wage = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_excess = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_pay_when_month_date = new ObservableField<>();

    public final ObservableField<String> employer_contract_fin_final_date = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_final_place = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_final_employer_phone = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_final_employer_address = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_final_employer_name = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_final_worker_phone = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_final_worker_address = new ObservableField<>();
    public final ObservableField<String> employer_contract_fin_final_worker_name = new ObservableField<>();


    //      employer_predict_payment
    public final ObservableField<String> predict_payment_name = new ObservableField<>();
    public final ObservableField<String> predict_payment_day = new ObservableField<>();
    public final ObservableField<String> predict_payment_pay_total = new ObservableField<>();
    public final ObservableField<String> predict_payment_pay_wage = new ObservableField<>();
    public final ObservableField<String> predict_payment_pay_base = new ObservableField<>();
    public final ObservableField<String> predict_payment_pay_free = new ObservableField<>();
    public final ObservableField<String> predict_payment_pay_extend = new ObservableField<>();
    public final ObservableField<String> predict_payment_pay_night = new ObservableField<>();



    //    EmployerContractWorkerFragment
    public final ObservableField<String> worker_sum = new ObservableField<>();

    //    EmployerContractTempFragment
    public final ObservableField<String> contract_temp_sum1 = new ObservableField<>();
    public final ObservableField<String> contract_temp_sum2 = new ObservableField<>();


    //    EmployerManageActivity
    public final ObservableField<String> employer_manage_list_sum = new ObservableField<>();
    public final ObservableField<String> employer_manage_req_sum = new ObservableField<>();
    public final ObservableField<String> employer_manage_cur_sum = new ObservableField<>();


    private String id;
    private User user;
    private String email;

    private CallonClick callback ;
    private android.os.Handler handler;
    private Realm realm;

    private Boolean check1 = true;
    private Boolean check2 = true;
    private Boolean check3 = true;
    private Boolean check4 = true;
    private Boolean check5 = true;

    //계약서 작성 checkbox
    private Boolean ch_hour_wage =true;
    private Boolean ch_month_day_wage = true;
    private Boolean ch_way_wage = true;
    private Boolean ch_insur_hire = true;
    private Boolean ch_insur_accident = true;
    private Boolean ch_insur_pension = true;
    private Boolean ch_insur_health = true;


    // private UserDao userDao;


    public MainViewModel(CallonClick onClick)
    {
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

            case "EmployerFixPlaceActivity":
            {
                fix_place_name.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                fix_place_owner.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                fix_place_addr.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                fix_place_addr_num.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                fix_place_phone.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                fix_place_realaddr.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                fix_place_realaddr_num.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
                fix_place_wifi.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
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
                contract_write_edit_month.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });

                break;
            }

            case "EmployerContractWriteFinActivity":
            {
                contract_write_fin_edit_month.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });

                break;
            }

            case "WorkerFindPlaceActivity":
            {
                select_workplace_edittext.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
            }

            case "WorkerContractFinActivity":
            {
                contract_fin_pay_when_month_date.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
            }

            case "WorkerContractRequestActivity":
            {
                contract_request_pay_when_month_date.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {

                    }
                });
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
                    check2 = true;
                    Log.d("gogo","sw체크됨");
                }
                else
                {
                    check2 = false;
                    Log.d("gogo","sw체크x");
                }
                break;
            }

            case R.id.employer_fix_place_check:
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
            case R.id.employer_fix_place_switch:
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
                    method_time = "TIME";
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
                    method_time="MONTH";
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
                   ch_hour_wage = true;
                }
                break;
            }
            case R.id.employer_contract_write_ch_month:
            {
                if(ischeckd)
                {
                    ch_hour_wage = false;
                }
                break;
            }
            case R.id.employer_contract_write_ch_pay_day:
            {
                if(ischeckd)
                {
                    ch_month_day_wage = false;
                }
                break;
            }
            case R.id.employer_contract_write_ch_pay_month:
            {
                if(ischeckd)
                {
                    ch_month_day_wage = true;
                }
                break;
            }
            case R.id.employer_contract_write_ch_pay_how1:
            {
                if(ischeckd)
                {
                    ch_way_wage = true;
                }
                break;
            }
            case R.id.employer_contract_write_ch_pay_how2:
            {
                if(ischeckd)
                {
                    ch_way_wage = false;
                }
                break;
            }
            case R.id.employer_contract_write_ch_insure1:
            {
                if(ischeckd)
                {
                    ch_insur_hire = true;
                }
                else
                {
                    ch_insur_hire = false;
                }
                break;
            }
            case R.id.employer_contract_write_ch_insure2:
            {
                if(ischeckd)
                {
                    ch_insur_accident = true;
                }
                else
                {
                    ch_insur_accident = false;
                }
                break;
            }
            case R.id.employer_contract_write_ch_insure3:
            {
                if(ischeckd)
                {
                    ch_insur_pension = true;
                }
                else
                {
                    ch_insur_pension = false;
                }
                break;
            }
            case R.id.employer_contract_write_ch_insure4:
            {
                if(ischeckd)
                {
                    ch_insur_health = true;
                }
                else
                {
                    ch_insur_health = false;
                }
                break;
            }
            //contract
            case R.id.contract_ch_paytype_time:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_time체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_time체크x");
                }
                break;
            }
            case R.id.contract_ch_paytype_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_month체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_month체크x");
                }
                break;
            }
            case R.id.contract_bonus_ch_no:
            {
                if(ischeckd)
                {
                    Log.d("gogo","bonus_ch_no체크됨");
                }
                else
                {
                    Log.d("gogo","bonus_ch_no체크x");
                }
                break;
            }
            case R.id.contract_bonus_ch_yes:
            {
                if(ischeckd)
                {
                    Log.d("gogo","ch_yes체크됨");
                }
                else
                {
                    Log.d("gogo","ch_yes체크x");
                }
                break;
            }
            case R.id.contract_ch_pay_when_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month체크x");
                }
                break;
            }
            case R.id.contract_pay_when_month_date:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month_date체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month_date체크x");
                }
                break;
            }
            case R.id.contract_ch_pay_how1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how1체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how1체크x");
                }
                break;
            }
            case R.id.contract_ch_pay_how2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how2체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how2체크x");
                }
                break;
            }
            case R.id.contract_insure_1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_1체크됨");
                }
                else
                {
                    Log.d("gogo","insure_1체크x");
                }
                break;
            }
            case R.id.contract_insure_2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_2체크됨");
                }
                else
                {
                    Log.d("gogo","insure_2체크x");
                }
                break;
            }
            case R.id.contract_insure_3:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_3체크됨");
                }
                else
                {
                    Log.d("gogo","insure_3체크x");
                }
                break;
            }
            case R.id.contract_insure_4:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_4체크됨");
                }
                else
                {
                    Log.d("gogo","insure_4체크x");
                }
                break;
            }
            ////////////////////////////////////////////으아아ㅏ///////////////////////////////////////////

            //worker fin
            case R.id.contract_fin_ch_paytype_time:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_time체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_time체크x");
                }
                break;
            }
            case R.id.contract_fin_ch_paytype_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_month체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_month체크x");
                }
                break;
            }
            case R.id.contract_fin_bonus_ch_no:
            {
                if(ischeckd)
                {
                    Log.d("gogo","bonus_ch_no체크됨");
                }
                else
                {
                    Log.d("gogo","bonus_ch_no체크x");
                }
                break;
            }
            case R.id.contract_fin_bonus_ch_yes:
            {
                if(ischeckd)
                {
                    Log.d("gogo","ch_yes체크됨");
                }
                else
                {
                    Log.d("gogo","ch_yes체크x");
                }
                break;
            }
            case R.id.contract_fin_ch_pay_when_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month체크x");
                }
                break;
            }
            case R.id.contract_fin_ch_pay_when_day:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month_date체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month_date체크x");
                }
                break;
            }
            case R.id.contract_fin_ch_pay_how1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how1체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how1체크x");
                }
                break;
            }
            case R.id.contract_fin_ch_pay_how2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how2체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how2체크x");
                }
                break;
            }
            case R.id.contract_fin_insure_1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_1체크됨");
                }
                else
                {
                    Log.d("gogo","insure_1체크x");
                }
                break;
            }
            case R.id.contract_fin_insure_2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_2체크됨");
                }
                else
                {
                    Log.d("gogo","insure_2체크x");
                }
                break;
            }
            case R.id.contract_fin_insure_3:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_3체크됨");
                }
                else
                {
                    Log.d("gogo","insure_3체크x");
                }
                break;
            }
            case R.id.contract_fin_insure_4:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_4체크됨");
                }
                else
                {
                    Log.d("gogo","insure_4체크x");
                }
                break;
            }

            //worker request
            case R.id.contract_request_ch_paytype_time:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_time체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_time체크x");
                }
                break;
            }
            case R.id.contract_request_ch_paytype_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_month체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_month체크x");
                }
                break;
            }
            case R.id.contract_request_bonus_ch_no:
            {
                if(ischeckd)
                {
                    Log.d("gogo","bonus_ch_no체크됨");
                }
                else
                {
                    Log.d("gogo","bonus_ch_no체크x");
                }
                break;
            }
            case R.id.contract_request_bonus_ch_yes:
            {
                if(ischeckd)
                {
                    Log.d("gogo","ch_yes체크됨");
                }
                else
                {
                    Log.d("gogo","ch_yes체크x");
                }
                break;
            }
            case R.id.contract_request_ch_pay_when_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month체크x");
                }
                break;
            }
            case R.id.contract_request_ch_pay_when_day:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month_date체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month_date체크x");
                }
                break;
            }
            case R.id.contract_request_ch_pay_how1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how1체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how1체크x");
                }
                break;
            }
            case R.id.contract_request_ch_pay_how2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how2체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how2체크x");
                }
                break;
            }
            case R.id.contract_request_insure_1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_1체크됨");
                }
                else
                {
                    Log.d("gogo","insure_1체크x");
                }
                break;
            }
            case R.id.contract_request_insure_2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_2체크됨");
                }
                else
                {
                    Log.d("gogo","insure_2체크x");
                }
                break;
            }
            case R.id.contract_request_insure_3:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_3체크됨");
                }
                else
                {
                    Log.d("gogo","insure_3체크x");
                }
                break;
            }
            case R.id.contract_request_insure_4:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_4체크됨");
                }
                else
                {
                    Log.d("gogo","insure_4체크x");
                }
                break;
            }


            //employer send
            case R.id.contract_send_ch_paytype_time:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_time체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_time체크x");
                }
                break;
            }
            case R.id.contract_send_ch_paytype_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_month체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_month체크x");
                }
                break;
            }
            case R.id.contract_send_bonus_ch_no:
            {
                if(ischeckd)
                {
                    Log.d("gogo","bonus_ch_no체크됨");
                }
                else
                {
                    Log.d("gogo","bonus_ch_no체크x");
                }
                break;
            }
            case R.id.contract_send_bonus_ch_yes:
            {
                if(ischeckd)
                {
                    Log.d("gogo","ch_yes체크됨");
                }
                else
                {
                    Log.d("gogo","ch_yes체크x");
                }
                break;
            }
            case R.id.contract_send_ch_pay_when_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month체크x");
                }
                break;
            }
            case R.id.contract_send_ch_pay_when_day:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month_date체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month_date체크x");
                }
                break;
            }
            case R.id.contract_send_ch_pay_how1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how1체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how1체크x");
                }
                break;
            }
            case R.id.contract_send_ch_pay_how2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how2체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how2체크x");
                }
                break;
            }
            case R.id.contract_send_insure_1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_1체크됨");
                }
                else
                {
                    Log.d("gogo","insure_1체크x");
                }
                break;
            }
            case R.id.contract_send_insure_2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_2체크됨");
                }
                else
                {
                    Log.d("gogo","insure_2체크x");
                }
                break;
            }
            case R.id.contract_send_insure_3:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_3체크됨");
                }
                else
                {
                    Log.d("gogo","insure_3체크x");
                }
                break;
            }
            case R.id.contract_send_insure_4:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_4체크됨");
                }
                else
                {
                    Log.d("gogo","insure_4체크x");
                }
                break;
            }


            //employer fin
            case R.id.employer_contract_fin_ch_paytype_time:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_time체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_time체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_ch_paytype_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","paytype_month체크됨");
                }
                else
                {
                    Log.d("gogo","paytype_month체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_bonus_ch_no:
            {
                if(ischeckd)
                {
                    Log.d("gogo","bonus_ch_no체크됨");
                }
                else
                {
                    Log.d("gogo","bonus_ch_no체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_bonus_ch_yes:
            {
                if(ischeckd)
                {
                    Log.d("gogo","ch_yes체크됨");
                }
                else
                {
                    Log.d("gogo","ch_yes체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_ch_pay_when_month:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_ch_pay_when_day:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_when_month_date체크됨");
                }
                else
                {
                    Log.d("gogo","pay_when_month_date체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_ch_pay_how1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how1체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how1체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_ch_pay_how2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","pay_how2체크됨");
                }
                else
                {
                    Log.d("gogo","pay_how2체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_insure_1:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_1체크됨");
                }
                else
                {
                    Log.d("gogo","insure_1체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_insure_2:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_2체크됨");
                }
                else
                {
                    Log.d("gogo","insure_2체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_insure_3:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_3체크됨");
                }
                else
                {
                    Log.d("gogo","insure_3체크x");
                }
                break;
            }
            case R.id.employer_contract_fin_insure_4:
            {
                if(ischeckd)
                {
                    Log.d("gogo","insure_4체크됨");
                }
                else
                {
                    Log.d("gogo","insure_4체크x");
                }
                break;
            }
            case R.id.employer_predict_payment_pay_send_together:
            {
                if(ischeckd)
                {
                    Log.d("gogo","send_together체크됨");
                }
                else
                {
                    Log.d("gogo","send_together체크x");
                }
                break;
            }


        }
    }

    @BindingAdapter({"loadImg"})
    public static void setImage(ImageView image, Uri uri)
    {
        Glide.with(image.getContext()).load(uri)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())).into(image);
    }

    @BindingAdapter( "loadImg")
    public static void setImage(ImageView image, String url)
    {
        Glide.with(image.getContext()).load(url).into(image);
    }

/*
    public void getUsers(Context context)
    {
        MainRepository.getInstance().getUsers(context, new UserSource.LoadDataCallback() {
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
     * 로그인 Activity
     */

    /**
     * Login 시도 시 호출
     */
    public void onLogin()
    {
        Login login = new Login(login_edit_email.get().toString(),"1");
        BaseApplication.mApiService.login(login, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {
                //성공
                InfoManager.setOAuthToken(loginResponse.access_token);
                callback.textChanged(0);
            }

            @Override
            public void failure(RetrofitError error) {
                //실패
            }
        });

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
                Company company = new Company();
                company.name = place_name.get();
                company.registration_number = place_num.get();
                company.employer_name = place_owner.get();
                company.phone = place_phone.get();
                company.postcode = Integer.valueOf(place_addr_num.get());
                company.head_postcode = Integer.valueOf(place_realaddr_num.get());
                company.head_address = place_realaddr.get();
                company.address = place_addr.get();
                company.wifi_ip_address = place_wifi.get();
                company.is_less_then_5_employee = check1;
                company.usage_wifi = check2;

                TaskManager.api_company_me_create(company, new Callback<Company>() {
                    @Override
                    public void success(Company company, Response response) {
                        int id = company.id;
                        PreferenceUtil.savePlaceId(id);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
                callback.onBtnClick(view);
                break;
            }
            case R.id.employer_create_place_btn_wifi:
            {

                callback.onBtnClick(view);
                break;
            }
            case R.id.employer_fix_place_btn_create:
            {
                employerPlace = new EmployerPlace(fix_place_name.get(),fix_place_addr.get()
                        ,fix_place_phone.get(),fix_place_owner.get());
//                EmployerRepository.getInstance().addEmployerPlace(employerPlace);
                callback.onBtnClick(view);
                break;
            }
            case R.id.worker_home_btn_goto_office:
            {
                break;
            }
            case R.id.worker_home_btn_leave_office:
            {
                break;
            }

            case R.id.cert_btn_again:
            {
                requestCert(BaseApplication.join.email);
                break;
            }
            case R.id.cert_btn_next:
            {
                onCert(BaseApplication.join.email);
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
        SignThread signThread = new SignThread(bitmap, new SignThread.Callback() {
            @Override
            public void finish() {
                callback.textChanged(0);
            }
        });
        signThread.start();
        try {
            Log.d("SignActivity!","스레드 종료");
            signThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }




    /**
     * 회원가입 시작 버튼 클릭 처리 함수
     *
     * @param context JoinActivity
     * @return 가입여부
     */
    public void onJoinUser(Context context)
    {
        if(join_edit_email.get() != null) {
            email = join_edit_email.get();
            if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+$")) {
                //
            }
            TaskManager.api_check_exist_email(email, new Callback<HashMap<String, Object>>() {
                @Override
                public void success(HashMap<String, Object> stringObjectHashMap, Response response) {
                    if(stringObjectHashMap != null) {
                        boolean result = (boolean) stringObjectHashMap.get("result");
                        if(result) {
                            //TODO 기존 가입자처리
                            Log.d("join_at","기존 가입됨");
                            callback.textChanged(Constants.RESPONSE_DUPLICATE);

                        } else {
                            //TODO 새로가입자
                            //다이얼로그 생성
                            Log.d("join_at","이메일 가입 시작");
                            callback.textChanged(Constants.RESPONSE_SUCCESS);
                            BaseApplication.join.email = email;
                        }
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    CommonClass.showError(error);
                    Log.d("join_at",error.getMessage());
                }
            });
        }
        else
        {
            return;
        }
    }


    /**
     *
     * CertActivity
     * 이메일 인증 activity
     *
     */



    public void requestCert(String email)
    {
        TaskManager.api_request_verify_email(URLEncoder.encode(email), new Callback<HashMap<String, Object>>() {

            @Override
            public void success(HashMap<String, Object> stringObjectHashMap, Response response) {
                Log.d("cert_at",CommonClass.toJson(stringObjectHashMap));
                boolean result = (boolean) stringObjectHashMap.get("result");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("cert_at","Error : "+error.getMessage());
            }
        });

    }

    public void onCert(String email)
    {
        TaskManager.api_verify_email(URLEncoder.encode(email), Integer.parseInt(one.get() + two.get() + three.get() + four.get()), new Callback<HashMap<String, Object>>() {

            @Override
            public void success(HashMap<String, Object> stringObjectHashMap, Response response) {
                boolean result = (boolean) stringObjectHashMap.get("result");
                if(result) {
                    callback.textChanged(0);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    /**
     *
     * SelectActivity
     * 역할 선택 activity
     *
     */
    public void selectRole(int flag)
    {
        if(flag == Constants.REQUEST_EMPLOYER)
        {
            BaseApplication.join.role = "employer";
            TaskManager.api_create_user(BaseApplication.join, new Callback<Join>() {
                @Override
                public void success(Join join, Response response) {
                    if(join != null) {
                        PreferenceUtil.saveUser(join);
                        callback.textChanged(Constants.REQUEST_EMPLOYER);
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
        else if(flag == Constants.REQUEST_EMPLOYEE)
        {
            BaseApplication.join.role = "employee";
            TaskManager.api_create_user(BaseApplication.join, new Callback<Join>() {
                @Override
                public void success(Join join, Response response) {
                    if(join != null) {
                        PreferenceUtil.saveUser(join);
                        callback.textChanged(Constants.REQUEST_EMPLOYEE);
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }

    }


    /**
     * InputInfoActivity
     * 기본정보 입력 activity
     */

    public void setUser()
    {
        BaseApplication.join.address = inputinfo_address.get() + inputinfo_edit_rest_address.get();
        BaseApplication.join.name = inputinfo_edit_name.get();
        BaseApplication.join.phone = inputinfo_edit_registerNum.get();
        BaseApplication.join.postcode = inputinfo_edit_postcode.get();
        callback.textChanged(0);
    }
    public void setAddr(String addr)
    {
        inputinfo_edit_postcode.set(addr.substring(0,5));
        inputinfo_address.set(addr.substring(7,addr.length()));
    }

    /**
     *
     * JoinActivity
     * 회원가입 activity
     *
     */

    public interface ListCallback
    {
        void getList(ArrayList<? extends Object> mlist);
    }

    public void getCompanyList(ListCallback listCallback)
    {
        TaskManager.api_company_me_list(new Callback<ArrayList<Company>>() {
            @Override
            public void success(ArrayList<Company> companies, Response response) {
                Log.d("employer_place_at",CommonClass.toJson(companies));
                listCallback.getList(companies);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("employer_place_at",CommonClass.toJson(error));
            }
        });
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
            check1 = true;
        }

        else if(id == R.id.employer_fix_place_radio_one)
        {
            check1 = true;
            //5인 미만
        }
        else
        {
            check1 = false;
            //5인 이상
        }
    }

    public String getWifi()
    {
        String parse_ip = "";
        ParsingIp parse = new ParsingIp();

        parse.execute();
        try {
            parse_ip = parse.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        place_wifi.set(parse_ip);
        return parse_ip;
    }


    /**
     *
     * EmployerCalcActivity
     * 인건비 계산 activity
     *
     */
    //인건비 계산한 데이터 저장
    public void onSaveCalc(String name, ContractWork contractWork)
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

        PersonnalCostRequest personnalCostRequest = new PersonnalCostRequest();
        personnalCostRequest.company_id = PreferenceUtil.loadPlaceId();
        personnalCostRequest.employee_id = PreferenceUtil.loadUser().id;
        personnalCostRequest.title = name;
        personnalCostRequest.salary_method = method_time;
        TaskManager.personnal_cost_save(
                PreferenceUtil.loadPlaceId(),
                51,
                personnalCostRequest, new Callback<PersonnalCost>() {
                    @Override
                    public void success(PersonnalCost personnalCost, Response response) {
                        Log.d("calc_at",CommonClass.toJson(personnalCost));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("calc_at",CommonClass.showError(error));
                    }
                });

        ArrayList<WorkSchedule> workSchedules = new ArrayList<>();

        /*TaskManager.personnal_work_schedule(
                PreferenceUtil.loadPlaceId(),
                51,
                list, new Callback<WorkScheduleItem>() {
                    @Override
                    public void success(WorkScheduleItem workScheduleItem, Response response) {
                        Log.d("calc_at",CommonClass.toJson(workScheduleItem));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("calc_at",CommonClass.showError(error));
                    }
                });*/

        /*realm.beginTransaction();
        CalcContent calc = realm.createObject(CalcContent.class);
        calc.setName(name);
        calc.setTotal_wage("1000");
        calc.setDate(month + "월 " + date + "일 " + hour + ":" + min);
        realm.commitTransaction();*/

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


    public void getCalcList(ListCallback callback)
    {
        TaskManager.get_personnal_cost_list(PreferenceUtil.loadPlaceId(), new Callback<ArrayList<PersonnalCost>>() {
            @Override
            public void success(ArrayList<PersonnalCost> perlist, Response response) {
                ArrayList<PersonnalCost> list = perlist;
                callback.getList(list);
                Log.d("calc_list",CommonClass.toJson(perlist));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("calc_list",CommonClass.showError(error));
            }
        });
    }

    /**
     * EmployerContractWriteActivity
     * 로그인 Activity
     */

    public CompanyContract getContract(ContractWork work)
    {
        CompanyContract contract = new CompanyContract();
        contract.employer_name = contract_write_owner.get();
        contract.employee_name = contract_write_worker.get();
        contract.working_place = contract_write_place.get();
        contract.working_description = contract_write_content.get();
        contract.begin = contract_write_term1.get();
        contract.end = contract_write_term2.get();
        contract.approve_date = contract_write_day.get();
        contract.work_day = contract_write_day.get();
        contract.holiday = contract_write_holiday.get();
        contract.wage = contract_write_wage.get();
        contract.bonus_pay = Integer.valueOf(contract_write_bonus.get());
        contract.bonus_rate_of_over_time = Integer.valueOf(contract_write_excess.get()) / 100;
        if(ch_month_day_wage)
        {
            contract.payment_cycle = "MONTHLY";
        }
        if(ch_way_wage)
        {
            contract.payment_method = "BANK_TRANSFER";
        }
        else
        {

        }
        contract.insu_employment = ch_insur_hire;
        contract.insu_health = ch_insur_health;
        contract.insu_industrial_accident = ch_insur_accident;
        contract.insu_national = ch_insur_pension;
        contract.schedule.hour_pay = Integer.parseInt(contract_write_wage.get());

        List<RestTime> rest_times = work.getRestTime();
        List<WorkTime> work_times = work.getWorkTime();


        int cnt = 0;
        WorkScheduleItem item = new WorkScheduleItem();
        for(WorkTime time : work_times)
        {
            if(time.getType() == WorkTimeType.Work)
            {
                if(cnt !=0)
                {
                    contract.schedule.weekly_items.add(item);
                }
                item.begin = String.valueOf(time.getStart());
                item.end = String.valueOf(time.getEnd());
                item.day_of_week = time.getWeek().getValue();
                item.item_type = "work";
                cnt++;
            }
            else
            {
                WorkScheduleItem meal = new WorkScheduleItem();
                meal.item_type = "meal";
                meal.day_of_week = time.getWeek().getValue();
                meal.begin = String.valueOf(time.getStart());
                meal.end = String.valueOf(time.getEnd());
                item.meals.add(meal);
            }
        }
        return contract;

    }


    /**
     *
     * EmployerContractWorkerFragment
     * 계약서 보관함의 근로자 계약서 fragment
     *
     */

    public void getContractWorkers(MainViewModel.ContractWorkerCallback callback)
    {
        EmployerRepository.getInstance().getContractWorker(new ContractSource.LoadContractWorkerCallback() {
            @Override
            public void onContractWorkerLoad(ArrayList<Contract> list) {
                if(list != null)
                {
                    worker_sum.set(String.valueOf(list.size()));
                    callback.get(list);
                }
            }
        });

    }

    public interface ContractWorkerCallback
    {
        void get(ArrayList<Contract> contracts);
    }

    /**
     *
     * EmployerContractTempFragment
     * 계약서 보관함의 미완성 계약서 fragment
     *
     */

    public void getContractTemp(MainViewModel.ContractTempCallback callback)
    {
        EmployerRepository.getInstance().getContractTemp(new ContractSource.LoadContractTempCallback() {
            @Override
            public void onContractTempLoad(ArrayList<Contract> list) {
                if(list != null)
                {
                    contract_temp_sum1.set(String.valueOf(list.size()));
                    callback.get(list);
                }
            }
        });

    }

    public interface ContractTempCallback
    {
        void get(ArrayList<Contract> contracts);
    }

    public void onSaveContractTemp(String name)
    {
        //데이터 삭제
       /* realm.beginTransaction();
        RealmResults<Contract> userList = realm.where(Contract.class).findAll();
        userList.deleteAllFromRealm();
        realm.commitTransaction();*/

        realm.beginTransaction();
        Contract contract = realm.createObject(Contract.class);
        contract.setName("와우");
        contract.setDate("2017-07-07");
        realm.commitTransaction();

    }

    public void setContractTempSum(int num)
    {
        contract_temp_sum2.set(String.valueOf(num));
    }



    /**
     *
     * EmployerManageActivity
     * 근로자 관리 Activity
     *
     */

    public interface WorkerCallback
    {
        void get(ArrayList<Worker> workers);
    }

    public void getReqWorker(MainViewModel.WorkerCallback callback)
    {
        EmployerRepository.getInstance().getReqWorker(new WorkerSource.LoadReqWorkerCallback() {
            @Override
            public void onReqWorkerLoad(ArrayList<Worker> list) {
                employer_manage_list_sum.set(String.valueOf(list.size()));
                employer_manage_req_sum.set(String.valueOf(list.size()));
                callback.get(list);
            }
        });
    }

    public void getCurWorker(MainViewModel.WorkerCallback callback)
    {
        EmployerRepository.getInstance().getCurWorker(new WorkerSource.LoadCurWorkerCallback() {
            @Override
            public void onCurWorkerLoad(ArrayList<Worker> list) {
                employer_manage_cur_sum.set(String.valueOf(list.size()));
                callback.get(list);
            }
        });
    }

    /**
     *
     * WorkerFindPlaceActivtiy
     * 근로자 사업주 찾기 화면
     *
     */

    public void findTextChanged(CharSequence s, int start, int before, int count)
    {
        if(s.length() != 0)
        {
            if(s.length() != text_len)
            {
                callback.textChanged(0);
            }
        }
        text_len = s.length();
    }


    /**
     *
     * WorkerHomeActivity
     * 근로자 홈 화면
     *
     */
    public void setCompanyName(String text)
    {
        company_name.set(text);
    }

    public void setWork()
    {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm");
        goto_office_time.set(dayTime.format(new Date(time)));
        Log.d("worker_home","work");
    }

    public void setLeave()
    {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm");
        leave_office_time.set(dayTime.format(new Date(time)));
    }

}
