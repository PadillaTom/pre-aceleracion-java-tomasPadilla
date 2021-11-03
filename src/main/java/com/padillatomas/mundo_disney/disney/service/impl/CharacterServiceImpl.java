package com.padillatomas.mundo_disney.disney.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.mundo_disney.disney.dto.CharacterBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.CharacterDTO;
import com.padillatomas.mundo_disney.disney.dto.CharacterFiltersDTO;
import com.padillatomas.mundo_disney.disney.entity.CharacterEntity;
import com.padillatomas.mundo_disney.disney.exception.ParamNotFound;
import com.padillatomas.mundo_disney.disney.mapper.CharacterMapper;
import com.padillatomas.mundo_disney.disney.repository.CharacterRepository;
import com.padillatomas.mundo_disney.disney.repository.specifications.CharacterSpecification;
import com.padillatomas.mundo_disney.disney.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	// Repository:
	@Autowired
	private CharacterRepository charRepo;
	
	// Mapper:
	@Autowired
	private CharacterMapper charMapper;
	
	// Specifications:
	@Autowired
	private CharacterSpecification charSpecs;
	

	// == GET ==
	@Override
	public List<CharacterBasicDTO> getCharacterBasicList() {	
		List<CharacterEntity> myList = charRepo.findAll();	
		List<CharacterBasicDTO> resultDTO = charMapper.basicListEntity2DTO(myList);			
		return resultDTO;
	}
	
	@Override
	public CharacterDTO getCharDetails(Long id) {		
		CharacterEntity dbChar = this.handleFindById(id);	
		
		CharacterDTO resultDTO = charMapper.entity2DTO(dbChar, true);
		
		return resultDTO;		
	}

	// == POST ==
	@Override
	public CharacterDTO saveNewCharacter(CharacterDTO newChar) {
		CharacterEntity newEntity = charMapper.charDTO2Entity(newChar);
		CharacterEntity savedEntity = charRepo.save(newEntity);
		CharacterDTO savedChar = charMapper.entity2DTO(savedEntity, false);	
		
		return savedChar;
	}
	
	// == DELETE ==
	@Override
	public void deleteCharacterById(Long id) {
		charRepo.deleteById(id);		
	}
	
	// == PUT ==
	@Override
	public CharacterDTO editCharacterById(Long id, CharacterDTO charToEdit) {		
		CharacterEntity savedChar = this.handleFindById(id);
		
		savedChar.setImageUrl(charToEdit.getImageUrl());
		savedChar.setName(charToEdit.getName());
		savedChar.setAge(charToEdit.getAge());
		savedChar.setWeight(charToEdit.getWeight());
		savedChar.setHistory(charToEdit.getHistory());		
		CharacterEntity editedChar = charRepo.save(savedChar);
		CharacterDTO resultDTO = charMapper.entity2DTO(editedChar, false);	
		
		return resultDTO;
	}

	// == FILTERS ==
	@Override
	public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies) {
		CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
		List<CharacterEntity> entityList = charRepo.findAll(charSpecs.getFiltered(filtersDTO));
		List<CharacterDTO> resultDTO = charMapper.charListEntity2DTOList(entityList, true);
		return resultDTO;
	}	
	
	// == ERROR HANDLING ==
	public CharacterEntity handleFindById(Long id) {
		Optional<CharacterEntity> toBeFound = charRepo.findById(id);
		if(!toBeFound.isPresent()) {
			throw new ParamNotFound("No Character for id: " + id);
		}
		return toBeFound.get();
	}
	
}
