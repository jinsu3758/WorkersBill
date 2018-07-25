package com.example.jinsu.work2.common;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.jinsu.work2.network.ApiManager;
import com.example.jinsu.work2.network.ApiService;
import com.example.jinsu.work2.network.model.Join;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    public static boolean DEBUG = false;
    public static ApiService mApiService;
    public static BaseApplication mContext;
    public static Join join;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = this;
        Constants.preferences = getSharedPreferences(Constants.PREF_KEY,MODE_PRIVATE);
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        join = new Join();

        mApiService = ApiManager.rebuildAdapter(ApiManager.HOST);
//        this.DEBUG
    }

    public static Context get() {
        return mContext;
    }

    /**
     * get Debug Mode
     *
     * @param context BaseApplication
     * @return 디버그 모드
     */
    private boolean isDebuggable(Context context)
    {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
        }

        return debuggable;
    }
}
