package com.spideo.mamadou.auctionar.services;

import com.spideo.mamadou.auctionar.beans.ActivityResponse;
import com.spideo.mamadou.auctionar.beans.PrettyDesignedAuctionResponse;
import com.spideo.mamadou.auctionar.exceptions.AuctionAlreadyExistsException;
import com.spideo.mamadou.auctionar.exceptions.AuctionHouseNotFoundException;
import com.spideo.mamadou.auctionar.exceptions.AuctionNotFoundException;
import com.spideo.mamadou.auctionar.exceptions.AuctionNotYetTerminated;
import com.spideo.mamadou.auctionar.exceptions.StartEndTimesIncorrectException;
import com.spideo.mamadou.auctionar.mappers.AuctionMapper;

/**
 * 
 * @author Mamadou <br>
 * 
 * Service for auction db transactions<br>
 *
 */
public interface IAuctionService {
	
	/**
	 * Given needed information, create a new auction.<br>
	 * Throws {@link AuctionHouseNotFoundException} if indicated Auction House doesn't exist.<br>
	 * Throws {@link AuctionAlreadyExistsException} if for the same auction house, there already exists auction with same name, descrition, startTime and endTime.<br> 
	 * Throws {@link StartEndTimesIncorrectException} if start and end time are functionally incorrect.<br>
	 * @param wrapper : correspond to all needed information for auction creation (@see {@link AuctionMapper}).<br>
	 * @return created Auction nested in activity response ({@link ActivityResponse})<br>
	 */
	public ActivityResponse create(AuctionMapper wrapper) ;
	
	/**
	 * Given Auction ID, return all corresponding details.<br>
	 * Throws {@link AuctionNotFoundException} if the auction indicated doesn't exist.<br>
	 * @param id : Auction we are looking for.<br>
	 * @return corresponding auction details in pretty print mode.<br>
	 */
	public PrettyDesignedAuctionResponse get(Long id) ;
	
	/**
	 * Delete corresponding auction
	 * Delete at the same time all linked bidding
	 * Throws {@link AuctionNotFoundException} if the auction indicated doesn't exist.<br>
	 * @param id
	 * @return
	 */
	public boolean delete(Long id) ;
	
	/**
	 * Given terminated Auction by ID, return the winner (with the high price).<br>
	 * Throws {@link AuctionNotYetTerminated} if auction is not terminated yet.<br>
	 * @param id : corresponding auction's ID
	 * @return the winner nested in  Activity response object {@link ActivityResponse}.
	 */
	public ActivityResponse getWinner(Long id) ;
	
}
