package com.dam2.rhythm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam2.rhythm.form.MusicaForm;
import com.dam2.rhythm.form.UsuarioLoginForm;
import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Genero;
import com.dam2.rhythm.model.Musica;
import com.dam2.rhythm.repository.ArtistaRepositorio;
import com.dam2.rhythm.repository.GeneroRepositorio;
import com.dam2.rhythm.repository.MusicaRepositorio;
import com.dam2.rhythm.util.Utilidades;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MusicaController {
	@Autowired
	private GeneroRepositorio generRepos;
	@Autowired
	private MusicaRepositorio musicRespos;
	@Autowired
	private ArtistaRepositorio artisRepos;

	@GetMapping(path = "/listamusicas")
	public String listaMusicas(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/avisosession";
		}
		List<Musica> listaMusicas = Utilidades.listMusicas(musicRespos.findAll());
		modelo.addAttribute("listaMusicas", listaMusicas);
		return "/listamusicas";
	}
	@GetMapping(path = "/listamusicasadmin")
	public String listaMusicasAdmin(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return "/cerrarsesion";
			}
		} catch (Exception e) {
			return "/avisosession";
		}
		List<Musica> listaMusicas = Utilidades.listMusicas(musicRespos.findAll());
		modelo.addAttribute("listaMusicas", listaMusicas);
		return "/listamusicasadmin";
	}

	@GetMapping(path = "/crearmusica")
	public String crearMusica(Model modelo, MusicaForm musicaForm, UsuarioLoginForm usuarioLoginForm,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde crear musica");
				return UsuarioController.cerrarSession(usuarioLoginForm, request);
			}

		} catch (Exception e) {
			System.out.println("login desde crear musica");
			return "/avisosession";

		}
		List<Artista> listaArtistas = Utilidades.listArtistas(artisRepos.findAll());
		List<Genero> listaGeneros = Utilidades.listGeneros(generRepos.findAll());
		modelo.addAttribute("listaArtistas", listaArtistas);
		modelo.addAttribute("listaGeneros", listaGeneros);
		return "/crearmusica";
	}

	@PostMapping(path = "/postcrearmusica")
	public String checkMusicaInfo(@Valid MusicaForm musicaForm, BindingResult bindingResult, Model modelo,
			UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return crearMusica(modelo, musicaForm, usuarioLoginForm, request);
		}
		if (musicRespos.findByArtistaAndTitulo(musicaForm.getArtista(), musicaForm.getTitulo()) != null) {
			modelo.addAttribute("error", "La música ya está registrada.");
			return crearMusica(modelo, musicaForm, usuarioLoginForm, request);
		}
		Musica musiNueva = new Musica(musicaForm.getTitulo(), musicaForm.getArtista(), musicaForm.getEstreno());
		musiNueva.getGeneros().add(musicaForm.getGenero());
		musicRespos.save(musiNueva);
		session.setAttribute("mensaje", musicaForm.getTitulo());
		return listaMusicas(modelo, request);
	}
	

	
	
}
