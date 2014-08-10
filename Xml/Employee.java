package com.fuxi.xml;

import java.util.Date;

public class Employee {

	private Long id;
	private String name;
	private Date birthday;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		String s = "id: " + this.id + ";name : " + this.name;
		return s;
	}
}
