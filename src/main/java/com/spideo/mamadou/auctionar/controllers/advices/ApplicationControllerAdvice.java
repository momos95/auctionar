package com.spideo.mamadou.auctionar.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.spideo.mamadou.auctionar.beans.ErrorResponse;
import com.spideo.mamadou.auctionar.factories.ErrorResponseFactory;

/**
 * 
 * @author Mamadou
 *
 */
@ControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(JsonParseException.class)
	public final ResponseEntity<ErrorResponse> handleBadFormatJson(JsonParseException ex){
		return new ResponseEntity<>(ErrorResponseFactory.buildBadRequest("Please check that the JSON Format is correct"), HttpStatus.BAD_REQUEST) ;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
		return new ResponseEntity<>(ErrorResponseFactory.buildBadRequest(ex.getMessage()), HttpStatus.BAD_REQUEST) ;
	}
}
