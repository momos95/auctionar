package com.spideo.mamadou.auctionar.beans;

import com.spideo.mamadou.auctionar.entities.SEntity;

/**
 * 
 * @author Mamadou
 * 
 * This bean is used in order to get sql request result from services to controller.
 *
 */
public class ActivityResponse {
	
	/**
	 * allows to know if we did encounter problem while executing functional process
	 */
	private boolean error ;
	
	/**
	 * corresponds to the entity that is returned by repositories actions
	 */
	private SEntity entity ;
	
	public ActivityResponse(){
		error = true ;
	}	
	
	public SEntity getEntity() {
		return entity;
	}

	public void setEntity(SEntity entity) {
		this.entity = entity;
	}

	public boolean hasError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
