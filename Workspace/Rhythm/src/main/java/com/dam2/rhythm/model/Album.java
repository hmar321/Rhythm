package com.dam2.rhythm.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "albumes")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String titulo;
	private String musicasCaneda;
	@ManyToMany(mappedBy = "albumes")
	private List<Musica> musicas;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMusicasCaneda() {
		return musicasCaneda;
	}
	public void setMusicasCaneda(String musicasCaneda) {
		this.musicasCaneda = musicasCaneda;
	}
	public List<Musica> getMusicas() {
		return musicas;
	}
	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", titulo=" + titulo + ", musicasCaneda=" + musicasCaneda + ", musicas=" + musicas.size()
				+ "]";
	}
	
}
