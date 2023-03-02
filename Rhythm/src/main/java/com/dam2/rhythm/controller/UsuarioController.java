package com.dam2.rhythm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam2.rhythm.form.UsuarioForm;
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
	/*
	@Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
	
	@GetMapping("/login_google")
    public String getLoginPage(HttpServletRequest request) {
        String redirectUrl = UriComponentsBuilder.fromHttpUrl(
                request.getRequestURL().toString())
                .path("/oauth2/callback/google")
                .build().toUriString();

        OAuth2AuthorizationRequest authorizationRequest =
                OAuth2AuthorizationRequest
                        .authorizationCode()
                        .clientId(googleClientId)
                        .redirectUri(redirectUrl)
                        .scope("profile", "email")
                        .state(UUID.randomUUID().toString())
                        .build();

        request.getSession().setAttribute(
                OAuth2AuthorizationRequest.class.getName(),
                authorizationRequest);

        return "redirect:" +
                "https://accounts.google.com/o/oauth2/v2/auth?" +
                "client_id=" + googleClientId +
                "&redirect_uri=" + redirectUrl +
                "&response_type=code" +
                "&scope=profile email" +
                "&state=" + authorizationRequest.getState();
    }
    
    @GetMapping("/oauth2/callback/google")
    public String handleGoogleCallback(OAuth2AuthorizedClientService authorizedClientService,
            OAuth2AuthenticationToken authenticationToken,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authenticationToken.getAuthorizedClientRegistrationId(),
                        authenticationToken.getName());

        return "redirect:/inicio";
    }
	*/
	@GetMapping(path = "/cerrar_sesion")
	public String cerrarSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/")
	public String login(UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		return "/index";
	}
	
	@GetMapping("/export")
    public String exportar(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde crear musica");
				return "redirect:/cerrar_sesion";
			}

		} catch (Exception e) {
			return "/aviso_session";

		}
        return "/export";
    }
	
	@GetMapping("/import")
	public static String importar(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde crear musica");
				return "redirect:/cerrar_sesion";
			}

		} catch (Exception e) {
			return "/aviso_session";

		}
		return "/import";
	}

	@PostMapping(path = "/post_login")
	public String checkLoginInfo(@Valid UsuarioLoginForm usuarioLoginForm, BindingResult bindingResult,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			return "/index";
		}
		Usuario usuario = usuarRepos.findByEmail(usuarioLoginForm.getEmail());
		if (usuario == null) {
			session.setAttribute("error", "Error email/contraseña.");
			return "/index";
		}
		if (usuario.getEmail().equals(usuarioLoginForm.getEmail())
				&& usuario.getPassword().equals(usuarioLoginForm.getPassword())) {
			if (usuario.getRol() == rolRepos.findByNombre("ADMIN")) {
				session.setAttribute("codigo", "ADMIN");
				return "/inicio_admin";
			}
			session.setAttribute("codigo", "USER");
			session.setAttribute("usuario", usuario.getEmail());
			session.setAttribute("nick", usuario.getNick());
			return "/inicio";
		} else {
			session.setAttribute("error", "Error email/contraseña.");
			return "/index";
		}
	}

	@GetMapping(path = "/usuario_crear")
	public String usuarioCrear(UsuarioForm usuarioForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		return "/usuario_crear";
	}

	@PostMapping(path = "/post_usuario_crear")
	public String checkPersonInfo(@Valid UsuarioForm usuarioAltaForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			return "/usuario_crear";
		}
		if (usuarRepos.findByEmail(usuarioAltaForm.getEmail()) != null) {
			session.setAttribute("error", "El correo ya está registrado.");
			return "/usuario_crear";
		}
		if (!usuarioAltaForm.getPassword().equals(usuarioAltaForm.getPassword2())) {
			session.setAttribute("error", "Las contraseñas no son iguales.");
			return "/usuario_crear";
		}
		if (usuarioAltaForm.getPassword().length() == 0) {
			session.setAttribute("error", "Contraseña vacía.");
			return "/usuario_crear";
		}
		Usuario usuNuevo = new Usuario(rolRepos.findByNombre("USER"), usuarioAltaForm.getNick(),
				usuarioAltaForm.getNombre(), usuarioAltaForm.getApellido(), usuarioAltaForm.getEmail(),
				usuarioAltaForm.getPassword());
		usuarRepos.save(usuNuevo);
		session.setAttribute("usuario", usuNuevo.getEmail());
		session.setAttribute("nick", usuNuevo.getNick());
		return "/inicio";
	}

	@GetMapping(path = "/usuarios")
	public String usuarios(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde lista usuarios");
				return "redirect:/cerrar_sesion";
			}
		} catch (Exception e) {
			return "/aviso_session";

		}
		List<Usuario> listaUsuarios = Utilidades.listUsuarios(usuarRepos.findAll());
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		return "/usuarios";
	}

	@GetMapping(path = "/inicio")
	public String inicio(UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";

		}
		return "/inicio";
	}

	@GetMapping(path = "/inicio_admin")
	public String inicioAdmin(UsuarioLoginForm usuarioLoginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde inicio admin");
				return "redirect:/cerrar_sesion";
			}
		} catch (Exception e) {
			return "/aviso_session";
		}
		return "/inicio_admin";
	}
	
	

}
