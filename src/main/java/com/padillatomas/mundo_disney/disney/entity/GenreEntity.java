package com.padillatomas.mundo_disney.disney.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "genres")
@Getter
@Setter
public class GenreEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private String imageUrl;
	
	// Soft Delete:
	private boolean deleted = Boolean.FALSE;		
	
	// ManyToMany: Peliculas
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
			}, fetch = FetchType.LAZY)
	@JoinTable(
			name = "genre_movies",
			joinColumns= @JoinColumn(name = "genre_id"),
			inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<MovieEntity> genreMovies = new ArrayList<>();
	
	
	// Metodos:
	
	// addMovie
	// removeMovie
	

}
