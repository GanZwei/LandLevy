package com.java.ov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleNotice {
	private String apply;
	private String reference;
	private String address;
	private String year;
	private String status;
	
}
