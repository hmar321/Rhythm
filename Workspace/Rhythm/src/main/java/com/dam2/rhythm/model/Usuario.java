package com.dam2.rhythm.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "rol_id")
	private Rol rol;
	private String nick;
	private String nombre;
	private String apellido;
	@Column(unique=true)
	private String email;
	private String password;
	@OneToMany(mappedBy = "usuario")
	private List<Lista> listas;
	
	public Usuario() {
	}
	
	public Usuario(Rol rol, String nick, String nombre, String apellido, String email, String password) {
		this.rol = rol;
		this.nick = nick.equals(null)||nick.length()==0?"Usuario":nick;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.listas=new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
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
	public List<Lista> getListas() {
		return listas;
	}
	public void setListas(List<Lista> lista) {
		this.listas = lista;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", rol=" + rol.getNombre() + ", nick=" + nick + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + email + ", password=" + password + ", lista=" + listas.size() + "]";
	}
	
}
