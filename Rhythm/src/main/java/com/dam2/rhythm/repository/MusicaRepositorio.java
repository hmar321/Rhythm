package com.dam2.rhythm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Musica;

public interface MusicaRepositorio extends CrudRepository<Musica, Integer> {
	Musica findByArtistaAndTitulo(Artista artista, String titulo);
	Optional<Musica> findById(Integer id);
}
