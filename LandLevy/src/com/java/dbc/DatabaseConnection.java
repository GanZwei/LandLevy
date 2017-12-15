package com.java.dbc;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseConnection {
	private Connection con=null;
	private DataSource ds;
	public static DatabaseConnection instance;
	private DatabaseConnection(){
		Context context;
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/land board"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static DatabaseConnection getInstance(){
		if(instance==null){
			try {
				instance=new DatabaseConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	public Connection getConnection() throws Exception{
			con=ds.getConnection();
			return con;
	}
	public void close() throws Exception{
		if(con!=null){
			con.close();
		}
	}
}
