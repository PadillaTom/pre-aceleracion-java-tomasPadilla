package com.padillatomas.mundo_disney.disney.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.mundo_disney.disney.dto.MovieBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.MovieDTO;
import com.padillatomas.mundo_disney.disney.entity.MovieEntity;
import com.padillatomas.mundo_disney.disney.mapper.MovieMapper;
import com.padillatomas.mundo_disney.disney.repository.MovieRepository;
import com.padillatomas.mundo_disney.disney.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	// Repository:
	@Autowired
	private MovieRepository movieRepo;
	// Mapper:
	@Autowired
	private MovieMapper movieMapper;
	
	// == GET ==
	@Override
	public List<MovieBasicDTO> getBasicMoviesList() {
		List<MovieEntity> dbList = movieRepo.findAll();
		List<MovieBasicDTO> resultDTO = movieMapper.entityList2BasicDTO(dbList);
		return resultDTO;
	}

	@Override
	public MovieDTO getMovieDetails(Long id) {
		//TODO -> OPTIONAL, ERROR HANDLING
		MovieEntity dbMovie = movieRepo.getById(id);
		MovieDTO resultDTO = movieMapper.entity2DTO(dbMovie, true);
		return resultDTO;
	}

	// == POST ==
	@Override
	public MovieDTO saveNewMovie(MovieDTO newMovie) {
		MovieEntity newEntity = movieMapper.movieDTO2Entity(newMovie);
		MovieEntity savedEntity = movieRepo.save(newEntity);
		MovieDTO resultDTO = movieMapper.entity2DTO(savedEntity, false);
		return resultDTO;
	}

}
