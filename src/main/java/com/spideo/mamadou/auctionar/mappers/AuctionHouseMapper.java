package com.spideo.mamadou.auctionar.mappers;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.spideo.mamadou.auctionar.constants.ValidationMessages;
/**
 * 
 * @author Mamadou
 * Mapper used for RequestBody to get needed information for auction house creation
 *
 */
public class AuctionHouseMapper implements SMapper {

	@NotNull(message = ValidationMessages.AUCTION_HOUSE_NAME_NULL_EMPTY)
	@NotEmpty(message = ValidationMessages.AUCTION_HOUSE_NAME_NULL_EMPTY)
	private String name ;

	public AuctionHouseMapper(){ // FOR REQUESTBODY INIT

	}

	public AuctionHouseMapper(String name){ // FOR REQUESTBODY INIT
		this.name = name ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AuctionHouseMapper [name=" + name + "]";
	}	
}
