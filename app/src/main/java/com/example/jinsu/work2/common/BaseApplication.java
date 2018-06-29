package com.example.jinsu.work2.common;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.jinsu.work2.repositories.EmployerRepository;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {
    public static boolean DEBUG = false;


    @Override
    public void onCreate()
    {
        super.onCreate();
        EmployerRepository.getInstance().Connect();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);

//        this.DEBUG
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
