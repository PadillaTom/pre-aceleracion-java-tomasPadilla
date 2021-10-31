package com.padillatomas.mundo_disney.disney.service;

import java.util.List;

import com.padillatomas.mundo_disney.disney.dto.GenreDTO;

public interface GenreService {

	GenreDTO saveNewGenre(GenreDTO newGenre);

	List<GenreDTO> getAllGenres();

}
