package com.company.app.advice;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(WebRestControllerAdvice.class);

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> validationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

		Set<String> messages = new HashSet<>(constraintViolations.size());
		messages.addAll(constraintViolations.stream()
				.map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
						constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
				.collect(Collectors.toList()));

		return new ResponseEntity<String>(String.join(",", messages), HttpStatus.BAD_REQUEST);
	}
	
	
	/*
	 * @ExceptionHandler(value = { ValidationException.class })
	 * 
	 * @ResponseStatus(HttpStatus.BAD_REQUEST) public ResponseEntity<String>
	 * validationException(ValidationException ex) {
	 * 
	 * 
	 * 
	 * return new ResponseEntity<String>(String.join(",", ""),
	 * HttpStatus.BAD_REQUEST); }
	 */
	
	
	@ExceptionHandler(value = { InvalidArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> validationException(InvalidArgumentException ex) {
		
		logger.error("********Invalid Argument Exception Caught*******  {}",ex.getMessage() );
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}