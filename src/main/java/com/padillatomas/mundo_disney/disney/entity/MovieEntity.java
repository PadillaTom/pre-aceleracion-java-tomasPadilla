package com.padillatomas.mundo_disney.disney.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "movies")
@Getter
@Setter
public class MovieEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String imageUrl;
	
	private String title;
	
	@Column(name = "creation_date")
	private LocalDate creationDate;
	
	private double rating;
	
	// Soft Delete:
	private boolean deleted = Boolean.FALSE;
		
	
	// ManyToMany: Personajes.
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
			}, fetch = FetchType.LAZY)
	@JoinTable(
			name = "movie_characters",
			joinColumns= @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "character_id"))
	private List<CharacterEntity> movieCharacters = new ArrayList<>(); 	
	
	// Metodos:
	
	// addCharacter
	// removeCharacter
	

}
