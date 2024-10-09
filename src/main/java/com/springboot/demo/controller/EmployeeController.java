package com.springboot.demo.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.exception.EmployeeNotFoundException;
import com.springboot.demo.exception.EmptyInputException;
import com.springboot.demo.model.Employee;
import com.springboot.demo.service.EmployeeService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/employee")
@Log4j2
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	//Save employee
	@PostMapping("/saveEmployee")
	@Operation(summary = "Saves the employee details", description = "Returns true if successfully saved")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully saved"),
	})
	private ResponseEntity<Boolean> createEmployee(@RequestBody Employee employee)  {
		log.debug("Starting saveEmployee method");
		try {
			employeeService.saveEmployee(employee);
		}catch (EmptyInputException e) {
			throw e	;	
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	//Get all
	@Operation(summary = "Get all employees", description = "Retrieves all employee details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved"),
	})
	@GetMapping("/getAllEmployees")
	// @RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	//Get by employee id
	@Operation(summary = "Get employees by id", description = "Retrieves the employee details based on id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved"),
			@ApiResponse(responseCode = "404", description = "Employee id not found"),

	})
	@GetMapping("/getAllEmployeesById/{employeeId}")
	private ResponseEntity<Employee> getEmployeeById(@RequestParam("employeeId") int employeeId)
			throws EmployeeNotFoundException {
		Employee e = new Employee();
		try {
			e = employeeService.getEmployeeById(employeeId);
		} catch (EmployeeNotFoundException ex) {
			throw ex;
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}

	//Update
	@Operation(summary = "Update employee", description = "Updates the employee details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully updated"),
	})
	@PutMapping("/updateEmployee")
	private void updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
	}

	//Delete
	@Operation(summary = "Delete employee by id", description = "Delete an employee by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully deleted"),
	})
	@DeleteMapping("/deleteEmployee/{employeeId}")
	private void deleteEmployee(@PathVariable("employeeId") int employeeId) {
		employeeService.deleteEmployeeById(employeeId);
	}

}
