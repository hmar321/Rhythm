package com.dam2.rhythm.model;

import java.util.ArrayList;
import java.util.List;

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
	private String nick;
	private String integrantesCadenna;
	@OneToMany(mappedBy = "artista")
	private List<Integrante> integrantes;

	public Artista() {
	}

	public Artista(String nick) {
		this.nick = nick;
		this.integrantes = new ArrayList<>();
		actualizarIntegrantesCad();
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

	public String getIntegrantesCadenna() {
		actualizarIntegrantesCad();
		return integrantesCadenna;
	}

	public void setIntegrantesCadenna(String integrantesCadenna) {
		this.integrantesCadenna = integrantesCadenna;
	}

	public List<Integrante> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<Integrante> integrantes) {
		this.integrantes = integrantes;
	}

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nick=" + nick + ", integrantesCadenna=" + getIntegrantesCadenna() + ", integrantes="
				+ integrantes.size() + "]";
	}

	private void actualizarIntegrantesCad() {
		StringBuffer cad = new StringBuffer();
		for (Integrante integrante : integrantes) {
			cad.append(", " + integrante.getNombre() + " " + integrante.getApellido());
		}
		integrantesCadenna = cad.toString().replaceFirst(", ", "");
	}

}
