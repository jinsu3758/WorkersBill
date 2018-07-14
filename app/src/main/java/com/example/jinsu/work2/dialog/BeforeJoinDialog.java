package com.example.jinsu.work2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class BeforeJoinDialog extends Dialog {

    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private Button btn_ok;

    private onBtnCallback callback;

    public BeforeJoinDialog(@NonNull Context context, onBtnCallback onBtnCallback) {
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
        setContentView(R.layout.dialog_before_join);
        btn_ok = (Button)findViewById(R.id.dialog_before_join_btn_ok);

        btn_ok.setOnClickListener(v ->
        {
            dismiss();
        });

    }

}
