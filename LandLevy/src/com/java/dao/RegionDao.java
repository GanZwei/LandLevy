package com.java.dao;

public interface RegionDao {
	/**
	 * 
	 * @param str 查询条件（全部，已发布，未发布）
	 * @return 城市信息
	 */
	String cityQuery(String str);
	/**
	 * 
	 * @param str 查询条件 （全部，已发布，未发布）
	 * @param city 城市名称
	 * @return 县区信息
	 */
	String countyQuery(String city);
	/**
	 * 
	 * @param str 查询条件（全部，已发布，未发布）
	 * @param county 县城名称
	 * @return
	 */
	String villageQuery(String county);
}
