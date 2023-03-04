package com.dam2.rhythm.repository;

import org.springframework.data.repository.CrudRepository;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Integrante;

public interface IntegranteRepositorio extends CrudRepository<Integrante, Integer> {
Integrante findByArtistaAndNombreAndApellido(Artista artista,String nombre,String apellido);
}