package com.dam2.rhythm.dto;

import java.io.Serializable;

import com.dam2.rhythm.model.Genero;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "genero")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "nombre", "descripcion" })
public class GeneroDTO implements Serializable{
	private String nombre;
	private String descripcion;
	
	public GeneroDTO() {
	}

	public GeneroDTO(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Genero mappingToGenero() {
		return new Genero(nombre, descripcion);
	}
}
