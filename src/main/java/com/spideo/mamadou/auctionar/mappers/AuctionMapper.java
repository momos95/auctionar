package com.spideo.mamadou.auctionar.mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.spideo.mamadou.auctionar.constants.ValidationMessages;
/**
 * 
 * @author Mamadou
 * For RequestBody, to get needed information for auction creation
 *
 */
public class AuctionMapper implements SMapper {


	@NotNull(message=ValidationMessages.BIDDING_AUCTION_ID_NULL)	
	private Long idAuctionHouse ;

	@NotNull(message = ValidationMessages.AUCTION_NAME_EMPTY_OR_NULL)
	@NotEmpty(message = ValidationMessages.AUCTION_NAME_EMPTY_OR_NULL)
	private String name ;

	@NotNull(message = ValidationMessages.AUCTION_DESC_EMPTY_OR_NULL)
	@NotEmpty(message = ValidationMessages.AUCTION_DESC_EMPTY_OR_NULL)
	private String description ;

	@NotNull(message = ValidationMessages.AUCTION_START_TIME_NULL)	
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)	
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime startTime ;

	@NotNull(message = ValidationMessages.AUCTION_END_TIME_NULL)
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime endTime ;

	@NotNull(message = ValidationMessages.AUCTION_START_PRICE_EMPTY_NULL)	
	private BigDecimal startPrice ;

	private BigDecimal currentPrice ;

	/**
	 * Create new Auction and setting currentPrice (>= startPrice).
	 */
	public AuctionMapper() {

		currentPrice = startPrice ; 
	}

	public Long getIdAuctionHouse() {
		return idAuctionHouse;
	}

	public void setIdAuctionHouse(Long idAuctionHouse) {
		this.idAuctionHouse = idAuctionHouse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "AuctionMapper [idAuction=" + idAuctionHouse + ", name=" + name + ", description=" + description
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", startPrice=" + startPrice + ", currentPrice="
				+ currentPrice + "]";
	}		

}
