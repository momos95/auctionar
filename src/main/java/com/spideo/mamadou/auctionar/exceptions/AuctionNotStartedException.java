package com.spideo.mamadou.auctionar.exceptions;

import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
/**
 * 
 * @author Mamadou
 *
 */
public class AuctionNotStartedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionNotStartedException(){
		super(ExceptionMessages.AUCTION_NOT_STARTED) ;
	}
}
