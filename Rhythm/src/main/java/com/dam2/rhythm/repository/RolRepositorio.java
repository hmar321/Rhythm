package com.dam2.rhythm.repository;

import org.springframework.data.repository.CrudRepository;

import com.dam2.rhythm.model.Rol;

public interface RolRepositorio extends CrudRepository<Rol, Integer> {
	Rol findByNombre(String nombre);
}