package com.padillatomas.mundo_disney.disney.service;

import java.util.List;
import java.util.Set;

import com.padillatomas.mundo_disney.disney.dto.MovieBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.MovieDTO;

public interface MovieService {

	// GET
	List<MovieBasicDTO> getBasicMoviesList();

	MovieDTO getMovieDetails(Long id);
	
	// POST
	MovieDTO saveNewMovie(MovieDTO newMovie);
	void addCharacterToMovie(Long movieId, Long charId);
	void addGenreToMovie(Long movieId, Long genreId);

	// DEL
	void deleteMovieById(Long id);

	// PUT
	MovieDTO editMovieById(Long id, MovieDTO movieToEdit);

	// FILTERS
	List<MovieDTO> getByFilters(String name, Set<Long> genre, String order);

}
