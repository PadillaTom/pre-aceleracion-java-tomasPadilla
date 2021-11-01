package com.padillatomas.mundo_disney.disney.service;

import java.util.List;

import com.padillatomas.mundo_disney.disney.dto.GenreDTO;

public interface GenreService {

	// Post
	GenreDTO saveNewGenre(GenreDTO newGenre);

	// Get
	List<GenreDTO> getAllGenres();

	// Del
	void deleteGenreById(Long id);

	// PUT
	GenreDTO editGenreById(Long id, GenreDTO genreToEdit);

}
