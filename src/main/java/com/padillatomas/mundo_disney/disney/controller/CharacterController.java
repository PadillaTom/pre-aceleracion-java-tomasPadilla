package com.padillatomas.mundo_disney.disney.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padillatomas.mundo_disney.disney.dto.CharacterBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.CharacterDTO;
import com.padillatomas.mundo_disney.disney.service.CharacterService;



@RestController
@RequestMapping("characters")
public class CharacterController {
	
	// === Instanciamos SERVICE ===
	@Autowired
	private CharacterService charServ;
	
	
	// == GET ==
	
	// Basic All
	@GetMapping
	public ResponseEntity<List<CharacterBasicDTO>> getBasicCharacters(){
		List<CharacterBasicDTO> charDTO = charServ.getCharacterBasicList();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(charDTO);
	}
	
	// Details by Id
	@GetMapping("/details/{id}")
	public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id){
		CharacterDTO charDetails = charServ.getCharDetails(id);
		return ResponseEntity.status(HttpStatus.OK).body(charDetails);
	}
	
	// == POST ==	
	@PostMapping
	public ResponseEntity<CharacterDTO> postNewCharacter(@RequestBody CharacterDTO newChar){
		CharacterDTO createdChar = charServ.saveNewCharacter(newChar);
		return ResponseEntity.status(HttpStatus.OK).body(createdChar);
	}
	
	// == PUT ==	
	
	// == DELETE ==	
	
}
