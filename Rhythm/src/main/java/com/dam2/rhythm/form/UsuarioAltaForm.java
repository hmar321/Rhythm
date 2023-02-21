package com.dam2.rhythm.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioAltaForm {
	@Size(max = 30, message = "El campo nick puede tener un máximo de 30 caracteres")
	private String nick;
	@NotNull
	@Size(min = 3, max = 30, message = "El campo nombre debe tener entre 3 y 30 caracteres")
	private String nombre;
	@NotNull
	@Size(min = 3, max = 30, message = "El campo apellido debe tener entre 3 y 30 caracteres")
	private String apellido;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String password2;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
}
