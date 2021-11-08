package com.padillatomas.mundo_disney.disney.auth.service;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.padillatomas.mundo_disney.disney.auth.dto.UserDTO;
import com.padillatomas.mundo_disney.disney.auth.entity.UserEntity;
import com.padillatomas.mundo_disney.disney.auth.repository.UserRepository;
import com.padillatomas.mundo_disney.disney.service.EmailService;

@Service
public class UserDetailsCustomService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EmailService emailServ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Traemos User: 
		UserEntity foundUser = userRepo.findByUsername(username);
		
		// IF No User: Exception
		if (foundUser == null) {
			throw new UsernameNotFoundException("Username: " + username + " -> NOT FOUND");
		}
		
		// IF User: Creamos un Nuevo User Object con datos del UserEncontrado.
		return new User(
				foundUser.getUsername(),
				foundUser.getPassword(), 
				Collections.emptyList() // Roles
			);
	}
	
	// Signup New User:
	public boolean signupUser(@Valid UserDTO userToCreate) {
		// === 
		// Podriamos Tener un MAPPER
		UserEntity newUser = new UserEntity();
		newUser.setUsername(userToCreate.getUsername());
		newUser.setPassword(userToCreate.getPassword());	
		// === 
		
		UserEntity matchingUser = userRepo.findByUsername(userToCreate.getUsername());			
		if(matchingUser != null && (matchingUser.getUsername().equals(newUser.getUsername()))) {
			// NO LO CREA, PERO NO ENVIA "Already Exists"
			// En Controller verificamos TRUE o FALSE y Mandamos ResponseEntity segun corresponda
			return false;
		}		
		newUser = userRepo.save(newUser);
		
		//Email Stuff:
		if(newUser != null) {
			emailServ.sendWelcomeEmail(newUser.getUsername());
		}
		
		return newUser != null;		
	}	

}
