package com.java.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			if(isAccord(str)==1){
				stat.setString(1, "reference");
				ResultSet result=stat.executeQuery();	
				
			}else if(isAccord(str)==2){
				
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
