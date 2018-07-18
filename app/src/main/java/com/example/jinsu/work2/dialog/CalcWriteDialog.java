package com.example.jinsu.work2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;

import com.example.jinsu.work2.R;

public class CalcWriteDialog extends Dialog {

    private Button btn_continue;
    private Button btn_save;

    private onBtnCallback callback;


    public CalcWriteDialog(@NonNull Context context, onBtnCallback callback) {
        super(context);
        this.callback = callback;
    }

    public interface onBtnCallback
    {
        void onSave();
        void onContinue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_calc_write);
        btn_save = (Button)findViewById(R.id.dialog_write_btn_save);
        btn_continue = (Button)findViewById(R.id.dialog_write_btn_continue);


        btn_save.setOnClickListener(v ->
        {
            callback.onSave();
            dismiss();
        });
        btn_continue.setOnClickListener(v ->
        {
            callback.onContinue();
            dismiss();
        });

    }

}
