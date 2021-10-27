package com.padillatomas.mundo_disney.disney.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personaje")
@Getter
@Setter
public class Personaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String imagen;
	
	private String nombre;
	
	private int edad;
	
	private double peso;
	
	private String historia;
	
	// ManyToMany: Peliculas
	
	

}
