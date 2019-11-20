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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuctionControllerTest extends SpideoAbstractTest{

	@Override
	@Before
	public void setUp() {		
		super.setUp();
	}

	
	@Test
	public void test_1_CreateAuction_AuctionWithEmptyData_Fail() throws Exception {		
		String uri = "/auctions/" ;
		final AuctionMapper mapper = new AuctionMapper() ;
		String inputJson = super.mapToJson(mapper) ;

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}
	
	@Test
	public void test_2_CreateAuction_WithAuctionHouseDoesntExist_Fail() throws Exception {
		String uri = "/auctions/" ;
		final AuctionMapper mapper = new AuctionMapper() ;
		mapper.setIdAuctionHouse(new Long(42));;;
		mapper.setName("TestAuction");
		mapper.setDescription("This is an test auction.");
		mapper.setStartTime(LocalDateTime.now());
		mapper.setEndTime(LocalDateTime.of(2020, 12, 1, 15, 20, 42));
		mapper.setStartPrice(new BigDecimal(25000));
		String inputJson = super.mapToJson(mapper) ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}
	
	@Test
	public void test_3_CreateAuction_WithEndDateIsBeforeStartDate_Fail() throws Exception{
		String uri = "/auctions/" ;
		final AuctionMapper mapper = new AuctionMapper() ;		
		mapper.setIdAuctionHouse(new Long(42));
		mapper.setName("TestAuction");
		mapper.setDescription("This is an test auction.");
		mapper.setStartTime(LocalDateTime.now());
		mapper.setEndTime(LocalDateTime.of(2018, 12, 1, 15, 20, 42)); // endTime < startTime
		mapper.setStartPrice(new BigDecimal(25000));
		String inputJson = super.mapToJson(mapper) ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_4_CreateAuction_WithCorrectData_Success() throws Exception{
		
		// Create Auction house First
		String uriAH = "/auctionhouses/" ;
		AuctionHouseMapper mapperAH = new AuctionHouseMapper("Maison de vente aux enchères 42") ;
		String inputJsonAH = super.mapToJson(mapperAH) ;
		MvcResult mvcResultAH = mvc.perform(MockMvcRequestBuilders.post(uriAH)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJsonAH)).andReturn();
		
		int statusAH = mvcResultAH.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), statusAH);
		
		// create Auction
		String uri = "/auctions/" ;
		final AuctionMapper mapper = new AuctionMapper() ;		
		mapper.setIdAuctionHouse(new Long(1));
		mapper.setName("TestAuction");
		mapper.setDescription("This is an test auction.");
		mapper.setStartTime(LocalDateTime.of(2019, 12, 1, 15, 20, 42));
		mapper.setEndTime(LocalDateTime.of(2020, 12, 1, 15, 20, 42));
		mapper.setStartPrice(new BigDecimal(25000));
		String inputJson = super.mapToJson(mapper) ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}
	
	@Test
	public void test_5_CreateAuction_WithAlreadyExistingSameAuctionInHouse_Fail() throws Exception {
		String uri = "/auctions/" ;
		final AuctionMapper mapper = new AuctionMapper() ;		
		mapper.setIdAuctionHouse(new Long(1));
		mapper.setName("TestAuction");
		mapper.setDescription("This is an test auction.");
		mapper.setStartTime(LocalDateTime.of(2019, 12, 1, 15, 20, 42));
		mapper.setEndTime(LocalDateTime.of(2020, 12, 1, 15, 20, 42));
		mapper.setStartPrice(new BigDecimal(25000));
		String inputJson = super.mapToJson(mapper) ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}
	
	@Test
	public void test_6_DeleteAuction_FOR_SUCCESS() throws Exception {
		String uri = "/auctions/2" ; // ID is important
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(HttpStatus.OK.value(), status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Specified Auction is successfully deleted.");
	}
	
}
