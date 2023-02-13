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

import com.dam2.rhythm.form.UsuarioForm;
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
public class Controlador {
	@Autowired
	private AlbumRepositorio albumRepos;
	@Autowired
	private ArtistaRepositorio artisRepos;
	@Autowired
	private GeneroRepositorio generRepos;
	@Autowired
	private IntegranteRepositorio integRespos;
	@Autowired
	private ListaRepositorio listaRepos;
	@Autowired
	private MusicaRepositorio musicRepos;
	@Autowired
	private RolRepositorio rolRepos;
	@Autowired
	private UsuarioRepositorio usuarRepos;
	
	//@Bean
	public CommandLineRunner cargarDatos() {
		return (args) -> {
			rolRepos.save(new Rol("ADMIN"));
			rolRepos.save(new Rol("USUARIO"));
			usuarRepos.save(new Usuario(rolRepos.findByNombre("ADMIN"), "admin", "nombre", "apellido", "admin@correo.com", "admin"));
			artisRepos.save(new Artista("Michael Jackson"));
			artisRepos.save(new Artista("The Weeknd"));
			artisRepos.save(new Artista("Queen"));
			artisRepos.save(new Artista("BTS"));
			artisRepos.save(new Artista("Metal Gear Rising: Revengeance"));
			artisRepos.save(new Artista("GRIS"));
			artisRepos.save(new Artista("Bee Gees"));
			artisRepos.save(new Artista("Earth, Wind & Fire"));
			integRespos.save(new Integrante("Michael Jackson", "Michael Joseph", "Jackson", artisRepos.findByNick("Michael Jackson")));
			integRespos.save(new Integrante("The Weeknd", "Abel Makkonen", "Tesfaye", artisRepos.findByNick("The Weeknd")));
			integRespos.save(new Integrante(null, "Freddie", "Mercury", artisRepos.findByNick("Queen")));
			integRespos.save(new Integrante(null, "Brian", "May", artisRepos.findByNick("Queen")));
			integRespos.save(new Integrante(null, "Roger", "Taylor", artisRepos.findByNick("Queen")));
			integRespos.save(new Integrante(null, "John", "Deacon", artisRepos.findByNick("Queen")));
			integRespos.save(new Integrante("Jin", "Kim", "Seok-jin", artisRepos.findByNick("BTS")));
			integRespos.save(new Integrante("Suga", "Min", "Yoon-gi", artisRepos.findByNick("BTS")));
			integRespos.save(new Integrante("J-Hope", "Jung", "Ho-seok", artisRepos.findByNick("BTS")));
			integRespos.save(new Integrante("RM", "Kim", "Nam-joon", artisRepos.findByNick("BTS")));
			integRespos.save(new Integrante("Jimin", "Park", "Ji-min", artisRepos.findByNick("BTS")));
			integRespos.save(new Integrante("V", "Kim", "Tae-hyung", artisRepos.findByNick("BTS")));
			integRespos.save(new Integrante("Jungkook", "Jeon", "Jung-kook", artisRepos.findByNick("BTS")));
			integRespos.save(new Integrante("Metal Gear", "Konami", null, artisRepos.findByNick("Metal Gear Rising: Revengeance")));
			integRespos.save(new Integrante("GRIS", "Nomada Studio", null, artisRepos.findByNick("GRIS")));
			integRespos.save(new Integrante(null, "Barry", "Gibb", artisRepos.findByNick("Bee Gees")));
			integRespos.save(new Integrante(null, "Maurice", "Gibb", artisRepos.findByNick("Bee Gees")));
			integRespos.save(new Integrante(null, "Robin", "Gibb", artisRepos.findByNick("Bee Gees")));
			integRespos.save(new Integrante(null, "Vince", "Melouney", artisRepos.findByNick("Bee Gees")));
			integRespos.save(new Integrante(null, "Colin", "Petersen", artisRepos.findByNick("Bee Gees")));
			integRespos.save(new Integrante(null, "Geoff", "Bridgford", artisRepos.findByNick("Bee Gees")));
			integRespos.save(new Integrante(null, "Philip", "Bailey", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Verdine", "White", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Ralph", "Johnson", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "David", "Whitworth", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Philip", "Bailey Jr", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Myron", "McKinley", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "John", "Paris", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Morris", "O'Connor", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Serg", "Dimitrijevic", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Gary", "Bias", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Reggie", "Young", artisRepos.findByNick("Earth, Wind & Fire")));
			integRespos.save(new Integrante(null, "Bobby", "Burns Jr", artisRepos.findByNick("Earth, Wind & Fire")));
			generRepos.save(new Genero("R&B"));
			generRepos.save(new Genero("New Jack Swing"));
			generRepos.save(new Genero("Pop"));
			generRepos.save(new Genero("Funk"));
			generRepos.save(new Genero("Soul"));
			generRepos.save(new Genero("Opera"));
			generRepos.save(new Genero("Rock"));
			generRepos.save(new Genero("New wave"));
			generRepos.save(new Genero("Electronic"));
			generRepos.save(new Genero("Metal"));
			generRepos.save(new Genero("Classical"));
			generRepos.save(new Genero("Ambient"));			
			generRepos.save(new Genero("Disco"));
			generRepos.save(new Genero("Soft-rock"));
			generRepos.save(new Genero("Hip-hop"));
			generRepos.save(new Genero("Dance-pop"));
			generRepos.save(new Genero("Hard-rock"));
			generRepos.save(new Genero("Heavy-metal"));
			generRepos.save(new Genero("Synth-pop"));
			generRepos.save(new Genero("Dark-pop"));
			generRepos.save(new Genero("Progressive-rock"));
			Musica jacks1=new Musica("Remember the Time", artisRepos.findByNick("Michael Jackson"), "1992-01-14");
			Musica jacks2=new Musica("Thriller", artisRepos.findByNick("Michael Jackson"), "1982-11-29");
			Musica jacks3=new Musica("Billie Jean", artisRepos.findByNick("Michael Jackson"), "1983-01-02");
			jacks1.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("R&B"),generRepos.findByNombre("New Jack Swing")));
			jacks2.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("Pop"),generRepos.findByNombre("R&B")));
			jacks3.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("R&B"),generRepos.findByNombre("Funk")));
			Musica weekn1=new Musica("Is There Someone Else?", artisRepos.findByNick("The Weeknd"), "2022-01-03");
			Musica weekn2=new Musica("After Hours", artisRepos.findByNick("The Weeknd"), "2020-02-20");
			Musica weekn3=new Musica("Don't Break My Heart", artisRepos.findByNick("The Weeknd"), "2022-01-22");
			weekn1.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("R&B"),generRepos.findByNombre("Soul")));
			weekn2.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Dark-pop"),generRepos.findByNombre("Synth-pop"),generRepos.findByNombre("R&B")));
			weekn3.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("R&B"),generRepos.findByNombre("Soul")));
			Musica queen1 = new Musica("Bohemian Rhapsody", artisRepos.findByNick("Queen"), "1975-10-31");
			Musica queen2 = new Musica("I Want to Break Free", artisRepos.findByNick("Queen"), "1984-04-02");
			Musica queen3 = new Musica("Another One Bites the Dust", artisRepos.findByNick("Queen"), "1980-09-13");
			queen1.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Progressive-rock"),generRepos.findByNombre("Opera"),generRepos.findByNombre("Hard-rock"),generRepos.findByNombre("Heavy-metal")));
			queen2.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Rock"),generRepos.findByNombre("New wave")));
			queen3.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Rock"),generRepos.findByNombre("Funk")));
			Musica bts1 = new Musica("DOPE", artisRepos.findByNick("BTS"), "2015-06-22");
			Musica bts2 = new Musica("FIRE", artisRepos.findByNick("BTS"), "2016-05-02");
			Musica bts3 = new Musica("Not Today", artisRepos.findByNick("BTS"), "2017-02-19");
			bts1.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Hip-hop"),generRepos.findByNombre("Dance-pop")));
			bts2.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Hip-hop"),generRepos.findByNombre("Dance-pop")));
			bts3.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Hip-hop"),generRepos.findByNombre("Dance-pop")));
			Musica mgr1 = new Musica("Rules of nature", artisRepos.findByNick("Metal Gear Rising: Revengeance"), "2013-02-19");
			Musica mgr2 = new Musica("The only thing i know for real", artisRepos.findByNick("Metal Gear Rising: Revengeance"), "2013-02-19");
			Musica mgr3 = new Musica("It has to be this way", artisRepos.findByNick("Metal Gear Rising: Revengeance"), "2013-02-19");
			mgr1.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Metal"),generRepos.findByNombre("Electronic")));
			mgr2.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Metal"),generRepos.findByNombre("Electronic")));
			mgr3.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Metal"),generRepos.findByNombre("Electronic")));
			Musica gris1 = new Musica("Gris, Pt.1", artisRepos.findByNick("GRIS"), "2018-12-13");
			Musica gris2 = new Musica("Gris, Pt.2", artisRepos.findByNick("GRIS"), "2018-12-13");
			Musica gris3 = new Musica("In Your Hands", artisRepos.findByNick("GRIS"), "2018-12-13");
			gris1.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Classical"),generRepos.findByNombre("Ambient")));
			gris2.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Classical"),generRepos.findByNombre("Ambient")));
			gris3.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Classical"),generRepos.findByNombre("Ambient")));
			Musica bees1 = new Musica("Stayin' Alive", artisRepos.findByNick("Bee Gees"), "1977-12-31");
			Musica bees2 = new Musica("Night Fever", artisRepos.findByNick("Bee Gees"), "1977-12-31");
			Musica bees3 = new Musica("How Deep Is Your Love", artisRepos.findByNick("Bee Gees"), "1977-12-31");
			bees1.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Disco")));
			bees2.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Disco")));
			bees3.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Pop"),generRepos.findByNombre("Soft-rock")));
			Musica ewf1 = new Musica("September", artisRepos.findByNick("Earth, Wind & Fire"), "1978-09-22");
			Musica ewf2 = new Musica("Boogie Wonderland", artisRepos.findByNick("Earth, Wind & Fire"), "1979-04-06");
			Musica ewf3 = new Musica("Let's Groove", artisRepos.findByNick("Earth, Wind & Fire"), "1981-08-24");
			ewf1.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("Soul")));
			ewf2.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("Disco")));
			ewf3.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("R&B")));
			List<Musica> auxMusicas=new ArrayList<>(Arrays.asList(jacks1,jacks2,jacks3,weekn1,weekn2,weekn3,queen1,queen2,queen3,bts1,bts2,bts3,mgr1,mgr2,mgr3,gris1,gris2,gris3,bees1,bees2,bees1,ewf1,ewf2,ewf3));
			for (Musica mus : auxMusicas) {
				musicRepos.save(mus);
			}
			
		};
	}
	
	
	@GetMapping(path = "/listausuarios")
	public String getListaUsuarios(Model modelo) {
		List<Usuario> listaUsuarios = listUsuarios(usuarRepos);
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		return "/listausuarios";
	}

	@GetMapping(path = "/altausuario")
	public String showForm(UsuarioForm personForm) {
		return "/altausuario";
	}

	@PostMapping(path = "/postusuario")
	public String checkPersonInfo(@Valid UsuarioForm usuForm, BindingResult bindingResult, Model modelo,HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "/altausuario";
		}
		if (!usuForm.getPassword().equals(usuForm.getPassword2())) {
			modelo.addAttribute("error", "Las contraseñas no son iguales.");
			return "/altausuario";
		}
		Usuario usuNuevo = new Usuario(rolRepos.findByNombre("USUARIO"), usuForm.getNick(), usuForm.getNombre(), usuForm.getApellido(), usuForm.getEmail(), usuForm.getPassword());
		usuarRepos.save(usuNuevo);
		modelo.addAttribute("mensaje", usuForm.getNick() + " dado de alta correctamente.");
		session.setAttribute("usuario", usuForm.getNick());
		return "/inicio";
	}
	
	//----------
	
	
	
	/*
	@GetMapping("/login")
	public String login(LoginForm loginForm) {
		return "/login";
	}
	//venimos desde login y validamos todos los datos
	@PostMapping(path = "/postlogin")
	public String checkLoginInfo(@Valid LoginForm loginForm,BindingResult bindingResult,HttpSession session) {
		System.out.println("en login post");
		if (bindingResult.hasErrors()) {
			session.setAttribute("error", "usuario/email incorrectos");
			return "/login";
		}
		System.out.println("en login post loginForm.getUserName()="+loginForm.getEmail());
		Usuario usuario=usuarioRepositorio.findByEmail(loginForm.getEmail());
		if (usuario.getEmail().equals(loginForm.getEmail())&&usuario.getName().equals(loginForm.getName())) {
			session.setAttribute("usuario", loginForm.getEmail());
			return "/index";
		}else {
			session.setAttribute("error", "usuario/email incorrectos");
			return "/login";
		}
	}
	*/
	private List<Usuario> listUsuarios(UsuarioRepositorio usuarioRepositorio) {
		Iterable<Usuario> itUsuarios = usuarioRepositorio.findAll();
		System.out.println("itUsuarios:" + itUsuarios);
		List<Usuario> listaUsuarios = new ArrayList<>();
		itUsuarios.forEach(listaUsuarios::add);
		return listaUsuarios;
	}
	private List<Rol> listRoles(RolRepositorio rolRepositorio) {
		Iterable<Rol> itRoles = rolRepositorio.findAll();
		System.out.println("itRoles:" + itRoles);
		List<Rol> listaRoles = new ArrayList<>();
		itRoles.forEach(listaRoles::add);
		return listaRoles;
	}
}
