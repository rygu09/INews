package com.ustc.gry.inews.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.greendao.DaoMaster;
import com.ustc.gry.inews.greendao.DaoSession;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/3
 */

public class App extends Application{

    public static final String DB_NAME = "NewsChannelTable";

    public static final String APP_NAME = "INews";

    private DaoSession mDaoSession;

    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = this;
        setupDatabase();
    }

    private void setupDatabase() {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        mDaoSession = daoMaster.newSession();
        Logger.i("setupDatabase");
    }

    /**
     * 获取dao会话
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return sApplicationContext;
    }
}
