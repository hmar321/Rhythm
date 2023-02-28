package com.dam2.rhythm.dto;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "musicas")
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicasDTO {
	private List<MusicaDTO> musica;

	public MusicasDTO() {

	}

	public MusicasDTO(List<MusicaDTO> musica) {
		this.musica = musica;
	}

	public List<MusicaDTO> getMusicas() {
		return musica;
	}

	public void setMusicas(List<MusicaDTO> musicas) {
		this.musica = musicas;
	}

}
