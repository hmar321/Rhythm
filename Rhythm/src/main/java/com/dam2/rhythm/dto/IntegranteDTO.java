package com.dam2.rhythm.dto;

import java.io.Serializable;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Integrante;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "integrante")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "nick", "nombre", "apellido" })
public class IntegranteDTO implements Serializable {
	private String nick;
	private String nombre;
	private String apellido;

	public IntegranteDTO() {
	}

	public IntegranteDTO(String nick, String nombre, String apellido) {
		this.nick = nick;
		this.nombre = nombre;
		this.apellido = apellido;
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

	public Integrante mappingToIntegrante() {
		return new Integrante(nick, nombre, apellido);
	}

}
