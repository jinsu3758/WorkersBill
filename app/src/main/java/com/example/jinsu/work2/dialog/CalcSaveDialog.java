package com.example.jinsu.work2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.viewmodel.EmployerViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class CalcSaveDialog extends Dialog {


    private EmployerViewModel employerViewModel;
    private VIewModelFactory vIewModelFactory;
    private EditText name;
    private Button btn_cancel;
    private Button btn_save;
    private Button btn_write;

    private onBtnCallback callback;


    public CalcSaveDialog(@NonNull Context context, onBtnCallback callback) {
        super(context);
        this.callback = callback;
    }

    public interface onBtnCallback
    {
        void onSave(String name);
        void onWrite();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_calc_save);
        btn_cancel = (Button)findViewById(R.id.dialog_calc_btn_cancel);
        btn_save = (Button)findViewById(R.id.dialog_calc_btn_save);
        btn_write = (Button)findViewById(R.id.dialog_calc_btn_write);
        name = (EditText)findViewById(R.id.dialog_calc_edit_title);

        btn_cancel.setOnClickListener(v ->
        {
            dismiss();
        });
        btn_save.setOnClickListener(v ->
        {
            callback.onSave(name.getText().toString());
            dismiss();
        });
        btn_write.setOnClickListener(v ->
        {
            callback.onWrite();
        });

    }

}
