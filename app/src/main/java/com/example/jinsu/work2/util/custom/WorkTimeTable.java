package com.example.jinsu.work2.util.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.enums.WorkTimeType;
import com.example.jinsu.work2.model.WorkTime;
import com.example.jinsu.work2.util.TimeString;

import java.util.List;

public class WorkTimeTable extends LinearLayout {
    private static final short MAX_LUNCH_TIME_COUNT = 5;
    private boolean readonly = false;

    // UI Views
    private CheckBox chkWeek;
    private TextView txtWorkHour;
    private EditText editRestTimePerHour;

    private LinearLayout layoutRestTime;
    private LinearLayout layoutWorkHour;
    private WorkTimePicker workTimePicker;

    private RecyclerView rcLunchTimes;
    private AdapterLunchTimes adapaterLunchTimes;
    private FrameLayout btnAddLunch;
    private RecyclerView.LayoutManager layoutManager;

    public WorkTimeTable(Context context) {
        super(context);
        init(context);
    }

    public WorkTimeTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        getAttrs(attrs);
    }

    public WorkTimeTable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
        getAttrs(attrs, defStyle);
    }

    private void init(Context context) {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.component_worktime_table, this, false);

        addView(v);

        chkWeek = (CheckBox)findViewById(R.id.chkWeek);
        txtWorkHour = (TextView)findViewById(R.id.txtWorkHour);
        editRestTimePerHour = (EditText)findViewById(R.id.editRestTimePerHour);

        layoutWorkHour = (LinearLayout)findViewById(R.id.layoutWorkHour);
         workTimePicker = (WorkTimePicker)findViewById(R.id.timeWork);
         rcLunchTimes = (RecyclerView)findViewById(R.id.rcLunchTimes);
         btnAddLunch = (FrameLayout)findViewById(R.id.btnAddLunch);
        layoutRestTime = (LinearLayout)findViewById(R.id.layoutRestTime);

        adapaterLunchTimes = new AdapterLunchTimes();

        layoutManager = new LinearLayoutManager(context);
        rcLunchTimes.setLayoutManager(layoutManager);
        rcLunchTimes.setAdapter(adapaterLunchTimes);

        workTimePicker.SetTimeTextWatcher(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int st = 0;
                int et = 0;
                Log.d("work_time_table","텍스트 바뀜");
                try {
                    st = TimeString.ConvertTimeStringToMinuites(workTimePicker.GetStartTimeText());
                    et = TimeString.ConvertTimeStringToMinuites(workTimePicker.GetEndTimeText());
                } catch (Exception e) {
                    return;
                }

                UpdateWorkHour(st, et);
            }
        });
    }

    private void UpdateWorkHour(int st, int et) {
        double h = 0;

        if(et >= st) {
            h = (double)(et - st) / (double)60;
        }

        txtWorkHour.setText(String.format("%.2f",h));
    }

    public void SetReadonly(boolean readonly) {
        this.readonly = readonly;

        workTimePicker.SetReadonly(this.readonly);

        editRestTimePerHour.setEnabled(!this.readonly);
        editRestTimePerHour.setFocusable(!this.readonly);
        chkWeek.setFocusable(!this.readonly);
        chkWeek.setEnabled(!this.readonly);

        adapaterLunchTimes.SetReadonly(this.readonly);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WorkTimeTable);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WorkTimeTable, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        String str = typedArray.getString(R.styleable.WorkTimeTable_week);
        chkWeek.setText(str);
        typedArray.recycle();

        chkWeek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("work_time_table","check됨");
                if(readonly == false ){
                    if(b) {
                        Show();
                    } else {
                        Hide();
                    }
                }

            }
        });

        btnAddLunch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapaterLunchTimes.getItemCount() < MAX_LUNCH_TIME_COUNT && readonly == false) {
                    WorkTime t = new WorkTime();
                    t.setType(WorkTimeType.Lunch);
                    adapaterLunchTimes.AddItem(t);
                }
            }
        });
    }

    private void Show(){
        layoutWorkHour.setVisibility(View.VISIBLE);
        layoutRestTime.setVisibility(View.VISIBLE);

        workTimePicker.setVisibility(View.VISIBLE);
        rcLunchTimes.setVisibility(View.VISIBLE);

        if(!this.readonly) {
            btnAddLunch.setVisibility(View.VISIBLE);
        }
    }

    private void Hide() {
        layoutWorkHour.setVisibility(View.GONE);
        layoutRestTime.setVisibility(View.GONE);

        workTimePicker.setVisibility(View.GONE);
        rcLunchTimes.setVisibility(View.GONE);
        btnAddLunch.setVisibility(View.GONE);
    }

    public short GetRestTime(){
        short t = 0;

        try {
            String v = editRestTimePerHour.getText().toString();
            t = Short.parseShort(v);
        } catch(Exception e) {
            return t;
        }

        return t;
    }

    public WorkTime GetWorkTime() {
        return workTimePicker.GetTimes();
    }

    public List<WorkTime> GetLunchTimes() {
        return adapaterLunchTimes.GetLunchTimes();
    }

    public void SetWorkTime(WorkTime wt) {
        UpdateWorkHour(wt.getStart(), wt.getEnd());
        workTimePicker.SetTimes(wt.getStart(), wt.getEnd());
    }

    public void SetRestTime(short rt){
        editRestTimePerHour.setText(String.valueOf(rt));
    }

    public void AddLunchTime(WorkTime lt) {
        adapaterLunchTimes.AddItem(lt);
    }

    public void ClearLucnTimes() {
        adapaterLunchTimes.Clear();
    }

    public void SetLunchTime(List<WorkTime> lt) {
        adapaterLunchTimes.Clear();

        for(WorkTime wt : lt) {
            adapaterLunchTimes.AddItemWithoutNotify(wt);
        }

        adapaterLunchTimes.notifyDataSetChanged();
    }

    public boolean IsChecked(){
        return chkWeek.isChecked();
    }

    public void SetChecked(boolean check) {
        if(check) {
            Show();
        } else {
            Hide();
        }
        chkWeek.setChecked(check);
    }
}
