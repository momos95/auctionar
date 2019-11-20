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
import com.spideo.mamadou.auctionar.beans.PrettyDesignedAuctionList;
import com.spideo.mamadou.auctionar.beans.SimpleApiPrettyResponse;
import com.spideo.mamadou.auctionar.enums.ResponseErrorType;
import com.spideo.mamadou.auctionar.factories.ErrorResponseFactory;
import com.spideo.mamadou.auctionar.mappers.AuctionHouseMapper;
import com.spideo.mamadou.auctionar.mappers.SMapper;
import com.spideo.mamadou.auctionar.services.IAuctionHouseService;
/**
 * 
 * @author Mamadou
 * Entry of Auction house transactions :
 * 		_ creation
 * 		_ details
 * 		_ deletion
 *
 */

@RestController
@RequestMapping("/auctionhouses")
public class AuctionHouseController {

	/**
	 * Service used to communicate with DB.
	 */
	@Autowired
	private IAuctionHouseService auctionHouseService ;
	
	/**
	 * used to validate all entry bean by checking constraints.
	 */
	@Autowired
	private Validator validator ;
	
	/**
	 * Return all existing auction houses
	 * EXPECTED HTTP METHOD : GET
	 * @return List of existing auction houses.
	 */
	@GetMapping(value="/")
	public ResponseEntity<Object> getAllAuctionHouses(){
		return new ResponseEntity<>(auctionHouseService.getAll(),HttpStatus.OK) ;
	}
	
	/**
	 * Get corresponding Auction House matching given ID
	 * EXPECTED HTTP METHOD : GET
	 * @param id (Long) : searched auction house's ID
	 * @return the corresponding Auction House
	 */
	@GetMapping(value="/{id}")
	public ResponseEntity<Object> getOneAuctionHouse(@PathVariable(value="id") Long id){
		return new ResponseEntity<>(auctionHouseService.getOneById(id), HttpStatus.OK) ;
	}
	
	/**
	 * Given Auction House (by ID), return listed auctions by status
	 * EXPECTED HTTP METHOD : GET
	 * @param id (Long) : ID of corresponding auction house
	 * @return list of auctions based on status
	 */
	@GetMapping(value="/{id}/auctions")
	public ResponseEntity<Object> getAuctions(@PathVariable(value="id") Long id){
		return new ResponseEntity<>(new PrettyDesignedAuctionList(auctionHouseService.getAuctions(id)), HttpStatus.OK) ;
	}
	
	/**
	 * Create new auction house based on provided information
	 * EXPECTED HTTP METHOD : POST
	 * @param auctionHouseMapper : Mapper used to get auction house information 
	 * @return created auction house
	 */
	@PostMapping(value="/")
	public ResponseEntity<Object> createAuctionHouse(@RequestBody AuctionHouseMapper auctionHouseMapper){
		// checking constraints
		Set<ConstraintViolation<SMapper>> violations = validator.validate(auctionHouseMapper) ;
		SimpleApiPrettyResponse response = new SimpleApiPrettyResponse() ;
		// Check if constraints are satisfied
		if(violations.isEmpty()){
			final ActivityResponse actionResult = auctionHouseService.create(auctionHouseMapper) ;
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
	 * Deleting corresponding auction house ID
	 * @param id : auction house's id
	 * @return :confirmation message with status.
	 */
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Object> deleteAucionHouse(@PathVariable(value="id") Long id){
		auctionHouseService.delete(id) ;
		return new ResponseEntity<>("Auction House successfully deleted", HttpStatus.OK) ;
	}
}
