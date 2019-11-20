package com.spideo.mamadou.auctionar.controllers;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spideo.mamadou.auctionar.beans.ActivityResponse;
import com.spideo.mamadou.auctionar.beans.PrettyDesignedAuctionResponse;
import com.spideo.mamadou.auctionar.beans.SimpleApiPrettyResponse;
import com.spideo.mamadou.auctionar.enums.ResponseErrorType;
import com.spideo.mamadou.auctionar.factories.ErrorResponseFactory;
import com.spideo.mamadou.auctionar.mappers.AuctionMapper;
import com.spideo.mamadou.auctionar.mappers.SMapper;
import com.spideo.mamadou.auctionar.services.IAuctionService;

/**
 * 
 * @author Mamadou
 * 
 * Controller managing some transactions linked to Auctions :
 * 		_ creation
 * 		_ deletion
 * 		_ details :
 * 			- all
 * 			- biddings
 * 			- winner
 * 			
 */
@RestController
@RequestMapping("/auctions")
public class AuctionController {
	
	/**
	 * Service allowing to communicate with BDD.
	 */
	@Autowired
	private IAuctionService auctionService ;
	
	/**
	 * Validator used to validate entry bean.
	 */
	@Autowired
	private Validator validator ;
	
	/**
	 * Given an auction by ID, show all liked details (all attributes).
	 * EXCEPTED HTTP METHOD : GET
	 * @param id (Long): the identity of an Auction.
	 * @return all details related to the corresponding auction.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getAllDetails(@PathVariable(value="id") Long id){
		PrettyDesignedAuctionResponse response = auctionService.get(id) ;
		return new ResponseEntity<>(response, HttpStatus.OK) ;		
	}
	
	/**
	 * Given an auction by ID, show all linked biddings.
	 * EXCEPTED HTTP METHOD : GET
	 * @param id (Long) : Auction's ID.
	 * @return List of linked Bidding (price and name of bidder).
	 */
	@GetMapping("/{id}/biddings")
	public ResponseEntity<Object> getBidings(@PathVariable(value="id") Long id){
		PrettyDesignedAuctionResponse response = auctionService.get(id) ;
		return new ResponseEntity<>(response.getAuction().getBiddings(), HttpStatus.OK) ;		
	}
	
	/**
	 * Given an auction by ID, get directly the winner.
	 * EXCEPTED HTTP METHOD : GET
	 * @param id (Long) : Auction's ID.
	 * @return the winner if the Auction is terminated, NULL else.
	 */
	@GetMapping("/{id}/winner")
	public ResponseEntity<Object> getWinner(@PathVariable(value="id") Long id){		
		return new ResponseEntity<>(auctionService.getWinner(id), HttpStatus.OK) ;		
	}
	
	/**
	 * Create auction given an auction House and auction's information 
	 * EXCEPTED HTTP METHOD : POST
	 * @param auctionWrapper : @see {@link AuctionMapper}
	 * @return Return the created Auction with readeable details
	 */	
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody AuctionMapper auctionWrapper){
		// checking constraints
		Set<ConstraintViolation<SMapper>> violations = validator.validate(auctionWrapper) ;
		SimpleApiPrettyResponse response = new SimpleApiPrettyResponse() ;
		// Check if constraints are satisfied
		if(violations.isEmpty()){
			final ActivityResponse actionResult = auctionService.create(auctionWrapper) ;
			if(!actionResult.hasError()){
				response.setEntity(actionResult.getEntity());
				response.setStatus(HttpStatus.CREATED);
			}				
			else{
				response.setError(ErrorResponseFactory.buildBadRequest("Fatal creation error, please contact admin.", ResponseErrorType.CREATE_ERROR));
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
		else{
			response.setError(ErrorResponseFactory.buildBadRequest(violations));
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, response.getStatus()) ;	
	}
	
	/**
	 * Delete given an Auction by ID,
	 * @param id : id of auction to delete
	 * @return a confirmation message with the status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value="id") Long id){
		auctionService.delete(id) ;
		return new ResponseEntity<>("Specified Auction is successfully deleted.", HttpStatus.OK) ;
	}
}
