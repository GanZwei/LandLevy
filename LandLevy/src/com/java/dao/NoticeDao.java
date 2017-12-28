package com.java.dao;

import com.java.ov.Notice;

public interface NoticeDao {
	/**
	 * 添加公告
	 * @param notice
	 */
	void addNotice(Notice notice);
	/**
	 *
	 * @param str 查询条件（批准文号或者项目名称）
	 * @return 查询信息
	 */
	String queryNotice(String str);
	/**
	 * 
	 * @param pageNo 当前页
	 * @param pagePerCount 每页条数
	 * @param where 查询条件（不固定）
	 * @return
	 */
	String getPageNewsList(int pageNo, int pagePerCount,String where);
}
