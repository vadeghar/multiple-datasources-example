package com.multidatasoucres.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multidatasoucres.test.model.Employee;
import com.multidatasoucres.test.repo.EmployeeRepository;
import com.multidatasoucres.view.EmployeeView;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<EmployeeView> getEmployeesByCountry(Long countryId) {
		List<Employee> list = employeeRepository.findEmployeesByCountryId(countryId);
		System.out.println("LIST: "+list);
		List<Object[]> list1 = employeeRepository.findAllEmployees(countryId);
		System.out.println("LIST 2: "+list1);
		return null;
	}

}
