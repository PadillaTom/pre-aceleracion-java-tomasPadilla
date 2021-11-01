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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "genres")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class GenreEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private String imageUrl;
	
	// Soft Delete:
	private boolean deleted = Boolean.FALSE;		
	
	// Has Many Movies
	@ManyToMany(mappedBy = "movieGenres", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MovieEntity> genreMovies = new ArrayList<>();

}
