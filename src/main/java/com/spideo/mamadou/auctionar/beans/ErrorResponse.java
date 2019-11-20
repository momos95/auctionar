package com.spideo.mamadou.auctionar.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.spideo.mamadou.auctionar.enums.ResponseErrorType;

/**
 * 
 * @author mamadou
 * This bean is used to show error details
 *
 */
public class ErrorResponse {
	
	/*
	 * When it happens exactly
	 */
    private Date timestamp;
    
    /**
     * List of error messages
     */
    private List<String> messages;
    
    /**
     * Kind of Error
     */
    private ResponseErrorType error;

    public ErrorResponse(){
    	super() ;
    	messages = new ArrayList<>() ;
    }
    
    public ErrorResponse(String message){
    	this() ;
    	timestamp = new Date() ;    	
    	messages.add(message) ;
    }
    
    public ErrorResponse(Date timestamp, List<String> message,ResponseErrorType error) {
      super();
      this.timestamp = timestamp;
      this.messages = message;
      this.error = error;
    }

    public Date getTimestamp () {
        return timestamp;
    }

    public void setTimestamp (Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getMessage () {
        return messages;
    }

    public void setMessage (List<String> message) {
        this.messages = message;
    }

    public ResponseErrorType getError () {
        return error;
    }

    public void setError (ResponseErrorType error) {
        this.error = error;
    }

    @Override
    public String toString () {
        return "ErrorResponse [timestamp=" + timestamp + ", message=" + messages + ", error=" + error + "]";
    }

}
