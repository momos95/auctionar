package com.spideo.mamadou.auctionar.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spideo.mamadou.auctionar.beans.ActivityResponse;
import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
import com.spideo.mamadou.auctionar.entities.Auction;
import com.spideo.mamadou.auctionar.entities.AuctionHouse;
import com.spideo.mamadou.auctionar.exceptions.AuctionHouseAlreadyExistsException;
import com.spideo.mamadou.auctionar.exceptions.AuctionHouseNotFoundException;
import com.spideo.mamadou.auctionar.exceptions.PrettySqlException;
import com.spideo.mamadou.auctionar.mappers.AuctionHouseMapper;
import com.spideo.mamadou.auctionar.repositories.AuctionHouseRepository;

@Service
public class AuctionHouseService implements IAuctionHouseService {

	/**
	 * The Logger
	 */
	private final Logger logger = LoggerFactory.getLogger (this.getClass ());
	
	@Autowired
	private AuctionHouseRepository repository ;
	
	@Override
	public AuctionHouse getOneById(Long id) {
		Optional<AuctionHouse> auctionHouse = repository.findById(id) ;
		if(!auctionHouse.isPresent())
			throw new AuctionHouseNotFoundException() ;
		return auctionHouse.get();
	}

	@Override
	public List<AuctionHouse> getAll() {		
		return repository.findAll();
	}

	@Override
	public ActivityResponse create(AuctionHouseMapper auctionHouseWrapper) {
		
		logger.info("Trying to create new Auction House with data : {} ", auctionHouseWrapper);
		ActivityResponse response = new ActivityResponse() ;
		
		if(repository.findByName(auctionHouseWrapper.getName()) != null)
			throw new AuctionHouseAlreadyExistsException() ;
		
		try {			
			AuctionHouse auctionHouse = new AuctionHouse() ;
			auctionHouse.setName(auctionHouseWrapper.getName());
			response.setEntity(repository.save(auctionHouse));
			response.setError(false);
			logger.info("Auction house successfully created : {} ", response.getEntity());
		} catch (Exception e) {
			logger.error("Error while inserting new Auction house {}", e);
			throw new PrettySqlException("[SQL ERROR] Error while inserting new Auction house.") ;
		}
		
		return response ;
	}

	@Override
	public boolean delete(Long id) {
		
		logger.info("Trying to delete auction house by ID : "+ id);
		
		if(!repository.findById(id).isPresent()){			
			logger.error(ExceptionMessages.A_HOUSE_NOT_FOUND);
			throw new AuctionHouseNotFoundException() ;
		}
			
		try {
			repository.deleteById(id);
			logger.info("Auction house is successfully deleted: (id = " + id + ")");
		} catch (Exception e) {
			logger.error("Error while deleting auction house : {} ", e);
			throw new PrettySqlException("[SQL ERROR] Error while deleting auction house.") ;
		}
		return true;
	}

	@Override
	public List<Auction> getAuctions(Long id) {
		logger.info("Trying to return all auctions for AuctionHouse : "+ id);

		if(!repository.findById(id).isPresent()){			
			logger.error(ExceptionMessages.A_HOUSE_NOT_FOUND);
			throw new AuctionHouseNotFoundException() ;
		}		
		return repository.getOne(id).getAuctions();
	}	
	
	
}
