package com.padillatomas.mundo_disney.disney.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
	
	private Long id;
	private String imageUrl;
	private String name;
	private double weight;
	private String history;
	private List<MovieDTO> characterMovies;

}
