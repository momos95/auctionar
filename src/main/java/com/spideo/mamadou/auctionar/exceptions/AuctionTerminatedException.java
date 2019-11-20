package com.spideo.mamadou.auctionar.exceptions;

import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
/**
 * 
 * @author Mamadou
 *
 */
public class AuctionTerminatedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionTerminatedException(){
		super(ExceptionMessages.AUCTION_IS_TERMINATED) ;
	}
}
