package com.dam2.rhythm.form;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Genero;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

public class MusicaForm {
	@NotNull
	private String titulo;
	@NotNull
	private Artista artista;
	@NotEmpty
	private String estreno;
	@NotNull
	private Genero genero;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public String getEstreno() {
		return estreno;
	}

	public void setEstreno(String estreno) {
		this.estreno = estreno;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}
