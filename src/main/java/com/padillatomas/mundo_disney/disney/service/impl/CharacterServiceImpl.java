package com.padillatomas.mundo_disney.disney.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.mundo_disney.disney.dto.CharacterBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.CharacterDTO;
import com.padillatomas.mundo_disney.disney.entity.CharacterEntity;
import com.padillatomas.mundo_disney.disney.mapper.CharacterMapper;
import com.padillatomas.mundo_disney.disney.repository.CharacterRepository;
import com.padillatomas.mundo_disney.disney.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	// Repository:
	@Autowired
	private CharacterRepository charRepo;
	
	// Mapper:
	@Autowired
	private CharacterMapper charMapper;
	

	// == GET ==
	@Override
	public List<CharacterBasicDTO> getCharacterBasicList() {	
		List<CharacterEntity> myList = charRepo.findAll();	
		List<CharacterBasicDTO> resultDTO = charMapper.basicListEntity2DTO(myList);			
		return resultDTO;
	}
	
	@Override
	public CharacterDTO getCharDetails(Long id) {
		// TODO : Change to FINDBY and ERRORHANDLING
		CharacterEntity dbChar = charRepo.getById(id);		
		CharacterDTO resultDTO = charMapper.entity2DTO(dbChar);
		return resultDTO;		
	}

	// == POST ==
	@Override
	public CharacterDTO saveNewCharacter(CharacterDTO newChar) {
		System.out.println(newChar.getHistory());
		CharacterEntity newEntity = charMapper.charDTO2Entity(newChar);
		System.out.println(newChar.getHistory());
		CharacterEntity savedEntity = charRepo.save(newEntity);
		CharacterDTO savedChar = charMapper.entity2DTO(savedEntity);		
		return savedChar;
	}	

	
	// == GET ==
	// == GET ==
	
	// == METHODS ==
	
	
	
}
