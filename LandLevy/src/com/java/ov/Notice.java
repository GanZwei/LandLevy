package com.java.ov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
	private String projecttype;
	private String time;
	private String aaply_name;
	private String reference;
	private String year;
	private String city;
	private String county;
	private String village;
	private String burg;
	private String status;
	private String filepath;
	private String userid;
}
