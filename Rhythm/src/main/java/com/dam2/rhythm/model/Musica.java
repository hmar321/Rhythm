package com.dam2.rhythm.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.dam2.rhythm.dto.GenerosDTO;
import com.dam2.rhythm.dto.MusicaDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	private static final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
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
	private Date estreno;
	@Column(unique=true)
	private String embed;

	public Musica() {
	}
	public Musica(String titulo, String estreno, String embed) throws ParseException {
		this(titulo, null, estreno, embed);
	}
	public Musica(String titulo, Artista artista, String estreno, String embed) throws ParseException {
		this.titulo = titulo;
		this.albumes = new ArrayList<>();
		this.listas = new ArrayList<>();
		this.generos = new ArrayList<>();
		this.artista = artista;
		this.estreno = formater.parse(estreno);
		this.embed = embed;
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

	public Date getEstreno() {
		return estreno;
	}

	public void setEstreno(Date estreno) {
		this.estreno = estreno;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	@Override
	public String toString() {
		return "Musica [id=" + id + ", titulo=" + titulo + ", albumes=" + albumes.size() + ", listas=" + listas.size()
				+ ", generos=" + generos.size() + ", artista=" + artista.getNick() + ", estreno=" + estreno +", embed=" + embed + "]";
	}
	
	public MusicaDTO mappingToMusicaDTO() {
		return new MusicaDTO(titulo, artista.getNick(), estreno, embed,new GenerosDTO(generos.stream().map(Genero::mappingToGeneroDTO).collect(Collectors.toList())));
	}

}