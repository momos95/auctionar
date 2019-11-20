package com.spideo.mamadou.auctionar.beans;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spideo.mamadou.auctionar.entities.SEntity;

/**
 * 
 * @author Mamadou
 * General Bean for controller returns. It contains entity returned by repositories actions and error or warning.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SimpleApiPrettyResponse {

	private SEntity entity ;
	private Date timestamp ;
	private HttpStatus status ;
	private ErrorResponse error ;	
	
	public SimpleApiPrettyResponse(){
		timestamp = new Date() ;
	}
	
	public SimpleApiPrettyResponse(ErrorResponse error){
		this() ;
		this.error = error ;
	}
	
	public SimpleApiPrettyResponse(SEntity entity){
		this() ;
		this.entity = entity ;
	}

	public ErrorResponse getError() {
		return error;
	}

	public void setError(ErrorResponse error) {
		this.error = error;
	}

	public SEntity getEntity() {
		return entity;
	}

	public void setEntity(SEntity entity) {
		this.entity = entity;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}	
		
	
}
