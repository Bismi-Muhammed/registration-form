package com.employee.registrationform.Advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.employee.registrationform.exception.EmptyInputException;

@ControllerAdvice
public class GlobalExceptionHandleController extends ResponseEntityExceptionHandler {

	/**
	 * To handle exceptions occured whenever the @ input fields are empty @
	 */

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException) {
		return new ResponseEntity<String>("Input field is empty,please look into it", HttpStatus.BAD_REQUEST);
	}

	/**
	 * To handle the exceptions occured whenever a request returns an empty list
	 * 
	 */

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException NoSuchElementException) {
		return new ResponseEntity<String>("No value present in the database", HttpStatus.NOT_FOUND);
	}

	/**
	 * To handle exception occured due to wrong request method
	 * 
	 */

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("please change your http method type", HttpStatus.NOT_FOUND);
	}

}
