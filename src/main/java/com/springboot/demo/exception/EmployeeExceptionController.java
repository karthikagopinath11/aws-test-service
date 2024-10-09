package com.springboot.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.demo.model.ErrorVo;

@ControllerAdvice
public class EmployeeExceptionController {

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<Object> exception(EmployeeNotFoundException exception) {
		ErrorVo error = new ErrorVo();
		error.setMessage(exception.getErrorMessage());
		error.setStatus(exception.getErrorCode());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmptyInputException.class)
	public ResponseEntity<Object> handleEmptyInputException(EmptyInputException exception) {
		ErrorVo error = new ErrorVo();
		error.setMessage(exception.getErrorMessage());
		error.setStatus(exception.getErrorCode());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	
}
