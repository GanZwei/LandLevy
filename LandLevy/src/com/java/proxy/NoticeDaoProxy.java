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
	@Test
	public void test(){
		dao.addNotice(new Notice("单独选址项目","2017-06-05 17:40:00	2017","年度西商村拟征收土地公告","ABC-123456"	,"2016","1","1","http","1"));
//		String info=dao.queryNotice("ABC-123456");
//		System.out.println(info);
	}


}
