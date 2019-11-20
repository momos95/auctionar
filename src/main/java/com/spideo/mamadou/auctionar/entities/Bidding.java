package com.spideo.mamadou.auctionar.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spideo.mamadou.auctionar.constants.ValidationMessages;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Getter @Setter
public class Bidding extends SpideoEntity implements Comparable<Bidding>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1016157788346286878L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id ;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bidder_id")
	@JsonBackReference
	private User bidder ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="auction_id")
	@JsonBackReference
	@JsonIgnoreProperties("biddings")
	private Auction auction ;
	
	@NotNull(message = ValidationMessages.BIDDING_PRICE_NULL)
	private BigDecimal price ;	

	public Bidding(User bidder, Auction auction, BigDecimal price) {
		this.bidder = bidder ;
		this.auction = auction ;
		this.price = price ;
	}
	
	@JsonProperty
	public String bidderName(){
		return bidder.getName() ;
	}
	
	@Override
	public int compareTo(Bidding o) {		
		return price.compareTo(o.price);
	}


	@Override
	public String toString() {
		return "Bidding [id=" + id + ", bidder=" + bidder + ", auction=" + auction + ", price=" + price + "]";
	}
}
