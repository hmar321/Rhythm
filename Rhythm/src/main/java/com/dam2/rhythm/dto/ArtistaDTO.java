package com.dam2.rhythm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Integrante;

public class ArtistaDTO implements Serializable {
	private String nick;
	private List<IntegranteDTO> integrantes;

	public ArtistaDTO(String nick, List<IntegranteDTO> integrantes) {
		this.nick = nick;
		this.integrantes = integrantes;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<IntegranteDTO> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<IntegranteDTO> integrantes) {
		this.integrantes = integrantes;
	}

	public Artista mapingToArtista() {
		List<Integrante> aux = new ArrayList<>();
		for (IntegranteDTO integranteDTO : integrantes) {
			aux.add(integranteDTO.mapingToIntegrante());
		}
		return new Artista(nick, aux);
	}

}
