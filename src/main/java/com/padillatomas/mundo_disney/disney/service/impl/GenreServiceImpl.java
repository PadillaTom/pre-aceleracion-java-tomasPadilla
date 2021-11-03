package com.padillatomas.mundo_disney.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.mundo_disney.disney.dto.GenreDTO;
import com.padillatomas.mundo_disney.disney.entity.GenreEntity;
import com.padillatomas.mundo_disney.disney.exception.ParamNotFound;
import com.padillatomas.mundo_disney.disney.mapper.GenreMapper;
import com.padillatomas.mundo_disney.disney.repository.GenreRepository;
import com.padillatomas.mundo_disney.disney.service.GenreService;


@Service
public class GenreServiceImpl implements GenreService {

	// Repository:
	@Autowired
	private GenreRepository genreRepo;
	// Mapper:
	@Autowired
	private GenreMapper genreMapper;
	
	// == POST ==
	@Override
	public GenreDTO saveNewGenre(GenreDTO newGenre) {
		GenreEntity newEntity = genreMapper.genreDTO2Entity(newGenre);
		GenreEntity savedGenre = genreRepo.save(newEntity);
		GenreDTO resultDTO = genreMapper.genreEntity2DTO(savedGenre, false);
		return resultDTO;
	}

	// == GET ==
	@Override
	public List<GenreDTO> getAllGenres() {
		List<GenreEntity> savedGenres = genreRepo.findAll();
		List<GenreDTO> resultDTO = genreMapper.genreEntityList2DTOList(savedGenres);
		return resultDTO;
	}
	
	// == PUT ==
	@Override
	public GenreDTO editGenreById(Long id, GenreDTO genreToEdit) {
		GenreEntity savedGenre = this.handleFindById(id);
		savedGenre.setImageUrl(genreToEdit.getImageUrl());
		savedGenre.setName(genreToEdit.getName());
		GenreEntity editedGenre = genreRepo.save(savedGenre);
		GenreDTO resultDTO = genreMapper.genreEntity2DTO(editedGenre, false);
		return resultDTO;
	}	

	// == DELETE ==
	@Override
	public void deleteGenreById(Long id) {
		genreRepo.deleteById(id);		
	}
	
	
	// :: Error Handling ::
	public GenreEntity handleFindById(Long id) {
		Optional<GenreEntity> toBeFound = genreRepo.findById(id);
		if(!toBeFound.isPresent()) {
			throw new ParamNotFound("No Genre for id: " + id);
		}
		return toBeFound.get();
	}

}
