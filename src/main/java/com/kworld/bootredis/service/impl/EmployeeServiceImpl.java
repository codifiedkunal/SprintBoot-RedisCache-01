package com.kworld.bootredis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kworld.bootredis.entity.Employee;
import com.kworld.bootredis.repository.EmployeeRepository;
import com.kworld.bootredis.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployee() {
		return this.employeeRepository.fetchEmployees();
	}

	@Override
	public Employee getEmployee(Long eid) {
		return this.employeeRepository.fetchEmployee(eid);
	}

	@Override
	@Transactional
	public Employee addEmployee(Employee employee) {
		Long eid = this.employeeRepository.insertEmployee(employee);
		employee.setEid(eid);
		
		this.employeeRepository.clearAllEmployeeCache();
		return employee;
	}
	
	@Override
	@CachePut(cacheNames="bootredis.employee.id", key="#employee.eid")
	public Employee updateEmployee(Employee employee) {
		this.employeeRepository.updateEmployee(employee);
		return employee;
	}
}