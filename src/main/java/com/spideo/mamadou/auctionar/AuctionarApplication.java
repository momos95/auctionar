package com.spideo.mamadou.auctionar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * 
 * @author mamadou
 * 
 * Auctionar is an web based application that manages auctions.
 * 
 * This is the entry point.
 *
 */
@SpringBootApplication
public class AuctionarApplication extends SpringBootServletInitializer{

	public AuctionarApplication(){ //NOSONAR
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AuctionarApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
        return builder.sources (AuctionarApplication.class);
    }

}
