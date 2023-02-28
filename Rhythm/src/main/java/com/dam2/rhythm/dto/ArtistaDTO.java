package com.dam2.rhythm.dto;

import java.io.Serializable;

import com.dam2.rhythm.model.Artista;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "artista")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "nick", "integrantes" })
public class ArtistaDTO implements Serializable {
	private String nick;
	private IntegrantesDTO integrantes;

	public ArtistaDTO() {
	}

	public ArtistaDTO(String nick, IntegrantesDTO integrantes) {
		this.nick = nick;
		this.integrantes = integrantes;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public IntegrantesDTO getIntegrantesDTO() {
		return integrantes;
	}

	public void setIntegrantesDTO(IntegrantesDTO integrantes) {
		this.integrantes = integrantes;
	}
	public Artista mappingToArtista() {
		return new Artista(nick);
	}

}
