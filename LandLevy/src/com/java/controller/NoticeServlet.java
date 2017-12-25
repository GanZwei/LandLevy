package com.java.controller;

import java.io.IOException;
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
		HttpSession session=req.getSession();
		if(path.endsWith("/query")){
			String input=req.getParameter("input-1");
			String str=DaoFactory.getNoticeDaoInstance().queryNotice(input);
			if(str!=null){
			System.out.println(str);
			JsonObject obj=new Gson().fromJson(str, JsonObject.class);
				List<SimpleNotice> list=(ArrayList<SimpleNotice>)new Gson().fromJson(obj.get("data").getAsString(),
					new TypeToken<ArrayList<SimpleNotice>>(){}.getType());
					session.setAttribute("list", list);
					resp.sendRedirect("../pages/project.jsp");
						return;
			}
		}
		
		if(path.endsWith("/querycity")){
			String info=req.getParameter("city");
			System.out.println(info);
			System.out.println("进入了city");
			String str=DaoFactory.getRegionDaoInstance().cityQuery(info);
			if(str!=null){
			JsonObject obj=new Gson().fromJson(str, JsonObject.class);
			List<String> city=(ArrayList<String>)new Gson().fromJson(obj.get("data").getAsString(),
				new TypeToken<ArrayList<String>>(){}.getType());
				session.setAttribute("city", city);
				resp.sendRedirect("www.baidu.com");
				return;
			}
		}
		if(path.endsWith("/querycounty")){
			String info=req.getParameter("county");
			String cityname=req.getParameter("cityname");
			System.out.println("进入了county");
			String str=DaoFactory.getRegionDaoInstance().countyQuery(info,cityname);
			if(str!=null){
			JsonObject obj=new Gson().fromJson(str, JsonObject.class);
			List<String> county=(ArrayList<String>)new Gson().fromJson(obj.get("data").getAsString(),
				new TypeToken<ArrayList<String>>(){}.getType());
				req.setAttribute("county", county);
				 req.getRequestDispatcher("../pages/project.jsp").forward(req, resp);
				return;
			}
		}
	}
}
