package com.padillatomas.mundo_disney.disney.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padillatomas.mundo_disney.disney.dto.GenreDTO;
import com.padillatomas.mundo_disney.disney.service.GenreService;

@RestController
@RequestMapping("genres")
public class GenreController {

	// === Instanciamos SERVICE ===
	@Autowired
	private GenreService genreServ;
	
	// == POST ==
	@PostMapping
	public ResponseEntity<GenreDTO> postNewGenre(@RequestBody GenreDTO newGenre){
		GenreDTO savedGenre = genreServ.saveNewGenre(newGenre);		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedGenre);
	}
	
	// == GET ==
	@GetMapping("/all")
	public ResponseEntity<List<GenreDTO>> getAllGenreList(){
		List<GenreDTO> genreList = genreServ.getAllGenres();
		return ResponseEntity.status(HttpStatus.OK).body(genreList);
	}
	
	// == PUT ==
	// == DELETE ==
	
}
