package com.dam2.rhythm.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "listas")
public class Lista {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	private String titulo;
	@ManyToMany(mappedBy = "listas")
	private List<Musica> musicas;
	private Integer numMusicas;

	
	public Lista() {
	}

	public Lista(Usuario usuario, String titulo) {
		this.usuario = usuario;
		this.titulo = titulo;
		this.musicas=new ArrayList<>();
		this.numMusicas=0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
		this.numMusicas = musicas.size();
	}

	public Integer getNumMusicas() {
		return musicas.size();
	}

	public void setNumMusicas(Integer numMusicas) {
		this.numMusicas = numMusicas;
	}

	public void add(Musica musica) {
		musicas.add(musica);
		this.numMusicas = musicas.size();
	}

	@Override
	public String toString() {
		return "Lista [id=" + id + ", usuario=" + usuario.getEmail() + ", titulo=" + titulo + ", musicas="
				+ musicas.size() + "]";
	}

}
