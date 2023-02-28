package com.dam2.rhythm.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "generos")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenerosDTO implements Serializable{
	private List<GeneroDTO> genero;

	public GenerosDTO() {
	}

	public GenerosDTO(List<GeneroDTO> genero) {
		this.genero = genero;
	}

	public List<GeneroDTO> getGenero() {
		return genero;
	}

	public void setGenero(List<GeneroDTO> genero) {
		this.genero = genero;
	}
	
}
