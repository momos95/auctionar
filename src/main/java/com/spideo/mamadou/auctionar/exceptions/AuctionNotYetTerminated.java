package com.spideo.mamadou.auctionar.exceptions;

import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
/**
 * 
 * @author Mamadou
 *
 */
public class AuctionNotYetTerminated extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionNotYetTerminated(){
		super(ExceptionMessages.AUCTION_NOT_TERMINATED) ;
	}
}
