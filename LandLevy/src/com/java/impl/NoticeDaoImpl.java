package com.java.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.java.dao.NoticeDao;
import com.java.ov.Notice;
import com.java.ov.SimpleNotice;
import com.java.utils.ResponseUtil;

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
			String city=notice.getCity();
			System.out.println(city+"这是city");
			String county=notice.getCounty();
			String village=notice.getVillage();
			insertAddress(city, county, village);
			String project_adrr=queryAddressID(city, county, village);
			stat=con.prepareStatement(sql);
			stat.setString(1,notice.getProjecttype());
			stat.setString(2,notice.getTime());
			stat.setString(3,notice.getAaply_name());
			stat.setString(4,notice.getReference());
			stat.setString(5,notice.getYear());
			stat.setString(6,project_adrr);
			stat.setString(7,notice.getFilepath());
			stat.setString(8,notice.getStatus());
			stat.setString(9,notice.getUserid());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void insertAddress(String city,String county,String village){
		String sql="insert into `address`(city,county,village) value(?,?,?)";
				try {
					stat=con.prepareStatement(sql);
					stat.setString(1, city);
					stat.setString(2, county);
					stat.setString(3, village);
					stat.execute();
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	private String queryAddressID(String city,String county,String village){
		String adId=null;
		String sql="select id from address where city=? and county=? and village=?";
		try {
			stat=con.prepareStatement(sql);
			stat.setString(1, city);
			stat.setString(2, county);
			stat.setString(3, village);
			ResultSet res=stat.executeQuery();
			if(res.next()){
				adId=res.getString(1);
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return adId;
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
	@Override
	public String getPageNewsList(int pageNo, int pagePerCount,String where) {
		        List<SimpleNotice> newslist =new ArrayList<SimpleNotice>();
		        String result="";
		          String sql = "select apply_name,reference,a.city,a.county,a.village,year,ps.status,time from noticeinfo as n "
		  				+"inner join address as a on a.id=n.project_addr "
						+"inner join publish_status as ps on ps.id=n.status "
						+where+" limit ?,?;";
		              try {
		            	  stat=con.prepareStatement(sql);
				          	stat.setInt(1, (pageNo-1)*pagePerCount);
				          	stat.setInt(2, pagePerCount);
				            ResultSet rs = stat.executeQuery();
				            int totalPage=0;
		                 while(rs.next()){
//		                	 int count = rs.getInt(1);
//		         			 totalPage = count%pagePerCount == 0? count/pagePerCount : count/pagePerCount+1;
		                     String apply=rs.getString("apply_name");
		     				 String reference=rs.getString("reference");
		     				 String city=rs.getString(3);
		     				 String county=rs.getString(4);
		     				 String village=rs.getString(5);
		     				 String address=city+county+village;
		     				 String year=rs.getString("year");
		     				 String status=rs.getString(7);
		     				 SimpleNotice notice=new SimpleNotice(apply, reference, address, year, status);
		     				newslist.add(notice);
		                 }
		              result=  ResponseUtil.createModelWithPageJson(newslist, totalPage);
		                 stat.close();
		             } catch (SQLException e) {
		                 e.printStackTrace();
		             }finally{
		             }
		         return result;
		     }
	
}
