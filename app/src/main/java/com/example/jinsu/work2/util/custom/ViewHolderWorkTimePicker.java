package com.example.jinsu.work2.util.custom;

import android.app.TimePickerDialog;
import android.content.Context;
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
 * Created by kasum on 2018-06-03.
 */

public class ViewHolderWorkTimePicker {
    private boolean readonly;

    public WorkTimeType type;

    public TextView title;
    public EditText StartTime;
    public EditText EndTime;

    public ViewHolderWorkTimePicker(View itemView) {
        this.type = WorkTimeType.Work;
        title = (TextView) itemView.findViewById(R.id.textTitle);
        StartTime = (EditText) itemView.findViewById(R.id.editStartTime);
        EndTime = (EditText) itemView.findViewById(R.id.editEndTime);

        StartTime.setOnClickListener(new onClickTimeEdit(itemView.getContext(), StartTime));
        EndTime.setOnClickListener(new onClickTimeEdit(itemView.getContext(), EndTime));
    }

    public WorkTime GetTimes() {
        WorkTime t = new WorkTime();

        t.setType(type);
        t.setStart(TimeString.ConvertTimeStringToMinuites(StartTime.getText().toString()));
        t.setEnd(TimeString.ConvertTimeStringToMinuites(EndTime.getText().toString()));

        return t;
    }

    public void SetTime(short st, short et) {
        StartTime.setText(TimeString.ConvertMinuitesToTimeString(st));
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