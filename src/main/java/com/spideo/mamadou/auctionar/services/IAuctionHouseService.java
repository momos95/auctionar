package com.spideo.mamadou.auctionar.services;

import java.util.List;

import com.spideo.mamadou.auctionar.beans.ActivityResponse;
import com.spideo.mamadou.auctionar.entities.Auction;
import com.spideo.mamadou.auctionar.entities.AuctionHouse;
import com.spideo.mamadou.auctionar.exceptions.AuctionHouseAlreadyExistsException;
import com.spideo.mamadou.auctionar.exceptions.AuctionHouseNotFoundException;
import com.spideo.mamadou.auctionar.mappers.AuctionHouseMapper;

/**
 * 
 * @author Mamadou </br>
 * 
 * Auction House services.</br>
 * 
 * Used in order to communicate with AuctionHouse Repository for SQL request </br>
 *
 */

public interface IAuctionHouseService {

	/**
	 * Return corresponding Auction House by matching ID. </br>
	 * It will throw an exception (@see {@link AuctionHouseNotFoundException}) if no Auction House is found.</br>
	 * @param id of the Auction House you are looking for.</br>
	 * @return the corresponding Auction House</br>
	 */
	public AuctionHouse getOneById(Long id) ;
	
	/**
	 * Get the list of all existing auction houses.</br>
	 * Return empty arrayList if no auction house found.</br>
	 * @return list of existing auction houses</br>
	 */	
	public List<AuctionHouse> getAll() ;
	
	
	/**
	 * Given Auction House ID, get all auction in corresponding auction house.</br>
	 * It will throw an exception (@see {@link AuctionHouseNotFoundException}) if the indicated auction house does'nt exist.</br>
	 * @param id : ID of auction house.</br>
	 * @return arrayList containing all auction in given auction house.</br>
	 */
	public List<Auction> getAuctions(Long id) ;
	
	/**
	 * Create new Auction house with provided information (@see {@link AuctionHouseMapper}).</br>
	 * Throws exception (@see {@link AuctionHouseAlreadyExistsException}) if there already exist an auction house with given name.</br>
	 * @param auctionHouseWrapper : mapper containing submitted data from requestbody</br>
	 * @return activity response (@see {@link ActivityResponse}) containing created Auction House.</br>
	 */
	public ActivityResponse create(AuctionHouseMapper auctionHouseWrapper) ;
	
	/**
	 * Delete from database corresponding auction house and all dependent data.</br>
	 * Throws exception (@see {@link AuctionHouseNotFoundException}) if auction house doesn't exist,</br>
	 * Delete all Auction and bidding.</br>
	 * @param id : corresponding auction house.</br>
	 * @return true if OK, false if KO.</br>
	 */
	public boolean delete(Long id) ;
	
}
