package com.dam2.rhythm.dto;

import java.io.Serializable;

import com.dam2.rhythm.model.Integrante;

public class IntegranteDTO implements Serializable{
	private String nick;
	private String nombre;
	private String apellido;
	private ArtistaDTO artista;

	public IntegranteDTO(String nick, String nombre, String apellido, ArtistaDTO artista) {
		this.nick = nick;
		this.nombre = nombre;
		this.apellido = apellido;
		this.artista = artista;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public ArtistaDTO getArtista() {
		return artista;
	}

	public void setArtista(ArtistaDTO artista) {
		this.artista = artista;
	}

	public Integrante mapingToIntegrante() {
		return new Integrante(nick, nombre, apellido, artista.mapingToArtista());
	}
}
