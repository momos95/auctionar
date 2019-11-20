package com.spideo.mamadou.auctionar.controllers;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spideo.mamadou.auctionar.beans.ActivityResponse;
import com.spideo.mamadou.auctionar.beans.SimpleApiPrettyResponse;
import com.spideo.mamadou.auctionar.enums.ResponseErrorType;
import com.spideo.mamadou.auctionar.factories.ErrorResponseFactory;
import com.spideo.mamadou.auctionar.mappers.BiddingMapper;
import com.spideo.mamadou.auctionar.mappers.SMapper;
import com.spideo.mamadou.auctionar.services.IBiddingService;
/**
 * 
 * @author Mamadou
 * Bidding controller used for : 
 * 		_creation
 * 	
 *
 */
@RestController
@RequestMapping("/biddings")
public class BiddingController {

	/**
	 * Service used to communicate with DB.
	 */
	@Autowired
	private IBiddingService biddingService ;
	
	/**
	 * Used for constraint validation
	 */
	@Autowired
	private Validator validator ;
	
	/**
	 * Letting Users to bid given Auction by provided needed Information @see {@link BiddingMapper}
	 * EXPECTED HTTP METHOD : POST
	 * @param biddingWrapper
	 * @return created bidding
	 */
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody BiddingMapper biddingWrapper){
		// checking constraints
		Set<ConstraintViolation<SMapper>> violations = validator.validate(biddingWrapper) ;
		SimpleApiPrettyResponse response = new SimpleApiPrettyResponse() ;
		// Check if constraints are satisfied
		if(violations.isEmpty()){
			final ActivityResponse actionResult = biddingService.create(biddingWrapper) ;
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
}
