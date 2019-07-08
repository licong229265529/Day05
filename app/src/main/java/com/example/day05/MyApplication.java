package com.example.day05;

import android.app.Application;

import com.example.day05.dao.DaoMaster;
import com.example.day05.dao.DaoSession;

public class MyApplication extends Application {

    private static DaoSession daoSession;

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "title.db");
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
    }
}
