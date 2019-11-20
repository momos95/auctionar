package com.spideo.mamadou.auctionar.factories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.spideo.mamadou.auctionar.beans.ErrorResponse;
import com.spideo.mamadou.auctionar.enums.ResponseErrorType;
import com.spideo.mamadou.auctionar.mappers.SMapper;


public class ErrorResponseFactory {

	private ErrorResponseFactory(){
		
	}
	
	public static ErrorResponse buildBadRequest(Set<ConstraintViolation<SMapper>> violations){		
		List<String> constarintMsg = new ArrayList<> ();
		violations.forEach (constraint -> constarintMsg.add (constraint.getMessage ()));
		return  new ErrorResponse (new Date (), constarintMsg, ResponseErrorType.BAD_REQUEST);
	}	
	
	public static ErrorResponse buildBadRequest(String message){
		ErrorResponse error = new ErrorResponse(message) ;
		error.setError(ResponseErrorType.BAD_REQUEST);
		return error ;
	}
	public static ErrorResponse buildBadRequest(String message, ResponseErrorType errorType){
		ErrorResponse error = new ErrorResponse(message) ;
		error.setError(errorType);
		return error ;
	}
}
