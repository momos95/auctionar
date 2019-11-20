package com.spideo.mamadou.auctionar.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spideo.mamadou.auctionar.constants.ValidationMessages;

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
public class AuctionHouse extends SpideoEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3896552861479525456L;	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id ;
	
	@NotNull(message = ValidationMessages.AUCTION_HOUSE_NAME_NULL_EMPTY)
	@NotEmpty(message = ValidationMessages.AUCTION_HOUSE_NAME_NULL_EMPTY)
	@Column(unique=true)
	private String name ;
	
	@JsonManagedReference	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="auctionHouse",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("auctionHouse")
	private List<Auction> auctions = new ArrayList<>() ;
	
	public AuctionHouse(String name){
		this() ;
		this.name = name ;
	}
	
	@Override
	public String toString() {
		return "AuctionHouse [id=" + id + ", name=" + name + "]";
	}
		
}
