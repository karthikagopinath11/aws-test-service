package com.springboot.demo.service;

import java.util.List;

import com.springboot.demo.model.Employee;

public interface EmployeeService {

	void saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int employeeId);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployeeById(int employeeId);

}
