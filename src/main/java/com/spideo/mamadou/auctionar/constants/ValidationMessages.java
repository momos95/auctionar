package com.spideo.mamadou.auctionar.constants;

/**
 * 
 * @author Mamadou
 * All Constraint validation messages
 *
 */
public abstract class ValidationMessages {

	/************************************ AUCTION HOUSE ************************************/
	public static final String AUCTION_HOUSE_NAME_NULL_EMPTY	= "Auction house's name cannot be empty or null" ;
	
	/************************************ AUCTION ******************************************/
	public static final String AUCTION_NAME_EMPTY_OR_NULL 		= "Auction's name cannot be empty or null." ;
	public static final String AUCTION_DESC_EMPTY_OR_NULL 		= "Auction's description cannot be empty or null." ;
	public static final String AUCTION_START_TIME_NULL			= "Auctions's Start time cannot be null." ;
	public static final String AUCTION_END_TIME_NULL			= "Auctions's End time cannot be null." ;
	public static final String AUCTION_START_PRICE_EMPTY_NULL	= "Auction's start price cannot be empty or null." ;
	
	/************************************* Bidding ******************************************/
	public static final String BIDDING_USER_NAME_NULL_EMPTY		= "idBidder cannot be null." ;
	public static final String BIDDING_AUCTION_ID_NULL			= "idAuction (or idAuctionHouse) cannot be null." ;
	public static final String BIDDING_PRICE_NULL				= "Bidding price cannot be null" ;
	
	/************************************ USER *********************************************/
	public static final String USER_NAME_EMPTY_NULL				= "User's name cannot be empty or null." ;
}
