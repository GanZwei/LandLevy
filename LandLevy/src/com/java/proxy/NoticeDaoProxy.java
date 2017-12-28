package com.java.proxy;

import org.junit.Test;

import com.java.dao.NoticeDao;
import com.java.dbc.DatabaseConnection;
import com.java.impl.NoticeDaoImpl;
import com.java.ov.Notice;

public class NoticeDaoProxy implements NoticeDao{
	private DatabaseConnection dbc;
	private NoticeDao dao=null;
	
	public NoticeDaoProxy() throws Exception{
		dbc=DatabaseConnection.getInstance();
		dao=new NoticeDaoImpl(dbc.getConnection());
	}
	
	@Override
	public void addNotice(Notice notice) {
		dao.addNotice(notice);
		try {
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String queryNotice(String str) {
		String info=dao.queryNotice(str);
		try {
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	@Override
	public String getPageNewsList(int pageNo, int pagePerCount, String where) {
		String info=null;
		try {
			info=dao.getPageNewsList(pageNo, pagePerCount, where);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}


}
