package com.example.jinsu.work2.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.jinsu.work2.R;

import org.jsoup.helper.StringUtil;

public class ParentActivity extends AppCompatActivity {

    protected final String TAG                      = getClass().getSimpleName();
    public static final int LOG_LOGCAT              = 1;
    public static final int LOG_LOGCAT_TOAST        = 2;
    public static final int LOG_LOGCAT_TOAST_DLG    = 3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 기본 로그레벨은 Logcat 표시
     */
    public void showLog(final String json) {
        String logData = json;
        if(StringUtil.isBlank(logData)) {
            logData = "";
        }
        Log.i(TAG, logData);
    }

    public void showLog(final int logLevel, final String json) {
        String logData = json;
        if(StringUtil.isBlank(logData)) {
            logData = "";
        }
        if(LOG_LOGCAT <= logLevel) {
            Log.i(TAG, logData);
        }
        if(LOG_LOGCAT_TOAST <= logLevel) {
            Toast.makeText(this, logData, Toast.LENGTH_LONG).show();
        }
        if(LOG_LOGCAT_TOAST_DLG <= logLevel) {
            String finalLogData = logData;
            new Handler().postDelayed(() -> {
                if(isFinishing()) {
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ParentActivity.this);
                builder.setTitle(R.string.confirm_yes);
                builder.setMessage(finalLogData);
                builder.setPositiveButton(R.string.confirm_yes,null);
                builder.show();
            },2000);

        }
    }

}
