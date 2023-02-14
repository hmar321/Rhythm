package com.dam2.rhythm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam2.rhythm.form.UsuarioAltaForm;
import com.dam2.rhythm.form.UsuarioLoginForm;
import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Genero;
import com.dam2.rhythm.model.Integrante;
import com.dam2.rhythm.model.Musica;
import com.dam2.rhythm.model.Rol;
import com.dam2.rhythm.model.Usuario;
import com.dam2.rhythm.repository.AlbumRepositorio;
import com.dam2.rhythm.repository.ArtistaRepositorio;
import com.dam2.rhythm.repository.GeneroRepositorio;
import com.dam2.rhythm.repository.IntegranteRepositorio;
import com.dam2.rhythm.repository.ListaRepositorio;
import com.dam2.rhythm.repository.MusicaRepositorio;
import com.dam2.rhythm.repository.RolRepositorio;
import com.dam2.rhythm.repository.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class UsuarioControlador {
	@Autowired
	private RolRepositorio rolRepos;
	@Autowired
	private UsuarioRepositorio usuarRepos;
	
	
	@GetMapping(path = "/listausuarios")
	public String getListaUsuarios(Model modelo) {
		List<Usuario> listaUsuarios = listUsuarios(usuarRepos);
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		return "/listausuarios";
	}

	@GetMapping(path = "/altausuario")
	public String showForm(UsuarioAltaForm personForm) {
		return "/altausuario";
	}

	@PostMapping(path = "/postusuario")
	public String checkPersonInfo(@Valid UsuarioAltaForm usuForm, BindingResult bindingResult, Model modelo,HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "/altausuario";
		}
		if (!usuForm.getPassword().equals(usuForm.getPassword2())) {
			session.setAttribute("error", "Las contraseñas no son iguales.");
			return "/altausuario";
		}
		Usuario usuNuevo = new Usuario(rolRepos.findByNombre("USUARIO"), usuForm.getNick(), usuForm.getNombre(), usuForm.getApellido(), usuForm.getEmail(), usuForm.getPassword());
		usuarRepos.save(usuNuevo);
		modelo.addAttribute("mensaje", usuForm.getNick() + " dado de alta correctamente.");
		session.setAttribute("usuario", usuNuevo);
		return "/inicio";
	}
	
	@GetMapping("/login")
	public String login(UsuarioLoginForm usuarioLoginForm) {
		return "/login";
	}

	//venimos desde login y validamos todos los datos
	@PostMapping(path = "/postlogin")
	public String checkLoginInfo(@Valid UsuarioLoginForm usuarioLoginForm,BindingResult bindingResult,HttpSession session) {
		System.out.println("en login post");
		if (bindingResult.hasErrors()) {
			session.setAttribute("error", "usuario/email incorrectos");
			return "/login";
		}
		System.out.println("en login post loginForm.getUserEmail()="+usuarioLoginForm.getEmail());
		Usuario usuario=usuarRepos.findByEmail(usuarioLoginForm.getEmail());
		if (usuario.getEmail().equals(usuarioLoginForm.getEmail())&&usuario.getPassword().equals(usuarioLoginForm.getPassword())) {
			session.setAttribute("usuario", usuario);
			return "/index";
		}else {
			session.setAttribute("error", "usuario/email incorrectos");
			return "/login";
		}
	}
	
	private List<Usuario> listUsuarios(UsuarioRepositorio usuarioRepositorio) {
		Iterable<Usuario> itUsuarios = usuarioRepositorio.findAll();
		System.out.println("itUsuarios:" + itUsuarios);
		List<Usuario> listaUsuarios = new ArrayList<>();
		itUsuarios.forEach(listaUsuarios::add);
		return listaUsuarios;
	}

}
