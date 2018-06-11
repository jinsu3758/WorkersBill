package com.example.jinsu.work2.util.custom;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.enums.WorkTimeType;
import com.example.jinsu.work2.model.WorkTime;
import com.example.jinsu.work2.util.TimeString;

import java.util.Calendar;

/**
 * Created by kasum on 2018-06-06.
 */

public class ViewHolderLunchTimePicker extends RecyclerView.ViewHolder {
    /*
     * ViewhodlerWorkTimePicker와 겹치는 부분이 많아 중복 제가 가능할 수 있으나 아래 사유로 복붙해서 사용합니다.
     * 1. LunchTime 뷰홀더는 상속을 받아야함. 다중 상속 필요해질 수 있음
     * 2. 여기 말곤 쓰이는데가 더 없음. 반복해서 사용할 여지 거의 없음.(ex: Work, Lunch 외 다른 시간이 더 추가될 가능성. 있더라도 극히 적을거라 예상)
    */
    private boolean readonly;
    private AdapterLunchTimes adapter;

    public WorkTimeType type;
    public TextView title;
    public EditText StartTime;
    public EditText EndTime;

    public ViewHolderLunchTimePicker(View itemView, final AdapterLunchTimes adapter) {
        super(itemView);

        this.adapter = adapter;
        this.type = WorkTimeType.Lunch;
        title = (TextView)itemView.findViewById(R.id.textTitle);
        StartTime = (EditText) itemView.findViewById(R.id.editStartTime);
        EndTime = (EditText) itemView.findViewById(R.id.editEndTime);

        StartTime.setOnClickListener(new onClickTimeEdit(itemView.getContext(), StartTime));
        EndTime.setOnClickListener(new onClickTimeEdit(itemView.getContext(), EndTime));

        StartTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                int pos = getAdapterPosition();
                String str = StartTime.getText().toString();

                adapter.GetLunchTime(pos).setStart(TimeString.ConvertTimeStringToMinuites(str));
            }
        });

        EndTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                int pos = getAdapterPosition();
                String str = EndTime.getText().toString();

                adapter.GetLunchTime(pos).setStart(TimeString.ConvertTimeStringToMinuites(str));
            }
        });
    }

    public WorkTime GetTimes(){
        WorkTime t = new WorkTime();

        t.setType(type);

        t.setStart(TimeString.ConvertTimeStringToMinuites(StartTime.getText().toString()));
        t.setEnd(TimeString.ConvertTimeStringToMinuites(EndTime.getText().toString()));

        return t;
    }

    public void SetTime(short st, short et) {
        if(st >= 0)
            StartTime.setText(TimeString.ConvertMinuitesToTimeString(st));

        if(et >= 0)
            EndTime.setText(TimeString.ConvertMinuitesToTimeString(et));
    }

    public void SetReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public class onClickTimeEdit implements View.OnClickListener {
        private Context context;
        private EditText editText;

        public onClickTimeEdit(Context context, EditText editText) {
            this.context = context;
            this.editText = editText;
        }

        @Override
        public void onClick(View view) {
            if(!readonly) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(this.context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editText.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        }
    }
}