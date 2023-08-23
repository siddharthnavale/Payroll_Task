package com.recruitment_portal.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public Map<String,String> handleValidationException(BindException ex)
	{
		Map<String,String> errors= new HashMap<>();
		ex.getBindingResult().getAllErrors().stream().forEach((e)->{
			String field = ((FieldError)e).getField();
			String defaultMessage = e.getDefaultMessage();
			errors.put(field, defaultMessage);	
		});
		return errors;
		}
	
	
}


