package com.dam2.rhythm.dto;

import java.io.Serializable;

import com.dam2.rhythm.model.Musica;

public class MusicaDTO implements Serializable{
	private String titulo;
	private ArtistaDTO artista;
	private String estreno;
	public MusicaDTO(String titulo, ArtistaDTO artista, String estreno) {
		this.titulo = titulo;
		this.artista = artista;
		this.estreno = estreno;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public ArtistaDTO getArtista() {
		return artista;
	}
	public void setArtista(ArtistaDTO artista) {
		this.artista = artista;
	}
	public String getEstreno() {
		return estreno;
	}
	public void setEstreno(String estreno) {
		this.estreno = estreno;
	}
	public Musica mapingToMusica() {
		return new Musica(titulo,artista.mapingToArtista(),estreno);
	}
	
}
