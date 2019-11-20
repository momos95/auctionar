package com.spideo.mamadou.auctionar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spideo.mamadou.auctionar.constants.ValidationMessages;
import com.spideo.mamadou.auctionar.enums.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Getter @Setter
public class User extends SpideoEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6486568850944725071L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id ;
	
	@NotNull(message = ValidationMessages.USER_NAME_EMPTY_NULL)
	@NotEmpty(message = ValidationMessages.USER_NAME_EMPTY_NULL)
	@Column(unique=true)
	private String name ;
	
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	protected Role role ;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
		
}
