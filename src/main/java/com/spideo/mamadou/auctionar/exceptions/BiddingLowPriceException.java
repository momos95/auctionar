package com.spideo.mamadou.auctionar.exceptions;

import java.math.BigDecimal;

import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
/**
 * 
 * @author Mamadou
 *
 */
public class BiddingLowPriceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BiddingLowPriceException(BigDecimal currentPrice){
		super(ExceptionMessages.BIDDING_LOWER_PRICE + " * currentPrice : "+currentPrice) ;
	}
}
