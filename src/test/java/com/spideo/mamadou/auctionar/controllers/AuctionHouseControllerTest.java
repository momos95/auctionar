package com.spideo.mamadou.auctionar.controllers;

import static org.junit.Assert.assertEquals;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuctionHouseControllerTest extends SpideoAbstractTest{

	@Override
	@Before
	public void setUp() {		
		super.setUp();
	}

	@Test
	public void test_1_GetAuctionHousesList_Success() throws Exception {
		String uri = "/auctionhouses/" ;
		AuctionHouseMapper mapper = new AuctionHouseMapper("Maison de vente aux enchères 1") ;

		String inputJson = super.mapToJson(mapper) ;

		mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals(mvcResult.getResponse().getStatus(), 200);
		
	}	

	@Test
	public void test_2_GetNoExistingAuctionHouse_Fail () throws Exception {
		String uri = "/auctionhouses/0" ;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();	
		assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK);
	}

	/**
	 * Method that test AuctionHouse creation with two test cases : 
	 * 		_ creation of no existing auction house<br>
	 * 		_ trying to create an existing auction house<br>
	 * @throws Exception
	 */
	@Test
	public void test_3_CreateAuctionHouse_TwoTestCases_SUCCESS() throws Exception {
		String uri = "/auctionhouses/" ;

		AuctionHouseMapper mapper = new AuctionHouseMapper("Maison de vente aux enchères 2") ;

		String inputJson = super.mapToJson(mapper) ;

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);

		// Trying to recreate new auction house with same name.

		MvcResult mvcResultBis = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int statusBis = mvcResultBis.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), statusBis);
	}

	/**
	 * Method to test AuctionHouse creation with empty name.
	 * Excepted 
	 */
	@Test
	public void test_4_CreateAuctionHouse_WithEmptyName_Fail() throws Exception {
		
		String uri = "/auctionhouses/" ;
		AuctionHouseMapper mapper = new AuctionHouseMapper() ;
		String inputJson = super.mapToJson(mapper) ;

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);
	}
}
