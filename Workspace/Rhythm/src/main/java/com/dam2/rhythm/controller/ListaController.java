package com.dam2.rhythm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam2.rhythm.form.ListaForm;
import com.dam2.rhythm.form.MusicaForm;
import com.dam2.rhythm.form.UsuarioLoginForm;
import com.dam2.rhythm.model.Lista;
import com.dam2.rhythm.model.Musica;
import com.dam2.rhythm.model.Usuario;
import com.dam2.rhythm.repository.ListaRepositorio;
import com.dam2.rhythm.repository.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ListaController {
	@Autowired
	private UsuarioRepositorio usuarRepos;
	@Autowired
	private ListaRepositorio listaRepos;

	@GetMapping(path = "/listasreproduccion")
	public String listasReproduccion( Model modelo,HttpSession session) {
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/error";
		}
		Usuario user = usuarRepos.findByEmail(session.getAttribute("usuario").toString());
		Iterable<Lista> itListas = user.getListas();
		List<Lista> listaListas = new ArrayList<>();
		itListas.forEach(listaListas::add);
		modelo.addAttribute("listaListas", listaListas);
		return "/listasreproduccion";
	}

	@GetMapping(path = "/crearlista")
	public String crearLista(ListaForm listaForm,HttpSession session, Model modelo) {
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/error";
		}
		Usuario user = usuarRepos.findByEmail(session.getAttribute("usuario").toString());
		modelo.addAttribute("listasUsuario", user);
		return "/crearlista";
	}
	
	@PostMapping(path = "/postcrearlista")
	public String checkListaInfo(@Valid ListaForm listaForm, BindingResult bindingResult, Model modelo,
			HttpSession session) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return crearLista(listaForm,session,modelo);
		}
		Lista nuevaLista=new Lista(listaForm.getUsuario(), listaForm.getTitulo());
		listaRepos.save(nuevaLista);
		return listasReproduccion(modelo,session);
	}
}
