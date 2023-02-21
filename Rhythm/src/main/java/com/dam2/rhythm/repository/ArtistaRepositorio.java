package com.dam2.rhythm.repository;

import org.springframework.data.repository.CrudRepository;

import com.dam2.rhythm.model.Artista;

public interface ArtistaRepositorio extends CrudRepository<Artista, Integer> {
	Artista findByNick(String nick);
}
