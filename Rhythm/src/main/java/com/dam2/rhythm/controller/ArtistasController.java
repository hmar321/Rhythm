package com.dam2.rhythm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.repository.ArtistaRepositorio;
import com.dam2.rhythm.util.Utilidades;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArtistasController {
	@Autowired
	private ArtistaRepositorio artisRepos;

	@GetMapping(path = "/listaartistas")
	public String listaArtistas(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("artistas listado");
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/avisosession";
		}
		List<Artista> listaArtistas = Utilidades.listArtistas(artisRepos.findAll());
		modelo.addAttribute("listaArtistas", listaArtistas);
		return "/listaartistas";
	}
}
