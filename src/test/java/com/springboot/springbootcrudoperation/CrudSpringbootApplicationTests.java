
package com.springboot.springbootcrudoperation;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.demo.CrudSpringbootApplication;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repository.EmployeeRepository;
import com.springboot.demo.service.EmployeeService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CrudSpringbootApplication.class)
@ActiveProfiles("test")
class CrudSpringbootApplicationTests {

	@Autowired
	EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;

	@Test
	public void getAllEmployeesTest() {
		List<Employee> list = new ArrayList<>();
		Employee e1 = new Employee(1, "Madhav", "Aluva");
		Employee e2 = new Employee(2, "Unni", "Aluva");
		Employee e3 = new Employee(3, "Karthika", "Aluva");

		list.add(e1);
		list.add(e2);
		list.add(e3);

		Mockito.when(employeeRepository.findAll()).thenReturn(list);

		// test
		List<Employee> empList = employeeService.getAllEmployees();

		assertEquals(3, empList.size());
		verify(employeeRepository, times(1)).findAll();
	}
	
	@Test
	public void getEmployeeByIdTest() {
		
		Optional<Employee> e = Optional.of(new Employee(1,"Madhav","Aluva"));		
		Mockito.when(employeeRepository.findById(1)).thenReturn(e);
		
		Employee e1 = employeeService.getEmployeeById(1);

		assertEquals("Madhav", e1.getEmployeeName());
		assertEquals("Aluva", e1.getEmployeeCity());
		assertEquals(1, e1.getEmployeeId());
	}
	
	@Test
	public void saveEmployeeTest() {
		Employee emp = new Employee(1, "Madhav", "Aluva");
		employeeService.saveEmployee(emp);
		verify(employeeRepository, times(1)).save(emp);
	}

	@Test
	public void updateEmployeeTest() {
		Employee emp = new Employee(1, "Madhav", "Athani");
		employeeService.updateEmployee(emp);
		verify(employeeRepository, times(1)).save(emp);
	}

	
}
