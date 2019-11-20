package com.spideo.mamadou.auctionar.constants;

/**
 * 
 * @author Mamadou
 * All exception messages to avoid ducplicates string params.
 */
public abstract class ExceptionMessages {

	/*************************************** USER ***************************************/
	public static final String USER_ALREADY_EXISTS = "User's name is already used." ;
	
	
	
	/*************************************** BIDDING ***************************************/
	public static final String BIDDING_LOWER_PRICE 		= "Bidding price is lower than current price." ;
	public static final String NO_BIDDING_ON_AUCTION 	= "Auction is over without biddings then : no winner." ;
	
	
	
	/*************************************** AUCTION H ***************************************/
	public static final String A_H_ALREADY_EXISTS 		= "Auction house's name is already used." ;
	public static final String A_HOUSE_NOT_FOUND 		= "Auction house not found." ;
	
	/*************************************** AUCTION  ***************************************/	
	public static final String AUCTION_ALREADY_EXISTS 	= "There is already an auction in specified house with the same name, description and dates." ;
	public static final String AUCTION_NOT_FOUND 		= "Auction not found." ;
	public static final String AUCTION_NOT_TERMINATED 	= "Auction is not yet terminated." ;
	public static final String AUCTION_NOT_STARTED 		= "Auction has not yet started." ;
	public static final String AUCTION_IS_TERMINATED 	= "Auction is already over." ;
	public static final String AUCTION_DATES_INCORRECT 	= "Incorrect Start and End times.";
	
	
}
