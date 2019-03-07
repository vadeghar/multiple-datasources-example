package com.multidatasoucres.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EMPLOYEE", schema="test")
public class Employee {
	
	public Employee() {}
	
	public Employee(Long id, Integer age, String name, Long countryId, String country) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.countryId = countryId;
		this.country = country;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Integer age;
	@Column
	private String name;
	
	@Column(name="COUNTRY_ID")
	private Long countryId;
	
	@Transient
	private String country;
	
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
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
