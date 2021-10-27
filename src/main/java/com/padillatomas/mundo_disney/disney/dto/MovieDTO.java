package com.padillatomas.mundo_disney.disney.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
	
	private Long id;
	private String imageUrl;
	private String title;
	private String creationDate;
	private List<CharacterDTO> movieCharacters;

}
