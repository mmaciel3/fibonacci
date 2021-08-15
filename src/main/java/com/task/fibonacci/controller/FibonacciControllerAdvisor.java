package com.task.fibonacci.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FibonacciControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest webRequest) {
		System.out.println("Illegal Argument Exception was thrown: " + ex);

		Map<String, Object> body = new HashMap<>();
		body.put("message", "n must be a non-negative integer");

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Missing required parameter was thrown: " + ex);

		Map<String, Object> body = new HashMap<>();
		body.put("message", "parameter " + ex.getParameterName() + " is required");

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}