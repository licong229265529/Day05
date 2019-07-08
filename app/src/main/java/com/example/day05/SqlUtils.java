package com.example.day05;

import com.example.day05.bean.TitleBean;
import com.example.day05.dao.DaoSession;
import com.example.day05.dao.TitleBeanDao;

import java.util.List;

public class SqlUtils {
    private static DaoSession daoSession = MyApplication.getDaoSession();
    public static void initsert(TitleBean titleBean){
        List<TitleBean> titleBeans = queryItem(titleBean.get_id());
        if (titleBeans.size()==0){
            daoSession.insert(titleBean);
        }
    }
    public static void delete(TitleBean titleBean){
        List<TitleBean> titleBeans = queryItem(titleBean.get_id());
        if (titleBeans.size()>0&&titleBeans!=null){
            daoSession.delete(titleBean);
        }
    }
    public static List<TitleBean> queryItem(String _id){
        List<TitleBean> list = daoSession.queryBuilder(TitleBean.class)
                .where(TitleBeanDao.Properties._id.eq(_id))
                .build()
                .list();
        return list;
    }
    public static List<TitleBean> queryAll(){
        return daoSession.loadAll(TitleBean.class);
    }
}
