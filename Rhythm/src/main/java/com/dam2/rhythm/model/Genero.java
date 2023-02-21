package com.dam2.rhythm.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "generos")
public class Genero {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	@ManyToMany(mappedBy = "generos")
	private List<Musica> musicas;
	private String descripcion;

	public Genero() {
	}
	public Genero(String nombre) {
		this(nombre,"");
	}
	
	public Genero(String nombre,String descripcion) {
		this.nombre = nombre;
		this.musicas = new ArrayList<>();
		this.descripcion=descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Genero [id=" + id + ", nombre=" + nombre + ", musicas=" + musicas.size() + ", descripcion="
				+ descripcion.length() + "]";
	}

}
