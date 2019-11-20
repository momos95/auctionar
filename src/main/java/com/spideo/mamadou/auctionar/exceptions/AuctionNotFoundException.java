package com.spideo.mamadou.auctionar.exceptions;

import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
/**
 * 
 * @author Mamadou
 *
 */
public class AuctionNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionNotFoundException(){
		super(ExceptionMessages.AUCTION_NOT_FOUND) ;
	}
}
