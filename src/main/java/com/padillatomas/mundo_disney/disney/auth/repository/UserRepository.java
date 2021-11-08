package com.padillatomas.mundo_disney.disney.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padillatomas.mundo_disney.disney.auth.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String Username);
	
}
