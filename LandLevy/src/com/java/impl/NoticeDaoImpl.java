package com.java.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.JsonObject;
import com.java.dao.NoticeDao;
import com.java.ov.Notice;

public class NoticeDaoImpl implements NoticeDao{
	private Connection con;
	private PreparedStatement stat=null;
	public NoticeDaoImpl(Connection con){
		this.con=con;
	}
	@Override
	public void addNotice(Notice notice) {
		String sql="insert into noticeinfo(projecttype,time,apply_name,reference,year,project_addr,file,status,userid) "
				+"value(?,?,?,?,?,?,?,?,?)";
		try {
			stat=con.prepareStatement(sql);
			stat.setString(1,notice.getProjecttype());
			stat.setString(2,notice.getTime());
			stat.setString(3,notice.getAaply_name());
			stat.setString(4,notice.getReference());
			stat.setString(5,notice.getYear());
			stat.setString(6,notice.getProject_addr());
			stat.setString(7,notice.getFilepath());
			stat.setString(8,notice.getStatus());
			stat.setString(9,notice.getUserid());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String queryNotice(String str) {
		String sql="select apply_name,reference,project_addr,time from noticeinfo where ?=?";
		try {
			stat=con.prepareStatement(sql);
			stat.setString(2, str);
			JsonObject root=null;
			if(isAccord(str)==1){
				root=new JsonObject();
				stat.setString(1, "reference");
			}else if(isAccord(str)==2){
				stat.setString(1, "apply_name");
			}
			ResultSet result=stat.executeQuery();	
			while(result.next()){
				String apply=result.getString("apply_name");
				String reference=result.getString("reference");
				String project_addr=result.getString("project_addr");
				String time=result.getString("time");
				root.addProperty("apply", apply);
				root.addProperty("reference", reference);
				root.addProperty("project_addr", project_addr);
				root.addProperty("time", time);
				return root.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

			return null;
	}
	private int isAccord(String str){
		String sql="select ? from noticeinfo where ?=?";
		try {
			stat=con.prepareStatement(sql);
			stat.setString(1, "reference");
			stat.setString(2, "reference");
			stat.setString(3, str);
			ResultSet result=stat.executeQuery();
			if(result.next()){
				return 1;
			}else{
				stat.setString(1, "apply_name");
				stat.setString(2, "apply_name");
				result=stat.executeQuery();
				if(result.next()){
					return 2;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
