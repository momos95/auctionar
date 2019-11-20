package com.spideo.mamadou.auctionar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spideo.mamadou.auctionar.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByName(String name) ;
	public boolean existsByName(String name) ;
}
