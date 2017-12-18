package com.java.factory;

import com.java.dao.NoticeDao;
import com.java.proxy.NoticeDaoProxy;

public class NoticeDaoFactory {
	public static NoticeDao getUserDaoInstance(){
		NoticeDao noticedao=null;
		try {
			noticedao=new NoticeDaoProxy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticedao;
	}
}
