package com.example.jinsu.work2.util.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.model.WorkTime;


public class WorkTimePicker extends LinearLayout {
    private ViewHolderWorkTimePicker viewHolder;
    private boolean readonly;

    public void SetReadonly(boolean readonly) {
        this.readonly = readonly;

        viewHolder.SetReadonly(readonly);
        /*
        viewHolder.StartTime.setEnabled(!readonly);
        viewHolder.StartTime.setFocusable(!readonly);

        viewHolder.EndTime.setEnabled(!readonly);
        viewHolder.EndTime.setFocusable(!readonly);*/
    }

    public WorkTimePicker(Context context) {
        super(context);
        init();
    }

    public WorkTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        getAttrs(attrs);
    }

    public WorkTimePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        getAttrs(attrs, defStyle);
    }

    private void init() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.component_worktime_picker, this, false);

        addView(v);

        viewHolder = new ViewHolderWorkTimePicker(this);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WorkTimePicker);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WorkTimePicker, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        String str = typedArray.getString(R.styleable.WorkTimePicker_title);
        viewHolder.title.setText(str);
        typedArray.recycle();
    }

    public WorkTime GetTimes() {
        return viewHolder.GetTimes();
    }

    public void SetTimes(short st, short et) {
        viewHolder.SetTime(st, et);
    }
    public String GetStartTimeText() { return viewHolder.StartTime.getText().toString(); }
    public String GetEndTimeText() { return viewHolder.EndTime.getText().toString(); }

    public void SetTimeTextWatcher(TextWatcher watcher) {
        viewHolder.StartTime.addTextChangedListener(watcher);
        viewHolder.EndTime.addTextChangedListener(watcher);
    }
}
