package com.example.jinsu.work2.util.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.enums.Week;
import com.example.jinsu.work2.enums.WorkTimeType;
import com.example.jinsu.work2.model.ContractWork;
import com.example.jinsu.work2.model.RestTime;
import com.example.jinsu.work2.model.WorkTime;

import java.util.ArrayList;
import java.util.List;

public class WorkTimeView extends LinearLayout {
    /*
        근로 시간 데이터 샘플
        본 기능들은 아래 데이터를 기준으로 만들었습니다. 백엔드 개발자와 회의한 뒤 프로토콜을 맞춰서 기능 수정하면 됩니다.
        점심시간은 n개 이상 데이터를 가질 수 있으며, Work Time은 1개만 대응합니다. 추후 기능 수정을 통해 n개로도 확장은 가능합니다.

        Week, Work/Lunch는 Enums를 참고하기 바랍니다.

        WorkTime: [
                { week: Enums.DayOfWeek.Monday, type: Enums.WorkTimeType.Work, start: 600, end: 1020 },
                { week: Enums.DayOfWeek.Monday, type: Enums.WorkTimeType.Lunch, start: 720, end: 780 },
                { week: Enums.DayOfWeek.Tueseday, type: Enums.WorkTimeType.Work, start: 600, end: 1020 },
                { week: Enums.DayOfWeek.Tueseday, type: Enums.WorkTimeType.Lunch, start: 720, end: 780 },
                { week: Enums.DayOfWeek.Wednesday, type: Enums.WorkTimeType.Work, start: 600, end: 1020 },
                { week: Enums.DayOfWeek.Wednesday, type: Enums.WorkTimeType.Lunch, start: 720, end: 780 },
                { week: Enums.DayOfWeek.Thursday, type: Enums.WorkTimeType.Work, start: 600, end: 1020 },
                { week: Enums.DayOfWeek.Thursday, type: Enums.WorkTimeType.Lunch, start: 720, end: 780 },
                { week: Enums.DayOfWeek.Friday, type: Enums.WorkTimeType.Work, start: 600, end: 1020 },
                { week: Enums.DayOfWeek.Friday, type: Enums.WorkTimeType.Lunch, start: 720, end: 780 },
            ],
            RestTime: [
                { week: Enums.DayOfWeek.Monday, time: 10 },
                { week: Enums.DayOfWeek.Tueseday, time: 10 },
                { week: Enums.DayOfWeek.Wednesday, time: 10 },
                { week: Enums.DayOfWeek.Thursday, time: 10 },
                { week: Enums.DayOfWeek.Friday, time: 10 }
            ]
     */

    private List<WorkTimeTable> workTables;
    private boolean readonly;

    public WorkTimeView(Context context) {
        super(context);
        init();
    }

    public WorkTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
        init();
    }

    public WorkTimeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getAttrs(attrs, defStyle);
        init();
    }

    private void init() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.component_worktime_view, this, false);

        addView(v);

        workTables = new ArrayList<>();

        workTables.add((WorkTimeTable)findViewById(R.id.mondayWorkTable));
        workTables.add((WorkTimeTable)findViewById(R.id.tuesedayWorkTable));
        workTables.add((WorkTimeTable)findViewById(R.id.wedneWorkTableWorkTable));
        workTables.add((WorkTimeTable)findViewById(R.id.thursdayWorkTable));
        workTables.add((WorkTimeTable)findViewById(R.id.fridayWorkTable));
        workTables.add((WorkTimeTable)findViewById(R.id.saturdayWorkTable));
        workTables.add((WorkTimeTable)findViewById(R.id.sundayWorkTable));


        /*ArrayList<WorkTime> workTimes = new ArrayList<>();
        ArrayList<RestTime> restTimes = new ArrayList<>();
        Week week = Week.Monday;
        WorkTime type = new WorkTime();
        workTimes.add(new WorkTime(week, type));
        restTimes.add(new RestTime(week,(short)10));
        ContractWork work = new ContractWork();
        work.setWorkTime(workTimes);
        work.setRestTime(restTimes);
        SetData(work);*/

        for(WorkTimeTable wt : workTables) {
            wt.SetChecked(false);
//            wt.SetReadonly(readonly);
        }
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WorkTimeView);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WorkTimeView, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        this.readonly = typedArray.getBoolean(R.styleable.WorkTimeView_readonly, false);

        typedArray.recycle();
    }

    // HTTP 통신 등으로 서버 백엔드에서 가져온 데이터를 입력합니다.
    public void SetData(ContractWork work){
        for(WorkTime wt : work.getWorkTime()) {
            int week = wt.getWeek().getValue();

            workTables.get(week).ClearLucnTimes();

            if(week >= 0 && week < workTables.size()) {
                workTables.get(week).SetChecked(true);

                if(wt.getType() == WorkTimeType.Work) {
                    workTables.get(week).SetWorkTime(wt);
                } else if(wt.getType() == WorkTimeType.Lunch) {
                    workTables.get(week).AddLunchTime(wt);
                } else {
                    // do nothing
                }
            }
        }

        for(RestTime rt : work.getRestTime()) {
            int week = rt.getWeek().getValue();

            if(week >= 0 && week < workTables.size()) {
                workTables.get(week).SetChecked(true);
                workTables.get(week).SetRestTime(rt.getTime());
            }
        }
    }

    // 반대로 HTTP 요청으로 보낼 데이터를 받아온 형식과 동일한 객체로 리턴합니다.
    public ContractWork GetData(){
        ContractWork contractWork = new ContractWork();

        for(int i=0;i<workTables.size(); ++i) {
            if(workTables.get(i).IsChecked()) {
                Week week = Week.values()[i];

                for(WorkTime wt : workTables.get(i).GetLunchTimes()) {
                    WorkTime lunch = new WorkTime(week, wt);

                    contractWork.getWorkTime().add(lunch);
                }

                WorkTime work = new WorkTime(week, workTables.get(i).GetWorkTime());
                RestTime rest = new RestTime(week, workTables.get(i).GetRestTime());

                contractWork.getWorkTime().add(work);
                contractWork.getRestTime().add(rest);
            }
        }

        return contractWork;
    }
}
