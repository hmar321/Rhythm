package com.dam2.rhythm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam2.rhythm.form.ListaForm;
import com.dam2.rhythm.form.ListaMusicaForm;
import com.dam2.rhythm.form.ListaMusicasForm;
import com.dam2.rhythm.form.MusicaHiddenForm;
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

	@GetMapping(path = "/listas")
	public String listas(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";
		}
		Usuario user = usuarRepos.findByEmail(session.getAttribute("usuario").toString());
		List<Lista> listaListas = Utilidades.listListas(user.getListas());
		modelo.addAttribute("listaListas", listaListas);
		return "/listas";
	}

	@GetMapping(path = "/lista_crear")
	public String listaCrear(ListaForm listaForm, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";
		}
		Usuario user = usuarRepos.findByEmail(session.getAttribute("usuario").toString());
		modelo.addAttribute("usuario", user);
		return "/lista_crear";
	}

	@PostMapping(path = "/post_lista_crear")
	public String checkListaInfo(@Valid ListaForm listaForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			return listaCrear(listaForm, modelo, request);
		}
		Lista nuevaLista = new Lista(listaForm.getUsuario(), listaForm.getTitulo());
		listaRepos.save(nuevaLista);
		return "redirect:/listas";
	}

	@PostMapping(path = "/post_lista_musicas")
	public String listaMusicas(@Valid ListaMusicasForm listaMusicasForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			return "redirect:/listas";
		}
		Lista listaActual = listaMusicasForm.getLista();
		List<Musica> listaMusicas = Utilidades.listMusicas(listaActual.getMusicas());
		modelo.addAttribute("listaMusicas", listaMusicas);
		session.setAttribute("listaId", listaActual.getId());
		return "/lista_musicas";
	}

	@GetMapping(path = "/lista_add_musica")
	public String listaAddMusica(ListaMusicaForm musicaListaForm, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";
		}

		List<Musica> listaMusicas = Utilidades.listMusicas(musicRepos.findAll());
		modelo.addAttribute("listaMusicas", listaMusicas);
		Integer id = Integer.parseInt(session.getAttribute("listaId").toString());
		Lista lista = listaRepos.findById(id).get();
		modelo.addAttribute("lista", lista);
		return "/lista_add_musica";
	}

	@PostMapping(path = "/post_lista_add_musica")
	public String checkListaAddMusica(@Valid ListaMusicaForm listaMusicaForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			return listaAddMusica(listaMusicaForm, modelo, request);
		}
		List<Musica> musicaAniadir = Arrays.asList(listaMusicaForm.getMusica());
		Lista listaActual = listaMusicaForm.getLista();
		for (Musica musica : musicaAniadir) {
			if (!listaActual.getMusicas().contains(musica)) {
				listaActual.add(musica);
				musica.getListas().add(listaActual);
			}
		}
		listaRepos.save(listaActual);
		musicRepos.saveAll(musicaAniadir);
		List<Musica> listaMusicas = Utilidades.listMusicas(listaActual.getMusicas());
		modelo.addAttribute("listaMusicas", listaMusicas);
		modelo.addAttribute("lista", listaActual);
		return "lista_musicas";
	}

	@GetMapping(path = "/volver_lista_musicas")
	public String aniadirMusicaALista(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";
		}
		Integer id = Integer.parseInt(session.getAttribute("listaId").toString());
		Lista listaActual = listaRepos.findById(id).get();
		List<Musica> listaMusicas = Utilidades.listMusicas(listaActual.getMusicas());
		modelo.addAttribute("listaMusicas", listaMusicas);
		return "/lista_musicas";
	}
	
	@PostMapping(path = "/post_lista_musicas_remove")
	public String listaMusicasQuitar(MusicaHiddenForm musicaRemoveForm, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";
		}
		Integer id = Integer.parseInt(session.getAttribute("listaId").toString());
		Lista listaActual = listaRepos.findById(id).get();
		List<Musica> listaMusicas = Utilidades.listMusicas(listaActual.getMusicas());
		listaMusicas.remove(musicaRemoveForm.getMusica());
		musicaRemoveForm.getMusica().getListas().remove(listaActual);
		musicRepos.save(musicaRemoveForm.getMusica());
		listaRepos.save(listaActual);
		modelo.addAttribute("listaMusicas", listaMusicas);
		return "/lista_musicas";
	}

}
