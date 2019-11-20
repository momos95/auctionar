package com.spideo.mamadou.auctionar.exceptions;

import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
/**
 * 
 * @author Mamadou
 *
 */
public class AuctionHouseNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionHouseNotFoundException(){
		super(ExceptionMessages.A_HOUSE_NOT_FOUND) ;
	}
}
