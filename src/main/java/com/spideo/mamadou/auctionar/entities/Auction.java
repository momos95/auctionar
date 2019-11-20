package com.spideo.mamadou.auctionar.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spideo.mamadou.auctionar.constants.ValidationMessages;
import com.spideo.mamadou.auctionar.mappers.AuctionMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Getter @Setter 
public class Auction extends SpideoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8990945564094600692L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long id ;
	
	@NotNull(message = ValidationMessages.AUCTION_NAME_EMPTY_OR_NULL)
	@NotEmpty(message = ValidationMessages.AUCTION_NAME_EMPTY_OR_NULL)
	private String name ;
	
	@NotNull(message = ValidationMessages.AUCTION_DESC_EMPTY_OR_NULL)
	@NotEmpty(message = ValidationMessages.AUCTION_DESC_EMPTY_OR_NULL)
	private String description ;
	
	@NotNull(message = ValidationMessages.AUCTION_START_TIME_NULL)	
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime startTime ;
	
	@NotNull(message = ValidationMessages.AUCTION_END_TIME_NULL)
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime endTime ;
	
	@NotNull(message = ValidationMessages.AUCTION_START_PRICE_EMPTY_NULL)	
	private BigDecimal startPrice ;
	
	private BigDecimal currentPrice ;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="auctionHouse_id")
	@JsonIgnoreProperties("auctions")
	private AuctionHouse auctionHouse ;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="auction",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	@JsonIgnoreProperties("auction")
	private List<Bidding> biddings = new ArrayList<>() ;
		
	
	public Auction(AuctionMapper wrapper){
		this() ;
		name = wrapper.getName() ;
		description = wrapper.getDescription() ;
		startTime = wrapper.getStartTime() ;
		endTime = wrapper.getEndTime() ;
		startPrice = wrapper.getStartPrice() ;
		currentPrice = wrapper.getCurrentPrice() ;
	}


	@Override
	public String toString() {
		return "Auction [id=" + id + ", name=" + name + ", description=" + description + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", startPrice=" + startPrice + ", currentPrice=" + currentPrice + "]";
	}
		
}
