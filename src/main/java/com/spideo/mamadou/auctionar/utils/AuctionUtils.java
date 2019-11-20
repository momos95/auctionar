package com.spideo.mamadou.auctionar.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.spideo.mamadou.auctionar.entities.Auction;
import com.spideo.mamadou.auctionar.entities.AuctionHouse;
import com.spideo.mamadou.auctionar.entities.Bidding;
import com.spideo.mamadou.auctionar.enums.AuctionStatus;
/**
 * 
 * @author Mamadou
 *
 */
public class AuctionUtils {

	private AuctionUtils() {
		
	}
	/**
	 * Check if submitted times are corrects.
	 * @param auction
	 * @return TRUE if startTime <= now < endTime
	 */
	public static final boolean correctStartEndDate(Auction auction){
		return auction.getStartTime().isBefore(auction.getEndTime()) && !isTerminated(auction) ;
	}
	
	/**
	 * Check if given auction is terminated.<br>
	 * @param auction {@link Auction}.<br>
	 * @return TRUE if endTime <= now.
	 */
	public static final boolean isTerminated(Auction auction){
		LocalDateTime now = LocalDateTime.now() ;
		return now.isAfter(auction.getEndTime()) || now.equals(auction.getEndTime()) ;
	}
	
	/**
	 * Check if auction is running.<br>
	 * @param auction {@link Auction}.<br>
	 * @return TRUE if startTime <= now < EndTime<br>.
	 */
	public static final boolean isRunning(Auction auction){
		LocalDateTime now =  LocalDateTime.now() ;
		return now.isAfter(auction.getStartTime()) && now.isBefore(auction.getEndTime()) ;
	}
	
	/**
	 * Check if auction is not yet started.<br>
	 * @param auction {@link Auction}.<br>
	 * @return TRUE if now < startTime <br>
	 */
	public static final boolean isNotStarted(Auction auction){
		return LocalDateTime.now().isBefore(auction.getStartTime()) ;
	}
	
	/**
	 * Given auction and price, Check if proposed bidding price is correct.<br>
	 * @param auction {@link Auction}.<br>
	 * @param price {@link BigDecimal}
	 * @return TRUE if price > action.currentPrice
	 */
	public static final boolean priceIsEligible(Auction auction, BigDecimal price){
		return price.compareTo(auction.getCurrentPrice()) == 1 ;
	}
	
	/**
	 * Given auction, return actual winner name.
	 * @param auction {@link Auction}
	 * @return actual winner name 
	 */
	public static final String getActualWinner(Auction auction){		
		List<Bidding> biddings = auction.getBiddings() ;
		if(biddings == null || biddings.isEmpty()){
			return null ;
		}
		Collections.sort(biddings, Collections.reverseOrder());
		return biddings.get(0).getBidder().getName() ;
	}
	
	/**
	 * Check if auction already exists in given auctionHouse.<br> 
	 * @param auction {@link Auction}.<br>
	 * @param house {@link AuctionHouse}.<br>
	 * @return true if auctionHouse contains already an auction with same name, description, start and end times.<br>
	 */
	
	public static final boolean alreadyExist(Auction auction, AuctionHouse house){
		return house.getAuctions().stream().anyMatch(a -> 
			a.getName().equals(auction.getName())
			&& a.getDescription().equals(auction.getDescription())
			&& a.getStartTime().isEqual(auction.getStartTime())
			&& a.getEndTime().isEqual(auction.getEndTime())) ;
	}
	
	/**
	 * Given an auction, return status.<br>
	 * @param auction {@link Auction}.<br>
	 * @return auction status {@link AuctionStatus}.
	 */
	public static final AuctionStatus getStatus(Auction auction){				
		
		if(isTerminated(auction))
			return AuctionStatus.TERMINATED ; 
		
		if(isRunning(auction))
			return AuctionStatus.RUNNING ;
		
		if(isNotStarted(auction))
			return AuctionStatus.NOT_STARTED ;
		
		return AuctionStatus.DELETED ;
	}
	
	/**
	 * Given auction list, filter by given status
	 * @param auctions List of {@link Auction}
	 * @param status
	 * @return filtered elements
	 */
	public static final List<Auction> getAuctionsBasedStatus(List<Auction> auctions, AuctionStatus status){
		if(auctions == null)
			return new ArrayList<>() ;
		return auctions.stream()
				.filter(auction -> status.equals(getStatus(auction)))
				.collect(Collectors.toList());
	}
}
