package com.dam2.rhythm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam2.rhythm.form.ListaForm;
import com.dam2.rhythm.form.ListaVerForm;
import com.dam2.rhythm.form.ListaMusicaForm;
import com.dam2.rhythm.model.Lista;
import com.dam2.rhythm.model.Musica;
import com.dam2.rhythm.model.Usuario;
import com.dam2.rhythm.repository.ListaRepositorio;
import com.dam2.rhythm.repository.MusicaRepositorio;
import com.dam2.rhythm.repository.UsuarioRepositorio;
import com.dam2.rhythm.util.Utilidades;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ListaController {
	@Autowired
	private UsuarioRepositorio usuarRepos;
	@Autowired
	private ListaRepositorio listaRepos;
	@Autowired
	private MusicaRepositorio musicRepos;

	@GetMapping(path = "/listasreproduccion")
	public String listasReproduccion(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/avisosession";
		}
		Usuario user = usuarRepos.findByEmail(session.getAttribute("usuario").toString());
		List<Lista> listaListas =Utilidades.listListas( user.getListas()); 
		modelo.addAttribute("listaListas", listaListas);
		return "/listasreproduccion";
	}

	@GetMapping(path = "/crearlista")
	public String crearLista(ListaForm listaForm, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/avisosession";
		}
		Usuario user = usuarRepos.findByEmail(session.getAttribute("usuario").toString());
		modelo.addAttribute("usuario", user);
		return "/crearlista";
	}

	@PostMapping(path = "/postcrearlista")
	public String checkListaInfo(@Valid ListaForm listaForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return crearLista(listaForm, modelo, request);
		}
		Lista nuevaLista = new Lista(listaForm.getUsuario(), listaForm.getTitulo());
		listaRepos.save(nuevaLista);
		return listasReproduccion(modelo, request);
	}

	@PostMapping(path = "/postverlista")
	public String musicasLista(@Valid ListaVerForm listaVerForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return listasReproduccion(modelo, request);
		}
		Lista listaActual = listaVerForm.getLista();
		List<Musica> listaMusicas = Utilidades.listMusicas(listaActual.getMusicas());
		modelo.addAttribute("listaMusicas", listaMusicas);
		session.setAttribute("listaId", listaActual.getId());
		return "/listareproduccionmusicas";
	}
	@GetMapping(path = "/aniadirmusica")
	public String listaAniadirMusica(ListaMusicaForm musicaListaForm, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/avisosession";
		}
		
		List<Musica> listaMusicas = Utilidades.listMusicas(musicRepos.findAll());
		modelo.addAttribute("listaMusicas", listaMusicas);
		Integer id=Integer.parseInt(session.getAttribute("listaId").toString());
		Lista lista=listaRepos.findById(id).get();
		modelo.addAttribute("lista", lista);
		return "/aniadirmusica";
	}
	@GetMapping(path = "/asdasdasd")//para volver del form de añadir música a la lista de reproducción
	public String listaAniadirMusica() {
		return "/";
	}
	
	@PostMapping(path = "/postlistaaniadirmusica")
	public String checkListaAniadirMusica(@Valid ListaMusicaForm listaMusicaForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return listaAniadirMusica(listaMusicaForm,modelo,request);
		}
		Musica musicaAniadir=listaMusicaForm.getMusica();
		Lista listaActual = listaMusicaForm.getLista();
		if (listaActual.getMusicas().contains(musicaAniadir)) {
			modelo.addAttribute("error","La lista ya contiene esa música.");
			return listaAniadirMusica(listaMusicaForm,modelo,request);
		}
		listaActual.add(listaMusicaForm.getMusica());
		musicaAniadir.getListas().add(listaActual);
		listaRepos.save(listaActual);
		musicRepos.save(musicaAniadir);
		List<Musica> listaMusicas = Utilidades.listMusicas(listaActual.getMusicas());
		modelo.addAttribute("listaMusicas", listaMusicas);
		modelo.addAttribute("lista", listaActual);
		return "/listareproduccionmusicas";
	}

}
