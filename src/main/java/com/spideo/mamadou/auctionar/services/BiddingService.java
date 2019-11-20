package com.spideo.mamadou.auctionar.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spideo.mamadou.auctionar.beans.ActivityResponse;
import com.spideo.mamadou.auctionar.entities.Auction;
import com.spideo.mamadou.auctionar.entities.Bidding;
import com.spideo.mamadou.auctionar.entities.User;
import com.spideo.mamadou.auctionar.enums.Role;
import com.spideo.mamadou.auctionar.exceptions.AuctionNotFoundException;
import com.spideo.mamadou.auctionar.exceptions.AuctionNotStartedException;
import com.spideo.mamadou.auctionar.exceptions.AuctionTerminatedException;
import com.spideo.mamadou.auctionar.exceptions.BiddingLowPriceException;
import com.spideo.mamadou.auctionar.exceptions.PrettySqlException;
import com.spideo.mamadou.auctionar.mappers.BiddingMapper;
import com.spideo.mamadou.auctionar.repositories.AuctionRepository;
import com.spideo.mamadou.auctionar.repositories.BiddingRepository;
import com.spideo.mamadou.auctionar.repositories.UserRepository;
import com.spideo.mamadou.auctionar.utils.AuctionUtils;

@Service
public class BiddingService implements IBiddingService {

	@Autowired
	private BiddingRepository biddingRepository ; 

	@Autowired
	private AuctionRepository auctionRepository ;
	
	@Autowired
	private UserRepository userRepository ;
	
	/**
	 * The Logger
	 */
	private final Logger logger = LoggerFactory.getLogger (this.getClass ());

	@Override
	public ActivityResponse create(BiddingMapper biddingMapper) {
		
		logger.info("Trying to bid an auction with data : {} ", biddingMapper);
		Optional<Auction> auctionOp = auctionRepository.findById(biddingMapper.getIdAuction()) ;		
		
		if(!auctionOp.isPresent()){
			logger.info("We couldn't find specified auction.");
			throw new AuctionNotFoundException();
		}			
		Auction auction = auctionOp.get() ;
		logger.info("Auction exists.");
		// Check Status and price.
		validateBiddingOptions(auction, biddingMapper.getPrice());
		logger.info("After data validation");
		User bidder = getOrCreateBidder(biddingMapper.getBidderName()) ;
		Bidding lastUserBidding = getLastUserBiddingAuction(auction, bidder) ;
		ActivityResponse response = new ActivityResponse() ;
		try {
			if(lastUserBidding != null){
				biddingRepository.deleteById(lastUserBidding.getId());
				biddingRepository.flush();
				auction.getBiddings().removeIf(a -> a.getId().equals(lastUserBidding.getId())) ;
			}			
			response.setEntity(biddingRepository.save(new Bidding(bidder, auction, biddingMapper.getPrice())));
			auction.setCurrentPrice(biddingMapper.getPrice());
			auctionRepository.save(auction) ;
			response.setError(false);
			logger.info("Bidding successfully consumed.");
		} catch (Exception e) {
			logger.error("Fatal exception : {} ", e) ;
			response.setError(true);
			throw new PrettySqlException("[SQL ERROR] fatal exception while Bidding.") ;
		}
		return response;
	}

	@Override
	public Bidding getLastUserBiddingAuction(Auction auction, User bidder){
		return auction.getBiddings().stream()
			.filter(bidding -> bidding.getBidder().getName().equals(bidder.getName()))
			.findAny()
			.orElse(null);
	}
	
	@Override
	public User getOrCreateBidder(String name){
		
		User bidder;
		
		if(userRepository.existsByName(name)){
			bidder = userRepository.findByName(name) ;
		}
		else{
			bidder = new User() ;
			bidder.setName(name);
			bidder.setRole(Role.BIDDER);
			try {
				bidder = userRepository.save(bidder) ;
				logger.info("New Bidder inserted : {}", bidder) ;
			} catch (Exception e) {
				logger.error("Fatal exception while inserting new Bidder : {} ", e) ;
				throw new PrettySqlException("[SQL ERROR] fatal exception while inserting new Bidder") ;
			}
		}
		return bidder ;
	}
	
	private void validateBiddingOptions(Auction auction, BigDecimal price){
		logger.info("Checking isTerminated validation");
		if(AuctionUtils.isTerminated(auction)){
			logger.error("Impossible to bid a terminated Auction");
			throw new AuctionTerminatedException() ;
		}
		logger.info("Checking isNotStarted validation");
		if(AuctionUtils.isNotStarted(auction)){
			logger.error("Impossible to bid a not started Auction");
			throw new AuctionNotStartedException() ;
		}
		logger.info("Checking priceIsEligible validation");
		if(!AuctionUtils.priceIsEligible(auction, price)){
			logger.error("Bidding price is lower than current price");
			throw new BiddingLowPriceException(auction.getCurrentPrice()) ;
		}
		logger.info("End of validation.");
	}
	
}
