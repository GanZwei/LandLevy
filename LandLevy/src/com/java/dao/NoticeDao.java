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
	
}
