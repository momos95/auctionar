package com.spideo.mamadou.auctionar.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spideo.mamadou.auctionar.entities.Auction;
import com.spideo.mamadou.auctionar.enums.AuctionStatus;
import com.spideo.mamadou.auctionar.utils.AuctionUtils;

/**
 * 
 * @author Mamadou
 * Bean used to show List of auctions based on their status. 
 *
 */
public class PrettyDesignedAuctionList {
	
	@JsonIgnore
	private List<Auction> auctions ;
	
	public PrettyDesignedAuctionList(List<Auction> auctions) {
		this.auctions = auctions ;
	}
	
	@JsonProperty
	public List<Auction> getRunningAuctions(){
		return AuctionUtils.getAuctionsBasedStatus(auctions, AuctionStatus.RUNNING) ;
	}
	
	@JsonProperty
	public List<Auction> getTerminatedAuctions(){
		return AuctionUtils.getAuctionsBasedStatus(auctions, AuctionStatus.RUNNING) ;
	}
	
	@JsonProperty
	public List<Auction> getNotStartedAuctions(){
		return AuctionUtils.getAuctionsBasedStatus(auctions, AuctionStatus.RUNNING) ;
	}
	
}
