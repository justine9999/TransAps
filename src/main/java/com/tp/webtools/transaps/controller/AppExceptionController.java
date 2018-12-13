package com.tp.webtools.transaps.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tp.webtools.transaps.exception.AppConflictException;
import com.tp.webtools.transaps.exception.AppNotFoundException;
import com.tp.webtools.transaps.exception.InternalServerException;

@ControllerAdvice
public class AppExceptionController {

	@ExceptionHandler (value = AppNotFoundException.class)
	public ResponseEntity<Object> exception(AppNotFoundException ex){		
		if(ex.getApp_title().equals("")) {
			return new ResponseEntity<>("Unable to '" + ex.getRoot_operation() + "'.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Unable to '" + ex.getRoot_operation() + "'. The App with title '" + ex.getApp_title() + "' does not exist.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler (value = AppConflictException.class)
	public ResponseEntity<Object> exception(AppConflictException ex){	
		return new ResponseEntity<>("Unable to '" + ex.getRoot_operation() + "'. The App with title '" + ex.getApp_title() + "' already exist.", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler (value = InternalServerException.class)
	public ResponseEntity<Object> exception(InternalServerException ex){		
		return new ResponseEntity<>("Unable to '" + ex.getRoot_operation() + "'. Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
