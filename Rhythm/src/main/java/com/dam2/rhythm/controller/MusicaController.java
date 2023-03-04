package com.dam2.rhythm.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dam2.rhythm.dto.GeneroDTO;
import com.dam2.rhythm.dto.MusicaDTO;
import com.dam2.rhythm.dto.MusicasDTO;
import com.dam2.rhythm.files.JSONRhythmManager;
import com.dam2.rhythm.files.XMLRhythmManager;
import com.dam2.rhythm.form.MusicaForm;
import com.dam2.rhythm.form.MusicaHiddenForm;
import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Genero;
import com.dam2.rhythm.model.Lista;
import com.dam2.rhythm.model.Musica;
import com.dam2.rhythm.repository.ArtistaRepositorio;
import com.dam2.rhythm.repository.GeneroRepositorio;
import com.dam2.rhythm.repository.MusicaRepositorio;
import com.dam2.rhythm.util.Utilidades;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBException;

@Controller
public class MusicaController {
	@Autowired
	private GeneroRepositorio generRepos;
	@Autowired
	private MusicaRepositorio musicRespos;
	@Autowired
	private ArtistaRepositorio artisRepos;

	@GetMapping(path = "/musicas")
	public String musicas(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";
		}
		List<Musica> listaMusicas = Utilidades.listMusicas(musicRespos.findAll());
		modelo.addAttribute("listaMusicas", listaMusicas);
		return "/musicas";
	}

	@GetMapping(path = "/musicas_admin")
	public String musicasAdmin(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return "redirect:/cerrar_sesion";
			}
		} catch (Exception e) {
			return "/aviso_session";
		}
		List<Musica> listaMusicas = Utilidades.listMusicas(musicRespos.findAll());
		modelo.addAttribute("listaMusicas", listaMusicas);
		return "/musicas_admin";
	}

	@GetMapping(path = "/musica_crear")
	public String musicaCrear(MusicaForm musicaForm, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return "redirect:/cerrar_sesion";
			}

		} catch (Exception e) {
			return "/aviso_session";

		}
		List<Artista> listaArtistas = Utilidades.listArtistas(artisRepos.findAll());
		List<Genero> listaGeneros = Utilidades.listGeneros(generRepos.findAll());
		modelo.addAttribute("listaArtistas", listaArtistas);
		modelo.addAttribute("listaGeneros", listaGeneros);
		return "/musica_crear";
	}

	@PostMapping(path = "/post_musica_crear")
	public String checkMusicaInfo(@Valid MusicaForm musicaForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (bindingResult.hasErrors()) {
			return musicaCrear(musicaForm, modelo, request);
		}

		if (musicRespos.findByArtistaAndTitulo(musicaForm.getArtista(), musicaForm.getTitulo()) != null) {
			modelo.addAttribute("error", "La música ya está registrada.");
			return musicaCrear(musicaForm, modelo, request);
		}
		Musica musiNueva = new Musica(musicaForm.getTitulo(), musicaForm.getArtista(), musicaForm.getEstreno(),
				musicaForm.getEmbed());
		musiNueva.setGeneros(Arrays.asList(musicaForm.getGenero()));
		musicRespos.save(musiNueva);
		session.setAttribute("mensaje", musicaForm.getTitulo());
		return "redirect:/musicas";
	}

	@GetMapping("/musicas.json")
	public ResponseEntity<String> descargarMusicasJSON(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return null;
			}

		} catch (Exception e) {
			return null;
		}
		String json = JSONRhythmManager.generateMusicJson(Utilidades.listMusicas(musicRespos.findAll()));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=musicas.json");
		return new ResponseEntity<>(json, headers, HttpStatus.OK);
	}

	@GetMapping("/musicas.xml")
	public ResponseEntity<String> descargarMusicasXML(HttpServletRequest request) throws JAXBException {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return null;
			}

		} catch (Exception e) {
			return null;
		}
		String xml = XMLRhythmManager.generateMusicasXML(Utilidades.listMusicas(musicRespos.findAll()));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=musicas.xml");
		return new ResponseEntity<>(xml, headers, HttpStatus.OK);
	}

	@PostMapping("/musicas_import")
	public String cargarMusicas(@RequestParam("myfile") MultipartFile file, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return "redirect:/cerrar_sesion";
			}

		} catch (Exception e) {
			return "/aviso_session";

		}
		try {
			List<Musica> nuevasMusicas = new ArrayList<>();
			int numMusicas = 0;
			int numGeneros = 0;
			InputStream inputStream = file.getInputStream();
			String filename = StringUtils.getFilename(file.getOriginalFilename());
			String extension = StringUtils.getFilenameExtension(filename);
			MusicasDTO musicas = null;
			switch (extension) {
			case "xml":
				musicas = XMLRhythmManager.readMusicasXML(inputStream);
				break;
			case "json":
				musicas = JSONRhythmManager.readMusicasJSON(inputStream);
				break;
			default:
				System.out.println("Error extensión");
				break;
			}
			for (MusicaDTO musicaDTO : musicas.getMusicas()) {
				String artistaNick = musicaDTO.getArtistaNick();
				if (artisRepos.findByNick(artistaNick) == null) {
					artisRepos.save(new Artista(artistaNick));
				}
				String titulo = musicaDTO.getTitulo();
				Artista artista = artisRepos.findByNick(artistaNick);
				if (musicRespos.findByArtistaAndTitulo(artista, titulo) == null) {
					Musica musica = musicaDTO.mappingToMusica();
					musica.setArtista(artista);
					for (GeneroDTO generoDTO : musicaDTO.getGeneros().getGenero()) {
						String nombreGenero = generoDTO.getNombre();
						if (generRepos.findByNombre(nombreGenero) == null) {
							generRepos.save(new Genero(nombreGenero, generoDTO.getDescripcion()));
							numGeneros++;
						}
						musica.getGeneros().add(generRepos.findByNombre(nombreGenero));
					}
					nuevasMusicas.add(musica);
				}
			}

			if (!nuevasMusicas.isEmpty()) {
				musicRespos.saveAll(nuevasMusicas);
			}
			numMusicas = nuevasMusicas.size();
			modelo.addAttribute("mensaje",
					String.format("Se han guardado %d Musicas y %d Generos.", numMusicas, numGeneros));
			return UsuarioController.importar(request);
		} catch (Exception e) {
			modelo.addAttribute("error", "Error al realizar la importación.");
			return UsuarioController.importar(request);
		}
	}

	@PostMapping(path = "post_musica_update")
	public String listaMusicasQuitar(MusicaHiddenForm musicaHiddenForm, MusicaForm musicaForm, Model modelo,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return "redirect:/cerrar_sesion";
			}

		} catch (Exception e) {
			return "/aviso_session";

		}
		Musica musica = musicaHiddenForm.getMusica();
		musicaForm.pasarDatos(musica);
		List<Artista> listaArtistas = Utilidades.listArtistas(artisRepos.findAll());
		List<Genero> listaGeneros = Utilidades.listGeneros(generRepos.findAll());
		modelo.addAttribute("listaArtistas", listaArtistas);
		modelo.addAttribute("listaGeneros", listaGeneros);
		modelo.addAttribute("musicaForm", musicaForm);
		modelo.addAttribute(musicaHiddenForm.getMusica());
		session.setAttribute("musicaId", musica.getId());
		return "/musica_update";
	}

	@PostMapping(path = "/post_musica_updating")
	public String checkMusicaUpdateInfo(@Valid MusicaForm musicaForm, BindingResult bindingResult, Model modelo,
			HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				return "redirect:/cerrar_sesion";
			}

		} catch (Exception e) {
			return "/aviso_session";

		}
		if (bindingResult.hasErrors()) {
			return musicaCrear(musicaForm, modelo, request);
		}
		int id = Integer.parseInt(session.getAttribute("musicaId").toString());
		Musica musicaActual = new Musica(musicaForm.getTitulo(), musicaForm.getArtista(), musicaForm.getEstreno(),
				musicaForm.getEmbed());
		musicaActual.setId(id);
		musicaActual.setGeneros(Arrays.asList(musicaForm.getGenero()));
		musicRespos.save(musicaActual);
		return "redirect:/musicas_admin";
	}

	@GetMapping("/reproductor")
	public String reproductor(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";

		}
		return "/reproductor";
	}
}
