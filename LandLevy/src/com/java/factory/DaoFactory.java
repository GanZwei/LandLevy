package com.java.factory;

import com.java.dao.NoticeDao;
import com.java.dao.RegionDao;
import com.java.proxy.NoticeDaoProxy;
import com.java.proxy.RegionDaoProxy;

public class DaoFactory {
	public static NoticeDao getNoticeDaoInstance(){
		NoticeDao noticedao=null;
		try {
			noticedao=new NoticeDaoProxy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticedao;
	}
	public static RegionDao getRegionDaoInstance(){
		RegionDao regiondao=null;
		try {
			regiondao=new RegionDaoProxy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regiondao;
	}
}
