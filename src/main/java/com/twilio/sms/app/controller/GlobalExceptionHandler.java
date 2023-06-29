package com.twilio.sms.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.twilio.sms.app.handler.ErrorHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorHandler> illegalArgumentException(
             Exception ex, 
             HttpServletRequest request){
        
		LOG.error("Exception : {}  for {} ", ex.getLocalizedMessage(), request.getRequestURI());
        
        return new ResponseEntity<>(
        	new ErrorHandler(
        			ex.getLocalizedMessage(),
        			HttpStatus.BAD_REQUEST.toString(),
        			request.getRequestURI(),
        			request.getMethod(),
        			"Invalid Request"), 
        	HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorHandler> genericException(
             Exception ex, 
             HttpServletRequest request){
        
		LOG.error("Exception : {}  for {} ", ex.getLocalizedMessage(), request.getRequestURI());
        
        return new ResponseEntity<>(
        	new ErrorHandler(
        			ex.getLocalizedMessage(),
        			HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        			request.getRequestURI(),
        			request.getMethod(),
        			"Could not process request!!!"), 
        	HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
