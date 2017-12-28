package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.java.factory.DaoFactory;
import com.java.ov.Notice;
import com.java.ov.SimpleNotice;

public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String path=req.getRequestURI();
		if(path.matches("(.+);(.+)")){
			String[] paths=path.split(";");
			path=paths[0];
		}
		HttpSession session=req.getSession();
		System.out.println("进入了servlet:"+path);
		StringBuffer sb = new StringBuffer(1024);
		//查询公告信息
		if(path.endsWith("/query")){
			System.out.println("进入了query");
			String input=req.getParameter("input-1");//取得查询框输入的信息
			System.out.println(input+"111111111");
			 if (input != null && !"".equals(input)) {
				input=input.trim();
				 if(input.matches("\\d+(.+)")){
					 System.out.println("第一个");
					 sb.append("where apply_name='"+input+"' ");
				 }else if (input.matches("[a-zA-Z]+-(\\d+)")) {
					 System.out.println("第二个"+input);
					sb.append("where reference='"+input+"'");
				}  
			}
			 String city=req.getParameter("city");//取得城市框输入的值
			 if (city != null && !"".equals(city)){
				 if(input == null ||"".equals(input)){
				 sb.append("where a.city='"+city+"' ");
				 }else{
					 sb.append(" and a.city='"+city+"' ");
				 }
			 }
			 String county=req.getParameter("county");//取得县区框输入的值
			 if(county != null && !"".equals(county)){
				 sb.append(" and a.county='"+county+"' ");
			 }
			 String village=req.getParameter("village");
			 if(village != null && !"".equals(village)){
				 sb.append(" and a.village='"+village+"' ");
			 }
			 int pageSize = 20;//页面容量
			 int currentPageNo = 1;//当前页码
			 String pageNo = req.getParameter("pageIndex");// 获取当前页码隐藏域1
			 if(pageNo==null){
				 
				 currentPageNo = 1;
			 }else{// 不为空的话，就链接提交给我的当前页码即是用户请求的页码传给我的值，
				 //我赋予给我定的当前页码参数值    
				 
				 currentPageNo = Integer.parseInt(pageNo); 
			 }
				 
			String str=DaoFactory.getNoticeDaoInstance().getPageNewsList(currentPageNo, pageSize, sb.toString());
			if(str!=null){
			System.out.println(str);
			JsonObject obj=new Gson().fromJson(str, JsonObject.class);
				@SuppressWarnings("unchecked")
				List<SimpleNotice> list=(ArrayList<SimpleNotice>)new Gson().fromJson(obj.get("data").getAsString(),
					new TypeToken<ArrayList<SimpleNotice>>(){}.getType());
					session.setAttribute("list", list);
					 resp.sendRedirect("../pages/project.jsp");
						return;
			}
		}
		//查询所有城市
		if(path.endsWith("/querycity")){
			String info=req.getParameter("city");
			System.out.println(info);
			System.out.println("进入了city");
			String str=DaoFactory.getRegionDaoInstance().cityQuery(info);
			if(str!=null){
			JsonObject obj=new Gson().fromJson(str, JsonObject.class);
			@SuppressWarnings("unchecked")
			List<String> city=(ArrayList<String>)new Gson().fromJson(obj.get("data").getAsString(),
				new TypeToken<ArrayList<String>>(){}.getType());
				session.setAttribute("city", city);
				 resp.sendRedirect("../pages/project.jsp");
				return;
			}
		}
		//查询城市对应的县区
		if(path.endsWith("/querycounty")){
			String cityname=req.getParameter("cityname");
			System.out.println("进入了county");
			String str=DaoFactory.getRegionDaoInstance().countyQuery(cityname);
			System.out.println(str);
			if(str!=null){
			JsonObject obj=new Gson().fromJson(str, JsonObject.class);
			PrintWriter out=resp.getWriter();
			@SuppressWarnings("unchecked")
			List<String> county=(ArrayList<String>)new Gson().fromJson(obj.get("data").getAsString(),
				new TypeToken<ArrayList<String>>(){}.getType());
				session.setAttribute("county", county);
				 out.write(str);
			}
		}
		//查询县区所对应的镇级
		if(path.endsWith("/queryvillage")){
			String countyname=req.getParameter("countyname");
			System.out.println("进入了查询镇级页");
			System.out.println(countyname);
			String str=DaoFactory.getRegionDaoInstance().villageQuery(countyname);
			System.out.println(str);
			if(str!=null){
			JsonObject obj=new Gson().fromJson(str, JsonObject.class);
			PrintWriter out=resp.getWriter();
			@SuppressWarnings("unchecked")
			List<String> village=(ArrayList<String>)new Gson().fromJson(obj.get("data").getAsString(),
				new TypeToken<ArrayList<String>>(){}.getType());
				session.setAttribute("village", village);
				out.write(str);
			}
		}
		if(path.endsWith("/addNotice")){
			//constructionType  starTime  applyname  reference  year city  county  village  burg
			String constructionType=req.getParameter("constructionType");
			String starTime=req.getParameter("starTime");
			String applyname=req.getParameter("applyname");
			String reference=req.getParameter("reference");
			String years=req.getParameter("year");
			String city=req.getParameter("city");
			String county=req.getParameter("county");
			String village=req.getParameter("village");
			String burg=req.getParameter("burg");
			String year=years.split("-")[0];
			System.out.println(city+"city是");
			Notice notice=new Notice(constructionType, starTime, applyname, reference, year, city, county, village, burg, "1", null, null);
			DaoFactory.getNoticeDaoInstance().addNotice(notice);
		}
	}
}
