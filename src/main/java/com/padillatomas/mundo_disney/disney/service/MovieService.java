package com.padillatomas.mundo_disney.disney.service;

import java.util.List;

import com.padillatomas.mundo_disney.disney.dto.MovieBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.MovieDTO;

public interface MovieService {

	// GET
	List<MovieBasicDTO> getBasicMoviesList();

	MovieDTO getMovieDetails(Long id);
	
	// POST
	MovieDTO saveNewMovie(MovieDTO newMovie);

}
