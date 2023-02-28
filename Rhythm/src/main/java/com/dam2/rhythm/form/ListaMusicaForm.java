package com.dam2.rhythm.form;

import com.dam2.rhythm.model.Lista;
import com.dam2.rhythm.model.Musica;

import jakarta.validation.constraints.NotNull;

public class ListaMusicaForm {
	public Lista lista;
	@NotNull
	public Musica[] musica;

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public Musica[] getMusica() {
		return musica;
	}

	public void setMusica(Musica[] musica) {
		this.musica = musica;
	}

}
