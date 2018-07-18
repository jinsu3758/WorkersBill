package com.example.jinsu.work2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jinsu.work2.R;
import com.example.jinsu.work2.common.Constants;


public class AddressActivity extends AppCompatActivity{

    private WebView browser;

    class MyJavaScriptInterface
    {
        @JavascriptInterface
        public void processDATA(String data){
            Log.v("태그", "여기까지14");
            // Bundle extra = new Bundle();
            postAddr(data);

        }
    }

    public void postAddr(String data)
    {
        Intent intent = new Intent(this,InputInfoActivity.class);
        intent.putExtra("data", data);
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.webview_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        browser = (WebView) findViewById(R.id.webView);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                browser.loadUrl("javascript:sample2_execDaumPostcode();");
                Log.v("태그", "여기까지5");
            }
        });

        browser.loadUrl(Constants.BASE_URL +"/v1/tools/kakao-address-page");
        Log.v("태그", "여기까지55");
        Log.v("태그", "여기까지15");

    }


}
