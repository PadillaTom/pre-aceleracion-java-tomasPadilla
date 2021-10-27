package com.padillatomas.mundo_disney.disney.entity;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pelicula")
@Getter
@Setter
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String imagen;
	
	private String titulo;
	
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;
	
	private double calificacion;
	
	// ManyToMany: Personajes.
	
	
	

}
