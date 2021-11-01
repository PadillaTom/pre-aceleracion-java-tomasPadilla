package com.padillatomas.mundo_disney.disney.service;

import java.util.List;

import com.padillatomas.mundo_disney.disney.dto.CharacterBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.CharacterDTO;

public interface CharacterService {

	// GET
	List<CharacterBasicDTO> getCharacterBasicList();
	CharacterDTO getCharDetails(Long id);
	
	// POST
	CharacterDTO saveNewCharacter(CharacterDTO newChar);
	
	// DEL
	void deleteCharacterById(Long id);
	
	// PUT
	CharacterDTO editCharacterById(Long id, CharacterDTO charToEdit);
}
