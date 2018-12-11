package com.kworld.bootredis.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.kworld.bootredis.entity.Employee;
import com.kworld.bootredis.mapper.EmployeeMapper;

@Repository
public class EmployeeRepository {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeRepository.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Cacheable(cacheNames = "bootredis.employee.all")
	public List<Employee> fetchEmployees(){
		log.info("Fetching All Employees");
		
		return this.namedParameterJdbcTemplate
				.query("select id, first_name, last_name, sex, dob, salary from employee ", employeeMapper);	
	}

	public Long insertEmployee(Employee employee) {
		log.info("Adding Employee ");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.namedParameterJdbcTemplate
				.update("insert into employee(first_name, last_name, sex, dob, salary) \r\n" + 
						"values \r\n" + 
						"(:firstName, :lastName, :sex, to_date(:dob, 'dd-MM-YYYY'), :salary)", 
						new MapSqlParameterSource("firstName", employee.getFirstName())
						.addValue("lastName", employee.getLastName())
						.addValue("sex", employee.getSex().getValue())
						.addValue("dob", employee.getDob())
						.addValue("salary", employee.getSalary()), keyHolder);
		return (Long) keyHolder.getKey();
	}

	@Cacheable(cacheNames="bootredis.employee.id", key="#eid")
	public Employee fetchEmployee(Long eid) {
		log.info("Fetching Employee with id : {}", eid);
		List<Employee> employees = this.namedParameterJdbcTemplate.query("select id, first_name, last_name, sex, dob, salary from employee where id = :eid ",
					new MapSqlParameterSource("eid", eid), employeeMapper);
		return CollectionUtils.isEmpty(employees) ? null : employees.get(0);
	}

	public Integer updateEmployee(Employee employee) {
		log.info("Updating Employee with id : {}", employee.getEid());
		return this.namedParameterJdbcTemplate
				.update("update employee set first_name = :firstName, last_name = :lastName, sex = :sex, dob = to_date(:dob, 'dd-MM-yyyy'), salary = :salary where id = :eid ", 
						new MapSqlParameterSource("eid", employee.getEid())
						.addValue("firstName", employee.getFirstName())
						.addValue("lastName", employee.getLastName())
						.addValue("sex", employee.getSex().getValue())
						.addValue("dob", employee.getDob())
						.addValue("salary", employee.getSalary()));
	}
	
	@CacheEvict(cacheNames="bootredis.employee.all")
	public boolean clearAllEmployeeCache() {
		log.info("Clearing Cache For all Employee !!");
		return true;
	}
	
	/*Optionally Below can be used to clear cache
	@Autowired private CacheManager cacheManager;
	public void clearCache(String cacheNameSpEL) {
		cacheManager.getCache(cacheNameSpEL).clear();
	}*/
}
