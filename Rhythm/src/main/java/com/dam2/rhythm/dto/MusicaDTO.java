package com.dam2.rhythm.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dam2.rhythm.model.Musica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "musica")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "titulo", "artistaNick", "estreno", "embed", "generos" })
public class MusicaDTO implements Serializable {
	private static final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	private String titulo;
	private String artistaNick;
	private Date estreno;
	private String embed;
	private GenerosDTO generos;

	public MusicaDTO() {
	}

	public MusicaDTO(String titulo, String artistaNick, Date estreno, String embed, GenerosDTO generos) {
		this.titulo = titulo;
		this.artistaNick = artistaNick;
		this.estreno = estreno;
		this.embed = embed;
		this.generos = generos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtistaNick() {
		return artistaNick;
	}

	public void setArtistaNick(String artistaNick) {
		this.artistaNick = artistaNick;
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

	public GenerosDTO getGeneros() {
		return generos;
	}

	public void setGeneros(GenerosDTO generos) {
		this.generos = generos;
	}

	public Musica mappingToMusica() throws ParseException {
		return new Musica(titulo, formater.format(estreno), embed);
	}

}
