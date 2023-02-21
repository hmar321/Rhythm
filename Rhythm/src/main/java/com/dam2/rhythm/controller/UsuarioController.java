package com.dam2.rhythm.controller;

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
import com.dam2.rhythm.util.Utilidades;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	@Autowired
	private RolRepositorio rolRepos;
	@Autowired
	private UsuarioRepositorio usuarRepos;

	@GetMapping(path = "/cerrarsesion")
	public static String cerrarSession(UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return login(usuarioLoginForm, request);
	}

	@GetMapping("/")
	public static String login(UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		return "/index";
	}

	@PostMapping(path = "/postlogin")
	public String checkLoginInfo(@Valid UsuarioLoginForm usuarioLoginForm, BindingResult bindingResult,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			session.setAttribute("error", "Usuario/email incorrectos.");
			return "/index";
		}
		Usuario usuario = usuarRepos.findByEmail(usuarioLoginForm.getEmail());
		if (usuario == null) {
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
			session.setAttribute("nick", usuario.getNick());
			return "/inicio";
		} else {
			session.setAttribute("error", "Usuario/email incorrectos.");
			return "/index";
		}
	}

	@GetMapping(path = "/altausuario")
	public String showForm(UsuarioAltaForm personForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		return "/altausuario";
	}

	@PostMapping(path = "/postaltausuario")
	public String checkPersonInfo(@Valid UsuarioAltaForm usuarioAltaForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
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
		session.setAttribute("nick", usuNuevo.getNick());
		return "/inicio";
	}

	@GetMapping(path = "/listausuarios")
	public String getListaUsuarios(Model modelo, UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde lista usuarios");
				cerrarSession(usuarioLoginForm, request);
			}
		} catch (Exception e) {
			System.out.println("login desde lista usuarios");
			return "/avisosession";

		}
		if (session.getAttribute("codigo").equals(null) || !session.getAttribute("codigo").equals("ADMIN")) {
			return "/index";
		}
		List<Usuario> listaUsuarios = Utilidades.listUsuarios(usuarRepos.findAll());
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		return "/listausuarios";
	}

	@GetMapping(path = "/inicio")
	public String inicio(UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals(null);
		} catch (Exception e) {
			System.out.println("login desde inicio usuarios");
			return "/avisosession";

		}
		return "/inicio";
	}

	@GetMapping(path = "/inicioadmin")
	public String inicioAdmin(UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde inicio admin");
				return cerrarSession(usuarioLoginForm, request);
			}
		} catch (Exception e) {
			return "/avisosession";

		}
		return "/inicioadmin";
	}

}
