package com.dam2.rhythm.form;

import java.text.SimpleDateFormat;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Genero;
import com.dam2.rhythm.model.Musica;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MusicaForm {
	@NotEmpty
	private String titulo;
	@NotNull
	private Artista artista;
	@NotEmpty
	private String estreno;
	@NotNull
	@NotEmpty
	private Genero[] genero;
	@NotEmpty
	private String embed;

	public MusicaForm(@NotEmpty String titulo, @NotNull Artista artista, @NotEmpty String estreno,
			@NotNull @NotEmpty Genero[] genero, @NotEmpty String embed) {
		super();
		this.titulo = titulo;
		this.artista = artista;
		this.estreno = estreno;
		this.genero = genero;
		this.embed = embed;
	}

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

	public Genero[] getGenero() {
		return genero;
	}

	public void setGenero(Genero[] genero) {
		this.genero = genero;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	public void pasarDatos(Musica musica) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		this.titulo = musica.getTitulo();
		this.artista = musica.getArtista();
		this.estreno = formater.format(musica.getEstreno());
		this.genero = musica.getGeneros().toArray(new Genero[musica.getGeneros().size()]);
		this.embed = musica.getEmbed();

	}

}