package com.spideo.mamadou.auctionar.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class SpideoEntity implements SEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1198689483281438369L;
	
	@CreationTimestamp
	protected LocalDateTime createdAt ;
	
	@UpdateTimestamp
	protected LocalDate lastUpdateDate ;		
		
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDate lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
		
}
