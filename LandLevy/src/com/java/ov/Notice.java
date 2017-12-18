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
	private String project_addr;
}
