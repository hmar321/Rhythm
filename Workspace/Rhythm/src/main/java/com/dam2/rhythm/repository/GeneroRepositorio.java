package com.dam2.rhythm.repository;

import org.springframework.data.repository.CrudRepository;

import com.dam2.rhythm.model.Genero;

public interface GeneroRepositorio extends CrudRepository<Genero, Integer> {
	Genero findByNombre(String nombre);
}
