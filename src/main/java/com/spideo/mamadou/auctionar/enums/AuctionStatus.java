package com.spideo.mamadou.auctionar.enums;
/**
 * 
 * @author Mamadou
 *
 */
public enum AuctionStatus {

	/**
	 * If startTime < now
	 */
    NOT_STARTED,
    
    /**
     * startTime <= now < endTime
     */
    RUNNING,
    
    /**
     * endTime <= now
     */
    TERMINATED,
    
    DELETED
}
