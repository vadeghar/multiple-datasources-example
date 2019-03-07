package com.multidatasoucres.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multidatasoucres.test.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query(value="select new Employee(e.id, e.age, e.name, e.countryId, c.countryIsoCode) "
			+ "from com.multidatasoucres.test.model.Employee e "
			+ "join com.multidatasoucres.store.model.Country c on c.id = e.countryId "
			+ "where e.countryId = :countryId ")
	public List<Employee> findEmployeesByCountryId(@Param("countryId") Long countryId);
	
	
	@Query(value="select * from test.employee e " + 
			"join store1.country c on c.country_id=e.country_id " + 
			"where e.country_id = :countryId ", nativeQuery = true)
	public List<Object[]> findAllEmployees(@Param("countryId") Long countryId);

}
