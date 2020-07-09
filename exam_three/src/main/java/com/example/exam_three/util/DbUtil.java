package com.example.exam_three.util;

import android.util.Log;

import com.example.exam_three.bean.DaoMaster;
import com.example.exam_three.bean.DaoSession;
import com.example.exam_three.bean.UserBean;
import com.example.exam_three.bean.UserBeanDao;

import java.util.List;

public class DbUtil {
    private static volatile DbUtil sDbUtil;
    private final UserBeanDao mUserBeanDao;
    private static final String TAG = "DbUtil";

    public static DbUtil getDbUtil() {
        if (sDbUtil == null) {
            synchronized (DbUtil.class) {
                if (sDbUtil == null) {
                    sDbUtil = new DbUtil();
                }
            }
        }
        return sDbUtil;
    }

    private DbUtil() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getApp(), "user.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        mUserBeanDao = daoSession.getUserBeanDao();
    }

    public void inserts(UserBean bean) {
        long insert = mUserBeanDao.insert(bean);
        if (insert >= 0) {
            Log.d(TAG, "inserts: " + "添加成功");
        } else {
            Log.d(TAG, "inserts: " + "添加失败");
        }
    }

    public UserBean queryItem(String name) {
        UserBean unique = mUserBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq(name)).unique();
        if (unique == null) return null;
        else return unique;
    }

    public List<UserBean> queryAll() {
        List<UserBean> list = mUserBeanDao.queryBuilder().list();
        return list;
    }

    public void deleteItem(UserBean bean) {
        mUserBeanDao.delete(bean);
    }

    public void update(UserBean bean) {
        mUserBeanDao.update(bean);
    }
}
