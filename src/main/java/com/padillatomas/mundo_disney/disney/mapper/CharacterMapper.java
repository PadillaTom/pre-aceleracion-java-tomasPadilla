package com.padillatomas.mundo_disney.disney.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.padillatomas.mundo_disney.disney.dto.CharacterBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.CharacterDTO;
import com.padillatomas.mundo_disney.disney.entity.CharacterEntity;

@Component
public class CharacterMapper {
	
	// Movie Mapper
	@Autowired
	private MovieMapper movieMapper;

	
	// === DTO -> Entity ===
	public CharacterEntity charDTO2Entity(CharacterDTO newChar) {
		CharacterEntity newEntity = new CharacterEntity();
		newEntity.setImageUrl(newChar.getImageUrl());
		newEntity.setName(newChar.getName());
		newEntity.setAge(newChar.getAge());
		newEntity.setWeight(newChar.getWeight());
		newEntity.setHistory(newChar.getHistory());	
		
		// TODO : PASAR ARRAY MOVIESID
		return newEntity;
	}
		
	// === Entity -> DTO ===
	public CharacterDTO entity2DTO(CharacterEntity savedEntity, Boolean fetchMovies) {
		CharacterDTO newDTO = new CharacterDTO();
		newDTO.setId(savedEntity.getId());
		newDTO.setImageUrl(savedEntity.getImageUrl());
		newDTO.setName(savedEntity.getName());
		newDTO.setAge(savedEntity.getAge());
		newDTO.setWeight(savedEntity.getWeight());
		newDTO.setHistory(savedEntity.getHistory());		
		if(fetchMovies) {
			newDTO.setCharacterMovies(movieMapper.movieEntityList2DTOList(savedEntity.getCharacterMovies(), false));		
		}		
		return newDTO;
	}		
	
	// === List<Entity> -> List<DTO> ===
	public List<CharacterDTO> charListEntity2DTOList(List<CharacterEntity> movieCharacters, boolean b) {
		List<CharacterDTO> newList = new ArrayList<>();
		for(CharacterEntity ent: movieCharacters) {
			newList.add(this.entity2DTO(ent, b));
		}
		return newList;
	}
	
	
	// === List<DTO> -> List<Entity> ===
	
	
	//
	// BASIC
	//
	
	// === Entity -> BasicDTO ===
		private CharacterBasicDTO charEntity2BasicDTO(CharacterEntity ch) {
			CharacterBasicDTO dto = new CharacterBasicDTO();
			dto.setImageUrl(ch.getImageUrl());
			dto.setName(ch.getName());
			return dto;
		}
	
	// === BasicList<Entity> -> BasicList<DTO> ===
	public List<CharacterBasicDTO> basicListEntity2DTO(List<CharacterEntity> myList) {
		List<CharacterBasicDTO> listDTO = new ArrayList<>();
		for (CharacterEntity ch : myList) {
			listDTO.add(this.charEntity2BasicDTO(ch));
		}
		return listDTO;
	}		

}
