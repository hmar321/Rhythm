package com.dam2.rhythm.dto;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artistas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArtistasDTO {
	private List<ArtistaDTO> artista;

	public ArtistasDTO() {
	}

	public ArtistasDTO(List<ArtistaDTO> artistas) {
		this.artista = artistas;
	}

	public List<ArtistaDTO> getArtistas() {
		return artista;
	}

	public void setArtistas(List<ArtistaDTO> artistas) {
		this.artista = artistas;
	}
}
