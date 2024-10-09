package com.springboot.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.exception.EmployeeNotFoundException;
import com.springboot.demo.exception.EmptyInputException;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.EmployeeRepository;
import com.springboot.demo.service.EmployeeService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {
		if(employee == null || 
				(employee!=null && (employee.getEmployeeName().isBlank() || 
				employee.getEmployeeCity().isBlank() || 
				employee.getEmployeeId().equals(null)) )){
			throw new EmptyInputException( "400","Input fields are empty");
		}
		employeeRepository.save(employee);

	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(e -> employees.add(e));
		if(employees.isEmpty()) {
			log.error("Failed to fetch employee details");
			throw new EmployeeNotFoundException("404","Employees not found");
		}
		return employees;

	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee e = new Employee();
		try {
			e = employeeRepository.findById(employeeId).get();
		} catch (Exception ex) {
			log.error("Failed to fetch employee details");
			log.error("Employee id not found");
			throw new EmployeeNotFoundException("404","Employee id not found");
		}
		return e;

	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		employeeRepository.deleteById(employeeId);

	}

}
