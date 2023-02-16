package com.dam2.rhythm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam2.rhythm.form.MusicaForm;
import com.dam2.rhythm.form.UsuarioAltaForm;
import com.dam2.rhythm.form.UsuarioLoginForm;
import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Genero;
import com.dam2.rhythm.model.Musica;
import com.dam2.rhythm.model.Usuario;
import com.dam2.rhythm.repository.AlbumRepositorio;
import com.dam2.rhythm.repository.ArtistaRepositorio;
import com.dam2.rhythm.repository.GeneroRepositorio;
import com.dam2.rhythm.repository.MusicaRepositorio;
import com.dam2.rhythm.repository.RolRepositorio;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
public class MusicaController {
	@Autowired
	private GeneroRepositorio generRepos;
	@Autowired
	private MusicaRepositorio musicRespos;
	@Autowired
	private ArtistaRepositorio artisRepos;

	@GetMapping(path = "/listamusicas")
	public String listaMusicas(Model modelo,HttpSession session) {
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/error";
		}
		List<Musica> listaMusicas = listMusicas(musicRespos);
		modelo.addAttribute("listaMusicas",listaMusicas);
		return "/listamusicas";
	}
	
	@GetMapping(path = "/crearmusica")
	public String crearMusica(Model modelo, HttpSession session,MusicaForm musicaForm,UsuarioLoginForm usuarioLoginForm) {
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return UsuarioControlador.login(usuarioLoginForm, session);
			}
			
		} catch (Exception e) {
			return UsuarioControlador.login(usuarioLoginForm, session);

		}
		List<Artista> listaArtistas =listArtistas(artisRepos);
		List<Genero> listaGeneros =listGeneros(generRepos);
		modelo.addAttribute("listaArtistas",listaArtistas);
		modelo.addAttribute("listaGeneros",listaGeneros);
		return "/crearmusica";
	}
	@PostMapping(path = "/postcrearmusica")
	public String checkMusicaInfo(@Valid MusicaForm musicaForm, BindingResult bindingResult, Model modelo,
			HttpSession session,UsuarioLoginForm usuarioLoginForm) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return crearMusica(modelo,session,musicaForm,usuarioLoginForm);
		}
		if (musicRespos.findByArtistaAndTitulo(musicaForm.getArtista(), musicaForm.getTitulo())!=null) {
			modelo.addAttribute("error","La música ya está registrada.");
			return crearMusica(modelo,session,musicaForm,usuarioLoginForm);
		}
		Musica musiNueva = new Musica(musicaForm.getTitulo(), musicaForm.getArtista(), musicaForm.getEstreno());
		musiNueva.getGeneros().add(musicaForm.getGenero());
		musicRespos.save(musiNueva);
		session.setAttribute("mensaje", musicaForm.getTitulo());
		return listaMusicas(modelo,session);
	}

	private List<Musica> listMusicas(MusicaRepositorio musicasRepositorio) {
		Iterable<Musica> itMusicas = musicasRepositorio.findAll();
		List<Musica> listaMusicas = new ArrayList<>();
		itMusicas.forEach(listaMusicas::add);
		System.out.println(listaMusicas.size());
		return listaMusicas;
	}
	private List<Artista> listArtistas(ArtistaRepositorio artistaRepositorio) {
		Iterable<Artista> itArtista = artistaRepositorio.findAll();
		List<Artista> listaArtistas = new ArrayList<>();
		itArtista.forEach(listaArtistas::add);
		System.out.println(listaArtistas.size());
		return listaArtistas;
	}
	private List<Genero> listGeneros(GeneroRepositorio generoRepositorio) {
		Iterable<Genero> itGenero = generoRepositorio.findAll();
		List<Genero> listaGeneros = new ArrayList<>();
		itGenero.forEach(listaGeneros::add);
		System.out.println(listaGeneros.size());
		return listaGeneros;
	}
}
