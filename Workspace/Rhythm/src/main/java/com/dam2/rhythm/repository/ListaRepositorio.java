package com.dam2.rhythm.repository;

import org.springframework.data.repository.CrudRepository;

import com.dam2.rhythm.model.Lista;
import com.dam2.rhythm.model.Usuario;

public interface ListaRepositorio extends CrudRepository<Lista, Integer> {
	Lista findByUsuario(Usuario usuario);
}
