package com.padillatomas.mundo_disney.disney.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter
@Setter
public class CharacterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String imageUrl;
	
	private String name;
	
	private int age;
	
	private double weight;
	
	private String history;
	
	// Soft Delete:
	private boolean deleted = Boolean.FALSE;
	
	// ManyToMany: Peliculas
	@ManyToMany(mappedBy = "movieCharacters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MovieEntity> characterMovies = new ArrayList<>();
	
	

}
