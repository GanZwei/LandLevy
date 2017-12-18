package com.java.ov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//工作人员表：id，账号名称，状态，姓名，单位，邮箱，电话，地址，备注，登录时间，登录IP
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String accountname;
	private String name;
	private String mail;
	private String address;
	private String remark;
	private String status;
	private String company;
	private String tel;
	private String logintime;
	private String loginip;
	
}
