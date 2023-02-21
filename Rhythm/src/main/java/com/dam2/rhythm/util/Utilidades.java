package com.dam2.rhythm.util;

import java.util.ArrayList;
import java.util.List;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Genero;
import com.dam2.rhythm.model.Lista;
import com.dam2.rhythm.model.Musica;
import com.dam2.rhythm.model.Usuario;

public class Utilidades {
	public static List<Musica> listMusicas(Iterable<Musica> itMusicas) {
		List<Musica> listaMusicas = new ArrayList<>();
		itMusicas.forEach(listaMusicas::add);
		System.out.println(listaMusicas.size());
		return listaMusicas;
	}

	public static List<Artista> listArtistas(Iterable<Artista> itArtista) {
		List<Artista> listaArtistas = new ArrayList<>();
		itArtista.forEach(listaArtistas::add);
		System.out.println(listaArtistas.size());
		return listaArtistas;
	}

	public static List<Genero> listGeneros(Iterable<Genero> itGenero) {
		List<Genero> listaGeneros = new ArrayList<>();
		itGenero.forEach(listaGeneros::add);
		System.out.println(listaGeneros.size());
		return listaGeneros;
	}
	public static List<Usuario> listUsuarios(Iterable<Usuario> itUsuarios) {
		System.out.println("itUsuarios:" + itUsuarios);
		List<Usuario> listaUsuarios = new ArrayList<>();
		itUsuarios.forEach(listaUsuarios::add);
		return listaUsuarios;
	}
	public static List<Lista> listListas(Iterable<Lista> itListas) {
		System.out.println("itListas:" + itListas);
		List<Lista> listaUsuarios = new ArrayList<>();
		itListas.forEach(listaUsuarios::add);
		return listaUsuarios;
	}

}
