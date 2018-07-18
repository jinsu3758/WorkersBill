package com.example.jinsu.work2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;

import com.example.jinsu.work2.R;

public class SelectDataDialog extends Dialog {

    private Button btn_calc;
    private Button btn_contract;

    private onBtnCallback callback;


    public SelectDataDialog(@NonNull Context context, onBtnCallback callback) {
        super(context);
        this.callback = callback;
    }

    public interface onBtnCallback
    {
        void onCalc();
        void onContract();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_data);
        btn_calc = (Button)findViewById(R.id.dialog_select_btn_calc);
        btn_contract = (Button)findViewById(R.id.dialog_select_btn_contract);


        btn_calc.setOnClickListener(v ->
        {
            callback.onCalc();
            dismiss();
        });
        btn_contract.setOnClickListener(v ->
        {
            callback.onContract();
            dismiss();
        });

    }

}
