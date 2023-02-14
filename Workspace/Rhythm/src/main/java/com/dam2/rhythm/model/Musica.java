package com.dam2.rhythm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Musica {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String titulo;
	@JoinTable(name = "rel_musicas_albumes", joinColumns = @JoinColumn(name = "FK_MUSICA", nullable = false), inverseJoinColumns = @JoinColumn(name = "FK_ALBUM", nullable = false))
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Album> albumes;
	@JoinTable(name = "rel_musicas_listas", joinColumns = @JoinColumn(name = "FK_MUSICA", nullable = false), inverseJoinColumns = @JoinColumn(name = "FK_LISTA", nullable = false))
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Lista> listas;
	@JoinTable(name = "rel_musicas_generos", joinColumns = @JoinColumn(name = "FK_MUSICA", nullable = false), inverseJoinColumns = @JoinColumn(name = "FK_GENERO", nullable = false))
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Genero> generos;
	@ManyToOne
	@JoinColumn(name = "artista_id")
	private Artista artista;
	private LocalDate estreno;

	public Musica() {
	}

	public Musica(String titulo, Artista artista, String estreno) {
		this.titulo = titulo;
		this.albumes = new ArrayList<>();
		this.listas = new ArrayList<>();
		this.generos = new ArrayList<>();
		this.artista = artista;
		this.estreno = LocalDate.parse(estreno);
	}

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

	public List<Album> getAlbumes() {
		return albumes;
	}

	public void setAlbumes(List<Album> albumes) {
		this.albumes = albumes;
	}

	public List<Lista> getListas() {
		return listas;
	}

	public void setListas(List<Lista> listas) {
		this.listas = listas;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public LocalDate getEstreno() {
		return estreno;
	}

	public void setEstreno(LocalDate estreno) {
		this.estreno = estreno;
	}

	@Override
	public String toString() {
		return "Musica [id=" + id + ", titulo=" + titulo + ", albumes=" + albumes + ", listas=" + listas + ", generos="
				+ generos.size() + ", artista=" + artista.getNick() + ", estreno=" + estreno + "]";
	}

}
