package com.multidatasoucres.view;

import java.io.Serializable;

public class EmployeeView implements Serializable{
	
	private Long id;
	private Integer age;
	private String name;
	private String country;
	
	@SuppressWarnings("unused")
	private EmployeeView() {}
	
	public EmployeeView(Long id, Integer age, String name, String country) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.country = country;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
