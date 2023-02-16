package com.dam2.rhythm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam2.rhythm.form.UsuarioAltaForm;
import com.dam2.rhythm.form.UsuarioLoginForm;
import com.dam2.rhythm.model.Usuario;
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

	@GetMapping("/")
	public static String login(UsuarioLoginForm usuarioLoginForm, HttpSession session) {
		session.invalidate();
		return "/index";
	}

	@PostMapping(path = "/postlogin")
	public String checkLoginInfo(@Valid UsuarioLoginForm usuarioLoginForm, BindingResult bindingResult,
			HttpSession session) {
		if (bindingResult.hasErrors()) {
			session.setAttribute("error", "Usuario/email incorrectos.");
			return "/index";
		}
		Usuario usuario = usuarRepos.findByEmail(usuarioLoginForm.getEmail());
		if (usuario==null) {
			session.setAttribute("error", "El usuario no está registrado.");
			return "/index";
		}
		if (usuario.getEmail().equals(usuarioLoginForm.getEmail())
				&& usuario.getPassword().equals(usuarioLoginForm.getPassword())) {
			if (usuario.getRol() == rolRepos.findByNombre("ADMIN")) {
				session.setAttribute("codigo", "ADMIN");
				return "/inicioadmin";
			}
			session.setAttribute("codigo", "USER");
			session.setAttribute("usuario", usuario.getEmail());
			return "/inicio";
		} else {
			session.setAttribute("error", "Usuario/email incorrectos.");
			return "/index";
		}
	}

	@GetMapping(path = "/altausuario")
	public String showForm(UsuarioAltaForm personForm, HttpSession session) {
		session.removeAttribute("error");
		return "/altausuario";
	}

	@PostMapping(path = "/postaltausuario")
	public String checkPersonInfo(@Valid UsuarioAltaForm usuarioAltaForm, BindingResult bindingResult, Model modelo,
			HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "/altausuario";
		}
		if (usuarRepos.findByEmail(usuarioAltaForm.getEmail()) != null) {
			session.setAttribute("error", "El correo ya está registrado.");
			return "/altausuario";
		}
		if (!usuarioAltaForm.getPassword().equals(usuarioAltaForm.getPassword2())) {
			session.setAttribute("error", "Las contraseñas no son iguales.");
			return "/altausuario";
		}
		if (usuarioAltaForm.getPassword().length() == 0) {
			session.setAttribute("error", "Contraseña vacía.");
			return "/altausuario";
		}
		Usuario usuNuevo = new Usuario(rolRepos.findByNombre("USER"), usuarioAltaForm.getNick(),
				usuarioAltaForm.getNombre(), usuarioAltaForm.getApellido(), usuarioAltaForm.getEmail(),
				usuarioAltaForm.getPassword());
		usuarRepos.save(usuNuevo);
		session.setAttribute("usuario", usuNuevo.getEmail());
		return "/inicio";
	}

	@GetMapping(path = "/listausuarios")
	public String getListaUsuarios(Model modelo, HttpSession session, UsuarioLoginForm usuarioLoginForm) {
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				login(usuarioLoginForm, session);
			}
		} catch (Exception e) {
			return login(usuarioLoginForm, session);

		}
		if (session.getAttribute("codigo").equals(null) || !session.getAttribute("codigo").equals("ADMIN")) {
			return "/index";
		}
		List<Usuario> listaUsuarios = listUsuarios(usuarRepos);
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		return "/listausuarios";
	}

	@GetMapping(path = "/inicio")
	public String inicio(HttpSession session, UsuarioLoginForm usuarioLoginForm) {
		try {
			session.getAttribute("codigo").equals(null);
		} catch (Exception e) {
			return login(usuarioLoginForm, session);

		}
		return "/inicio";
	}

	@GetMapping(path = "/inicioadmin")
	public String inicioAdmin(HttpSession session, UsuarioLoginForm usuarioLoginForm) {
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return login(usuarioLoginForm, session);
			}
		} catch (Exception e) {
			return login(usuarioLoginForm, session);

		}
		return "/inicioadmin";
	}

	private List<Usuario> listUsuarios(UsuarioRepositorio usuarioRepositorio) {
		Iterable<Usuario> itUsuarios = usuarioRepositorio.findAll();
		System.out.println("itUsuarios:" + itUsuarios);
		List<Usuario> listaUsuarios = new ArrayList<>();
		itUsuarios.forEach(listaUsuarios::add);
		return listaUsuarios;
	}

}
