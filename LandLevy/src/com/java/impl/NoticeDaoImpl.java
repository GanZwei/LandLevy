package com.java.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.java.dao.NoticeDao;
import com.java.ov.Notice;
import com.java.ov.SimpleNotice;
import com.java.utils.ResponseUtil;

import sun.font.CreatedFontTracker;

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
	public String queryNotice(String str){
		//sql查询语句
		String sql="select apply_name,reference,a.city,a.county,a.village,year,ps.status from noticeinfo as n "
				+"inner join address as a on a.id=n.project_addr "
				+"inner join publish_status as ps on ps.id=n.status "
				+ "where reference=?;";
		String sql1="select apply_name,reference,a.city,a.county,a.village,year,ps.status from noticeinfo as n "
				+"inner join address as a on a.id=n.project_addr "
				+"inner join publish_status as ps on ps.id=n.status "
				+"where apply_name=?;";
		String info=null;
		try {
			if(isAccord(str)==1){
				stat=con.prepareStatement(sql);
				stat.setString(1, str);
			}else if(isAccord(str)==2){
				stat=con.prepareStatement(sql1);
				stat.setString(1, str);
			}
			if(isAccord(str)!=0){
			ResultSet result=stat.executeQuery();
			List<SimpleNotice> sm=new ArrayList<SimpleNotice>();
			while(result.next()){
				String apply=result.getString("apply_name");
				String reference=result.getString("reference");
				String city=result.getString(3);
				String county=result.getString(4);
				String village=result.getString(5);
				String address=city+county+village;
				String year=result.getString("year");
				String status=result.getString(7);
				SimpleNotice notice=new SimpleNotice(apply, reference, address, year, status);
				sm.add(notice);
				}
			info=ResponseUtil.createModelJson(sm);
			stat.close();
			}
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseUtil.createErrorJson("数据库错误");
		}
	}
	private int isAccord(String str){
		String sql="select count(*) from noticeinfo where reference=?";
		try {
			PreparedStatement prepar=con.prepareStatement(sql);
			prepar.setString(1, str);
			ResultSet result=prepar.executeQuery();
			if(result.next()){
				int count =result.getInt("count(*)");
				if(count!=0){
				prepar.close();
				return 1;
				}
			}
			String sql1="select count(*) from noticeinfo where apply_name=?";
			PreparedStatement pre=con.prepareStatement(sql1);
			pre.setString(1, str);
			ResultSet res=pre.executeQuery();
			if(res.next()){
				int count =res.getInt("count(*)");
				if(count!=0){
				pre.close();
				return 2;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
