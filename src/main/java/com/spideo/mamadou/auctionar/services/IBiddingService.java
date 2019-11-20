package com.spideo.mamadou.auctionar.services;

import com.spideo.mamadou.auctionar.beans.ActivityResponse;
import com.spideo.mamadou.auctionar.entities.Auction;
import com.spideo.mamadou.auctionar.entities.Bidding;
import com.spideo.mamadou.auctionar.entities.User;
import com.spideo.mamadou.auctionar.exceptions.AuctionNotFoundException;
import com.spideo.mamadou.auctionar.exceptions.AuctionNotStartedException;
import com.spideo.mamadou.auctionar.exceptions.AuctionTerminatedException;
import com.spideo.mamadou.auctionar.exceptions.BiddingLowPriceException;
import com.spideo.mamadou.auctionar.mappers.BiddingMapper;

/**
 * 
 * @author Mamadou
 *
 */
public interface IBiddingService {
	/**
	 * Let specific user (@see {@link User}) bid auction by providing needed info (@see {@link BiddingMapper});<br>
	 * Throws {@link AuctionNotFoundException} if indicated auction doesn't exist.<br>
	 * Throws {@link AuctionTerminatedException} if auction is already terminated.<br>
	 * Throws {@link AuctionNotStartedException} if auction is not yet started.<br>
	 * Throws {@link BiddingLowPriceException} if bidding price is lower than current auction price.<br>
	 * @param biddingMapper (@see {@link BiddingMapper})
	 * @return created entity nested in activity response.
	 */
	public ActivityResponse create(BiddingMapper biddingMapper) ;
	
	/**
	 * Given auction and User data, check last user bidding on auction.<br>
	 * It's used to avoid two biddings with the same name about a same auction.<br>
	 * @param auction {@link Auction}<br>
	 * @param bidder {@link User}<br>
	 * @return Bidding if user has already a proposal for auction null otherwise.
	 */
	public Bidding getLastUserBiddingAuction(Auction auction, User bidder) ;
	
	/**
	 * Given name as string : <br>
	 * 	- if there is already existing user with same name, return this one.<br>
	 * 	- else, create a new one with given name.
	 * @param name (String)
	 * @return create {@link User}
	 */
	public User getOrCreateBidder(String name) ;
}
