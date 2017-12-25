package com.java.proxy;

import com.java.dao.RegionDao;
import com.java.dbc.DatabaseConnection;
import com.java.impl.RegionDaoImpl;

public class RegionDaoProxy implements RegionDao{
	private DatabaseConnection dbc;
	private RegionDao dao;
	public RegionDaoProxy() throws Exception{
		dbc=DatabaseConnection.getInstance();
		dao=new RegionDaoImpl(dbc.getConnection());
	}
	@Override
	public String cityQuery(String str) {
		String result=null;
		try {
			result=dao.cityQuery(str);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public String countyQuery(String str, String city) {
		String result=null;
		try {
			result=dao.countyQuery(str, city);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public String villageQuery(String str, String county) {
		// TODO Auto-generated method stub
		return null;
	}

}
