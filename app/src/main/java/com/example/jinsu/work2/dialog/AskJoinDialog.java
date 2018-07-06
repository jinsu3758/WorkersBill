package com.example.jinsu.work2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.viewmodel.MainViewModel;
import com.example.jinsu.work2.viewmodel.VIewModelFactory;

public class AskJoinDialog extends Dialog{

    private MainViewModel mainViewModel;
    private VIewModelFactory vIewModelFactory;
    private Button btn_cancel;
    private Button btn_join;

    private onBtnCallback callback;

    public AskJoinDialog(@NonNull Context context, onBtnCallback callback) {
        super(context);
        this.callback = callback;
    }

    public interface onBtnCallback
    {
        void onSave(String name);
        void onWrite();
    }

    private void init()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ask_join);
        btn_cancel = (Button)findViewById(R.id.dialog_ask_join_btn_cancel);
        btn_join = (Button)findViewById(R.id.dialog_ask_join_btn_join);

        btn_cancel.setOnClickListener(v ->
        {
            dismiss();
        });
        btn_join.setOnClickListener(v ->
        {
            dismiss();
        });

    }
}
