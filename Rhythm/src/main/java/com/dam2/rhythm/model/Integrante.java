package com.dam2.rhythm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Integrante {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nick;
	private String nombre;
	private String apellido;
	@ManyToOne
	@JoinColumn(name = "artista_id")
	private Artista artista;

	public Integrante() {
	}

	public Integrante(String nick, String nombre, String apellido, Artista artista) {
		this.nick = nick;
		this.nombre = nombre;
		this.apellido = apellido;
		this.artista = artista;
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

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	@Override
	public String toString() {
		return "Integrante [id=" + id + ", nick=" + nick + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", artista=" + artista.getNick() + "]";
	}

}
