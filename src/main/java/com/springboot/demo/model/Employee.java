package com.springboot.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Column
	@Id
	private Integer employeeId;
	
	@Column
	private String employeeName;	
	
	@Column
	private String employeeCity;

	
	

}
