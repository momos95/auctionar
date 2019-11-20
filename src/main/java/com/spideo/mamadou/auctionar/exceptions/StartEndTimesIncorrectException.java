package com.spideo.mamadou.auctionar.exceptions;

import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
/**
 * 
 * @author Mamadou
 *
 */
public class StartEndTimesIncorrectException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StartEndTimesIncorrectException(){
		super(ExceptionMessages.AUCTION_DATES_INCORRECT) ;
	}

}
