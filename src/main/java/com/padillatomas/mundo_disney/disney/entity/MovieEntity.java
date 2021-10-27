package com.padillatomas.mundo_disney.disney.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	// ManyToMany: Personajes.
	@OneToMany(cascade = CascadeType.ALL)
	private List<CharacterEntity> movieCharacters = new ArrayList<>(); 	
	

}
