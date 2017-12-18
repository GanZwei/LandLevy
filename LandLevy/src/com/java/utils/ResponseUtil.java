package com.java.utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ResponseUtil {
	
	public static String createStatusJson(boolean status){
		JsonObject obj = new JsonObject();
		obj.addProperty("status", status);
		return obj.toString();
	}
	
	public static String createErrorJson(String message){
		JsonObject obj = new JsonObject();
		obj.addProperty("status", false);
		obj.addProperty("data", message);
		return obj.toString();
	}
	
	public static String createModelJson(Object o){
		String json = new Gson().toJson(o);
		
		JsonObject obj = new JsonObject();
		obj.addProperty("status", true);
		obj.addProperty("data", json);
		return obj.toString();
	}
}
