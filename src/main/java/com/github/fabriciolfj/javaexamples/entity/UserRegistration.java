package com.github.fabriciolfj.javaexamples.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistration {

	private	String firstName;
	private	String lastName;
	private String company;
	private	String address;
	private	String city;
	private	String state;
	private	String zip;
	private	String county;
	private	String url;
	private	String phoneNumber;
	private	String fax;
}
