package com.spideo.mamadou.auctionar.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spideo.mamadou.auctionar.beans.ActivityResponse;
import com.spideo.mamadou.auctionar.beans.PrettyDesignedAuctionResponse;
import com.spideo.mamadou.auctionar.constants.ExceptionMessages;
import com.spideo.mamadou.auctionar.entities.Auction;
import com.spideo.mamadou.auctionar.entities.AuctionHouse;
import com.spideo.mamadou.auctionar.entities.Bidding;
import com.spideo.mamadou.auctionar.enums.AuctionStatus;
import com.spideo.mamadou.auctionar.exceptions.AuctionAlreadyExistsException;
import com.spideo.mamadou.auctionar.exceptions.AuctionHouseNotFoundException;
import com.spideo.mamadou.auctionar.exceptions.AuctionNotFoundException;
import com.spideo.mamadou.auctionar.exceptions.AuctionNotYetTerminated;
import com.spideo.mamadou.auctionar.exceptions.NoBiddingException;
import com.spideo.mamadou.auctionar.exceptions.PrettySqlException;
import com.spideo.mamadou.auctionar.exceptions.StartEndTimesIncorrectException;
import com.spideo.mamadou.auctionar.mappers.AuctionMapper;
import com.spideo.mamadou.auctionar.repositories.AuctionHouseRepository;
import com.spideo.mamadou.auctionar.repositories.AuctionRepository;
import com.spideo.mamadou.auctionar.repositories.UserRepository;
import com.spideo.mamadou.auctionar.utils.AuctionUtils;

@Service
public class AuctionService implements IAuctionService{

	@Autowired
	private AuctionRepository auctionRepository ;
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private AuctionHouseRepository auctionHouseRepository ;
	
	/**
	 * The Logger
	 */
	private final Logger logger = LoggerFactory.getLogger (this.getClass ());
	
	
	@Override
	public ActivityResponse create(AuctionMapper wrapper) {
		
		logger.info("Trying to add new Action with data : {} ", wrapper);
		
		Optional<AuctionHouse> auctionHouse = auctionHouseRepository.findById(wrapper.getIdAuctionHouse()) ;
		
		if(!auctionHouse.isPresent()){
			throw new AuctionHouseNotFoundException() ;
		}	
		
		ActivityResponse response = new ActivityResponse() ;
		Auction auction = new Auction(wrapper) ;
		
		if(!AuctionUtils.correctStartEndDate(auction)){
			logger.error(ExceptionMessages.AUCTION_DATES_INCORRECT);
			throw new StartEndTimesIncorrectException() ;
		}
		if(AuctionUtils.alreadyExist(auction, auctionHouse.get())){
			logger.error(ExceptionMessages.AUCTION_ALREADY_EXISTS);
			throw new AuctionAlreadyExistsException() ;
		}
		auction.setAuctionHouse(auctionHouse.get());
		
		try {
			response.setEntity(auctionRepository.save(auction));
			response.setError(false);
			logger.info("New auction successfully added : {} ", response.getEntity());
		} catch (Exception e) {
			logger.error("Error while inserting new action : {}", e);
			throw new PrettySqlException("[SQL ERROR] Error while inserting new auction") ;
		}
		return response;
	}

	@Override
	public boolean delete(Long id) {
		logger.info("Trying to delete an auction (ID) "+ id);
		Optional<Auction> auction = auctionRepository.findById(id) ;
		
		if(!auction.isPresent()){
			logger.info(ExceptionMessages.AUCTION_NOT_FOUND);
			throw new AuctionNotFoundException();
		}
		
		try {
			auctionRepository.deleteById(id);
			logger.info("Auction is successfully deleted: (id = " + id + ")");
		} catch (Exception e) {
			logger.error("Error while deleting auction : {} ", e);
			throw new PrettySqlException("[SQL ERROR] Error while deleting auction.") ;
		}
			
		return true;
	}

	@Override
	public ActivityResponse getWinner(Long id) {
		logger.info("Trying to get the winner of an auction (ID) "+ id);
		Optional<Auction> auction = auctionRepository.findById(id) ;
		
		if(!auction.isPresent()){
			logger.info(ExceptionMessages.AUCTION_NOT_FOUND);
			throw new AuctionNotFoundException();
		}
		
		if(!AuctionUtils.isTerminated(auction.get())){
			logger.info("Auction is not yet terminated");
			throw new AuctionNotYetTerminated() ;
		}
		
		ActivityResponse response = new ActivityResponse() ;
		List<Bidding> biddings = auction.get().getBiddings() ;		
		
		if(biddings.isEmpty()){
			logger.warn("No bidding for specified auction : NO WINNER.");
			throw new NoBiddingException() ;
		}		
		response.setEntity(userRepository.findByName(AuctionUtils.getActualWinner(auction.get())));
		return response;
	}

	@Override
	public PrettyDesignedAuctionResponse get(Long id) {
		
		logger.info("Trying to get an auction (ID) "+ id);
		Optional<Auction> auction = auctionRepository.findById(id) ;
		
		if(!auction.isPresent()){
			logger.info(ExceptionMessages.AUCTION_NOT_FOUND);
			throw new AuctionNotFoundException();
		}
		PrettyDesignedAuctionResponse response = new PrettyDesignedAuctionResponse() ;
		response.setStatus(AuctionUtils.getStatus(auction.get()));
		response.setAuction(auction.get());
		response.setWinner(response.getStatus().equals(AuctionStatus.TERMINATED) ? AuctionUtils.getActualWinner(auction.get()) : null);
		logger.info("{}", response.getWinner());
		return response;
	}

}
