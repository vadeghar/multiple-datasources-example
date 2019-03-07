package com.multidatasoucres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multidatasoucres.service.EmployeeService;
import com.multidatasoucres.view.EmployeeView;



@RestController
public class TestController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/employee/{countryId}")
	public List<EmployeeView> getEmployeesBuCountry(@PathVariable Long countryId) {
		List<EmployeeView> employeeList = employeeService.getEmployeesByCountry(countryId);
		return employeeList;
	}

}
