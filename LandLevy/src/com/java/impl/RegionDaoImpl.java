package com.java.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.dao.RegionDao;
import com.java.utils.ResponseUtil;

public class RegionDaoImpl implements RegionDao{
	private Connection con;
	private PreparedStatement stat;
	public RegionDaoImpl(Connection con){
		this.con=con;
	}
	private int isCondition(String str){
		if("全部".equals(str)||"所有".equals(str)){
			return 1;
		}
		if("已发布".equals(str)){
			return 2;
		}
		if("未发布".equals(str)){
			return 3;
		}
		return 0;
	}
	@Override
	public String cityQuery(String str){
		String result=null;
		List<String> list=new ArrayList<String>();
		if(isCondition(str)==1){
		String sql="select id,name from city";
			try {
				stat=con.prepareStatement(sql);
				ResultSet res=stat.executeQuery();
				while(res.next()){
					String name=res.getString("name");
					list.add(name);
				}
				result=ResponseUtil.createModelJson(list);
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(isCondition(str)==2){
			String sql="select name from city where=?";
			
			}
		if(isCondition(str)==3){
			String sql="select name from city where=?";
			
			}
		return result;
	}
	@Override
	public String countyQuery( String city) {
		String sql="select name from county where cityid=?";
		String id=queryCityidByname(city);
		List<String> list=new ArrayList<String>();
		String result=null;
		try {
			stat=con.prepareStatement(sql);
			stat.setString(1, id);
			ResultSet res=stat.executeQuery();
			while(res.next()){
				String countyname=res.getString("name");
				list.add(countyname);
			}
			result=ResponseUtil.createModelJson(list);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	private String queryCityidByname(String city){
		String sql="select id from city where name=?";
		String cityid=null;
		try {
			stat=con.prepareStatement(sql);
			stat.setString(1, city);
			ResultSet res=stat.executeQuery();
			if(res.next()){
				cityid=res.getString(1);
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return cityid;
	}
	@Override
	public String villageQuery(String county) {
		String sql="select name from village where countyid=?";
		String id=queryCountyidByname(county);
		List<String> list=new ArrayList<String>();
		String result=null;
		try {
			stat=con.prepareStatement(sql);
			stat.setString(1, id);
			ResultSet res=stat.executeQuery();
			while(res.next()){
				String countyname=res.getString("name");
				list.add(countyname);
			}
			result=ResponseUtil.createModelJson(list);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	private String queryCountyidByname(String county){
		String sql="select id from county where name=?";
		String countyid=null;
		try {
			stat=con.prepareStatement(sql);
			stat.setString(1, county);
			ResultSet res=stat.executeQuery();
			if(res.next()){
				countyid=res.getString(1);
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return countyid;
	}
}
