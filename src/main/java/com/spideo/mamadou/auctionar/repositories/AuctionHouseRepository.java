package com.spideo.mamadou.auctionar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spideo.mamadou.auctionar.entities.AuctionHouse;

public interface AuctionHouseRepository extends JpaRepository<AuctionHouse, Long> {

	public AuctionHouse findByName(String name) ;
	
}
