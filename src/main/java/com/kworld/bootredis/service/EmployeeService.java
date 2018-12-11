package com.kworld.bootredis.service;

import java.util.List;

import com.kworld.bootredis.entity.Employee;

public interface EmployeeService {
	/*
	 * Gets All Employee
	 */
	public List<Employee> getAllEmployee();
	
	/*
	 * Gets Employee based on eid
	 */
	public Employee getEmployee(Long eid);
	
	/*
	 * Adds new Employee and returs it with identifier
	 */
	public Employee addEmployee(Employee employee);

	/*
	 * Updates Existing Employee and returs it with identifier
	 */
	public Employee updateEmployee(Employee employee);

}
