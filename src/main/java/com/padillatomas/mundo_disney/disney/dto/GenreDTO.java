package com.padillatomas.mundo_disney.disney.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {
	
	private Long id;
	private String imageUrl;
	private String name;
	private List<MovieDTO> genreMovies;

}
