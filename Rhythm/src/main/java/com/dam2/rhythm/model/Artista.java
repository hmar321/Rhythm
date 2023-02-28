package com.dam2.rhythm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dam2.rhythm.dto.ArtistaDTO;
import com.dam2.rhythm.dto.IntegrantesDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique=true)
	private String nick;
	@OneToMany(mappedBy = "artista")
	private List<Integrante> integrantes;

	public Artista() {
	}

	public Artista(String nick) {
		this.nick = nick;
		this.integrantes = new ArrayList<>();
	}

	public Artista(String nick, List<Integrante> integrantes) {
		this.nick = nick;
		this.integrantes = new ArrayList<>(integrantes);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nick=" + nick + ", integrantes=" + integrantes.size() + "]";
	}

	public ArtistaDTO mappingToArtistaDTO() {
		return new ArtistaDTO(nick,new IntegrantesDTO(getIntegrantes().stream().map(Integrante::mappingToIntegranteDTO).collect(Collectors.toList())));
	}

}
