package com.padillatomas.mundo_disney.disney.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.padillatomas.mundo_disney.disney.dto.MovieBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.MovieDTO;
import com.padillatomas.mundo_disney.disney.entity.MovieEntity;

@Component
public class MovieMapper {

	//
	// === DTO -> Entity ===
	public MovieEntity movieDTO2Entity(MovieDTO dto) {
		MovieEntity ent = new MovieEntity();
		ent.setId(dto.getId());
		ent.setImageUrl(dto.getImageUrl());
		ent.setTitle(dto.getTitle());
		ent.setRating(dto.getRating());
		ent.setCreationDate(this.String2LocalDate(dto.getCreationDate()));
		return ent;
	}
	
	
	// === Entity -> DTO ===
	public MovieDTO entity2DTO(MovieEntity dbMovie) {
		MovieDTO dto = new MovieDTO();
		dto.setId(dbMovie.getId());
		dto.setImageUrl(dbMovie.getImageUrl());
		dto.setTitle(dbMovie.getTitle());	
		dto.setRating(dbMovie.getRating());
		dto.setCreationDate(this.localDate2String(dbMovie.getCreationDate()));
		
		return dto;
	}
	//
	// === List<DTO> -> List<Entity> ===
	
	//
	// === List<Entity> -> List<DTO> ===

	// BASIC

	//=== Entity -> BasicDTO ===
	public MovieBasicDTO entity2BasicDTO(MovieEntity ent) {
		MovieBasicDTO dto = new MovieBasicDTO();
		dto.setImageUrl(ent.getImageUrl());
		dto.setTitle(ent.getTitle());	
		dto.setCreationDate(this.localDate2String(ent.getCreationDate()));
		return dto;
	}

	//=== List<Entity> -> List<BasicDTO> ===
	public List<MovieBasicDTO> entityList2BasicDTO(List<MovieEntity> dbList) {
		List<MovieBasicDTO> newList = new ArrayList<>();
		for(MovieEntity ent : dbList) {
			newList.add(this.entity2BasicDTO(ent));
		}
		return newList;
	}


	// --> Utils <--
	public LocalDate String2LocalDate(String enteredDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate transformedDate = LocalDate.parse(enteredDate, formatter);
		return transformedDate;
	}
	public String localDate2String(LocalDate dbDate) {
		String formattedDate = dbDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));	
		return formattedDate;
	}
}
	

