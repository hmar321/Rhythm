package com.dam2.rhythm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dam2.rhythm.repository.ListaRepositorio;
import com.dam2.rhythm.repository.MusicaRepositorio;
import com.dam2.rhythm.repository.RolRepositorio;
import com.dam2.rhythm.repository.UsuarioRepositorio;

@Controller
public class MusicaController {
	@Autowired
	private RolRepositorio rolRepos;
	@Autowired
	private ListaRepositorio listaRepos;
	@Autowired
	private MusicaRepositorio musicRespos;

	
}
