package com.padillatomas.mundo_disney.disney.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padillatomas.mundo_disney.disney.dto.MovieBasicDTO;
import com.padillatomas.mundo_disney.disney.dto.MovieDTO;
import com.padillatomas.mundo_disney.disney.dto.MovieFiltersDTO;
import com.padillatomas.mundo_disney.disney.entity.CharacterEntity;
import com.padillatomas.mundo_disney.disney.entity.GenreEntity;
import com.padillatomas.mundo_disney.disney.entity.MovieEntity;
import com.padillatomas.mundo_disney.disney.exception.ParamNotFound;
import com.padillatomas.mundo_disney.disney.mapper.MovieMapper;
import com.padillatomas.mundo_disney.disney.repository.MovieRepository;
import com.padillatomas.mundo_disney.disney.repository.specifications.MovieSpecifications;
import com.padillatomas.mundo_disney.disney.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	// Repository:
	@Autowired
	private MovieRepository movieRepo;
	
	// Mapper:
	@Autowired
	private MovieMapper movieMapper;
	
	// Service:
	@Autowired
	private GenreServiceImpl genreServ;
	@Autowired
	private CharacterServiceImpl charServ;
	
	// Specifications:
	@Autowired
	private MovieSpecifications movieSpecs;
	
	// == GET ==
	@Override
	public List<MovieBasicDTO> getBasicMoviesList() {
		List<MovieEntity> dbList = movieRepo.findAll();
		List<MovieBasicDTO> resultDTO = movieMapper.entityList2BasicDTO(dbList);		
		return resultDTO;
	}

	@Override
	public MovieDTO getMovieDetails(Long id) {		
		MovieEntity dbMovie = this.handleFindById(id);
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
	
	@Override
	public void addCharacterToMovie(Long movieId, Long charId) {		
		MovieEntity savedMovie = this.handleFindById(movieId);
		CharacterEntity savedChar = charServ.handleFindById(charId);		
		savedMovie.getMovieCharacters().size();
		savedMovie.addCharacterToMovie(savedChar);			
		movieRepo.save(savedMovie);		
	}
	
	@Override
	public void addGenreToMovie(Long movieId, Long genreId) {	
		MovieEntity savedMovie = this.handleFindById(movieId);
		GenreEntity savedGenre = genreServ.handleFindById(genreId);			
		savedMovie.getMovieGenres().size();
		savedMovie.addGenreToMovie(savedGenre);		
		movieRepo.save(savedMovie);		
	}	
	
	// == PUT ==
	@Override
	public MovieDTO editMovieById(Long id, MovieDTO movieToEdit) {		
		MovieEntity savedMovie = this.handleFindById(id);			
		savedMovie.setImageUrl(movieToEdit.getImageUrl());
		savedMovie.setTitle(movieToEdit.getTitle());
		savedMovie.setRating(movieToEdit.getRating());
		savedMovie.setCreationDate(movieMapper.String2LocalDate(movieToEdit.getCreationDate()));		
		MovieEntity editedMovie = movieRepo.save(savedMovie);		
		MovieDTO resultDTO = movieMapper.entity2DTO(editedMovie, false);		
		return resultDTO;
	}

	// == DELETE ==
	@Override
	public void deleteMovieById(Long id) {
		movieRepo.deleteById(id);
	}

	// == FILTERS ==
	@Override
	public List<MovieDTO> getByFilters(String title, Set<Long> genre, String order) {
		MovieFiltersDTO movieFilters = new MovieFiltersDTO(title, genre, order);		
		List<MovieEntity> entityList = movieRepo.findAll(movieSpecs.getFiltered(movieFilters));
		List<MovieDTO> resultDTO = movieMapper.movieEntityList2DTOList(entityList, true);		
		return resultDTO;
	}	
	
	// == ERROR HANDLING ==
	public MovieEntity handleFindById(Long id) {
		Optional<MovieEntity> toBeFound = movieRepo.findById(id);
		if(!toBeFound.isPresent()) {
			throw new ParamNotFound("No Movie for id: " + id);
		}
		return toBeFound.get();
	}

}
