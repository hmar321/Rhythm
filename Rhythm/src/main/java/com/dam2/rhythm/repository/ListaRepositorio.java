package com.dam2.rhythm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dam2.rhythm.model.Lista;
import com.dam2.rhythm.model.Usuario;

public interface ListaRepositorio extends CrudRepository<Lista, Integer> {
	List<Lista> findByUsuario(Usuario usuario);
	Optional<Lista> findById(Integer id);
}
