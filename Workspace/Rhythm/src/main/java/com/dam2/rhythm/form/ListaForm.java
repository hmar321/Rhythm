package com.dam2.rhythm.form;

import com.dam2.rhythm.model.Usuario;

import jakarta.validation.constraints.NotNull;

public class ListaForm {
	@NotNull
	private Usuario usuario;
	@NotNull
	private String titulo;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
