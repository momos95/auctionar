package com.spideo.mamadou.auctionar.mappers;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.spideo.mamadou.auctionar.constants.ValidationMessages;
/**
 * 
 * @author Mamadou
 * Mapper used to get needed information for Bidding
 *
 */
public class BiddingMapper implements SMapper {

	@NotNull(message = ValidationMessages.BIDDING_USER_NAME_NULL_EMPTY) 
	@NotEmpty(message = ValidationMessages.BIDDING_USER_NAME_NULL_EMPTY) 
	private String bidderName ;
	
	@NotNull(message = ValidationMessages.BIDDING_AUCTION_ID_NULL)
	private Long idAuction ;
	
	@NotNull(message = ValidationMessages.BIDDING_PRICE_NULL)
	private BigDecimal price ;
	
	public BiddingMapper () { //FOR REQUESTBODY
		
	}
	
	public BiddingMapper(String bidderName, Long idAuction, BigDecimal price){
		this.bidderName = bidderName ;
		this.idAuction = idAuction ;
		this.price = price ;
	}
	
	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public Long getIdAuction() {
		return idAuction;
	}

	public void setIdAuction(Long idAuction) {
		this.idAuction = idAuction;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BiddingMapper [bidderName=" + bidderName + ", idAuction=" + idAuction + ", price=" + price + "]";
	}	
		
}
