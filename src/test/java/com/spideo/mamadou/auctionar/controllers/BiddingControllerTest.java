package com.spideo.mamadou.auctionar.controllers;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spideo.mamadou.auctionar.configs.SpideoAbstractTest;
import com.spideo.mamadou.auctionar.mappers.AuctionHouseMapper;
import com.spideo.mamadou.auctionar.mappers.AuctionMapper;
import com.spideo.mamadou.auctionar.mappers.BiddingMapper;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BiddingControllerTest extends SpideoAbstractTest{

	@Override
	@Before
	public void setUp() {		
		super.setUp();
	}

	@Test
	public void test_1_Bidding_NoExistingAuction_Must_Fail() throws Exception {
		String uri = "/biddings/" ;
		final BiddingMapper mapper = new BiddingMapper("Mamadou", 2L, new BigDecimal(20000)) ;
		String inputJson = super.mapToJson(mapper) ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}

	@Test
	public void test_2_Bidding_NotStartedAuction_Must_Fail() throws Exception {
		// Create Auction house First ID = 1
		String uriAH = "/auctionhouses/" ;
		AuctionHouseMapper mapperAH = new AuctionHouseMapper("Maison de vente aux enchères 42") ;
		String inputJsonAH = super.mapToJson(mapperAH) ;
		MvcResult mvcResultAH = mvc.perform(MockMvcRequestBuilders.post(uriAH)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJsonAH)).andReturn();

		int statusAH = mvcResultAH.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), statusAH);

		// create Auction ID = 2
		String uriA = "/auctions/" ;
		final AuctionMapper mapperA = new AuctionMapper() ;		
		mapperA.setIdAuctionHouse(new Long(1));
		mapperA.setName("TestAuction");
		mapperA.setDescription("This is an test auction.");
		mapperA.setStartTime(LocalDateTime.of(2019, 12, 1, 15, 20, 42)); // NOT STARTED
		mapperA.setEndTime(LocalDateTime.of(2020, 12, 1, 15, 20, 42));
		mapperA.setStartPrice(new BigDecimal(25000));
		String inputJsonA = super.mapToJson(mapperA) ;
		MvcResult mvcResultA = mvc.perform(MockMvcRequestBuilders.post(uriA)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJsonA)).andReturn();
		int statusA = mvcResultA.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), statusA);

		String uri = "/biddings/" ;
		final BiddingMapper mapper = new BiddingMapper("Mamadou", 2L, new BigDecimal(20000)) ;
		String inputJson = super.mapToJson(mapper) ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}

	@Test
	public void test_3_Bidding_WithLowerPrice_Must_Fail() throws Exception {
		// Auction House and House exist already
		String uri = "/biddings/" ;
		final BiddingMapper mapper = new BiddingMapper("Mamadou", 2L, new BigDecimal(20000)) ;
		String inputJson = super.mapToJson(mapper) ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}

	@Test
	public void test_4_Bidding_FOR_SUCCESS() throws Exception {

		//Create already started auction
		// create Auction ID = 3
		String uriA = "/auctions/" ;
		final AuctionMapper mapperA = new AuctionMapper() ;		
		mapperA.setIdAuctionHouse(new Long(1));
		mapperA.setName("TestAuction");
		mapperA.setDescription("This is an test auction.");
		mapperA.setStartTime(LocalDateTime.of(2019, 11, 1, 15, 20, 42)); // STARTED
		mapperA.setEndTime(LocalDateTime.of(2020, 12, 1, 15, 20, 42));
		mapperA.setStartPrice(new BigDecimal(25000));
		mapperA.setCurrentPrice(new BigDecimal(25000));
		String inputJsonA = super.mapToJson(mapperA) ;
		MvcResult mvcResultA = mvc.perform(MockMvcRequestBuilders.post(uriA)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJsonA)).andReturn();
		int statusA = mvcResultA.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), statusA);


		String uri = "/biddings/" ;
		final BiddingMapper mapper = new BiddingMapper("Mamadou", 3L, new BigDecimal(26000)) ;
		String inputJson = super.mapToJson(mapper) ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}

}
