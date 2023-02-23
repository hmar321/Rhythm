package com.dam2.rhythm.dto;

import java.util.List;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Integrante;

public class ArtistaDTO {
	private String nick;
	private List<Integrante> integrantes;
	
	public ArtistaDTO(String nick, List<Integrante> integrantes) {
		this.nick = nick;
		this.integrantes = integrantes;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public List<Integrante> getIntegrantes() {
		return integrantes;
	}
	public void setIntegrantes(List<Integrante> integrantes) {
		this.integrantes = integrantes;
	}
	public Artista mapingToArtista() {
		return new Artista(nick, integrantes);
	}
	
}
