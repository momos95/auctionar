package com.spideo.mamadou.auctionar.beans;

import com.spideo.mamadou.auctionar.entities.Auction;
import com.spideo.mamadou.auctionar.enums.AuctionStatus;

/**
 * 
 * @author Mamadou
 * Bean used to show Auction's details.
 */
public class PrettyDesignedAuctionResponse {
	
	private AuctionStatus status ;
	private Auction auction ;
	private String winner ;	
	
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	/**
	 * 
	 * @return the actual status of the auction
	 */
	public AuctionStatus getStatus() {
		return status;
	}
	public void setStatus(AuctionStatus status) {
		this.status = status;
	}
	/**
	 * 
	 * @return if Auction is terminated : the winner, otherwhise NULL 
	 */
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}		
}
