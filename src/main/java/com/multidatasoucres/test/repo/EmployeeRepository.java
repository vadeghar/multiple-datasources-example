package com.multidatasoucres.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multidatasoucres.test.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
