package com.kusoduck.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountRestExceptionHandler {

	// Add an exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<AccountErrorResponse> handleException(AccountNotFoundException exception){
		
		AccountErrorResponse error = new AccountErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// Add another exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<AccountErrorResponse> handleException(Exception exception){
		
		AccountErrorResponse error = new AccountErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
