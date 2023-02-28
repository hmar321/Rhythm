package com.dam2.rhythm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dam2.rhythm.dto.ArtistaDTO;
import com.dam2.rhythm.dto.ArtistasDTO;
import com.dam2.rhythm.dto.IntegranteDTO;
import com.dam2.rhythm.files.JSONRhythmManager;
import com.dam2.rhythm.files.XMLRhythmManager;
import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Integrante;
import com.dam2.rhythm.repository.ArtistaRepositorio;
import com.dam2.rhythm.repository.IntegranteRepositorio;
import com.dam2.rhythm.util.Utilidades;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.JAXBException;

@Controller
public class ArtistasController {
	@Autowired
	private ArtistaRepositorio artisRepos;
	@Autowired
	private IntegranteRepositorio integRepos;

	@GetMapping(path = "/artistas")
	public String artistas(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("artistas listado");
		try {
			session.getAttribute("codigo").equals("x");
		} catch (Exception e) {
			return "/aviso_session";
		}
		List<Artista> listaArtistas = Utilidades.listArtistas(artisRepos.findAll());
		modelo.addAttribute("listaArtistas", listaArtistas);
		return "/artistas";
	}

	@PostMapping("/artistas_import")
	public String cargarArtistas(@RequestParam("myfile") MultipartFile file, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde crear musica");
				return "redirect:/cerrar_sesion";
			}

		} catch (Exception e) {
			return "/aviso_session";

		}
		try {
			List<Integrante> nuevosIntegrantes = new ArrayList<>();
			int numArtistas = 0;
			int numIntegrantes =0;
			InputStream inputStream = file.getInputStream();
			String filename = StringUtils.getFilename(file.getOriginalFilename());
			String extension = StringUtils.getFilenameExtension(filename);
			ArtistasDTO artistas = null;
			switch (extension) {
			case "xml":
				artistas = XMLRhythmManager.readArtistasXML(inputStream);
				break;
			case "json":
				artistas = JSONRhythmManager.readArtistasJSON(inputStream);
				break;
			default:
				System.out.println("Error extensión");
				break;
			}
			for (ArtistaDTO artistaDTO : artistas.getArtistas()) {
				if (artisRepos.findByNick(artistaDTO.getNick()) == null) {
					artisRepos.save(new Artista(artistaDTO.getNick()));
					numArtistas ++;
					for (IntegranteDTO integranteDTO : artistaDTO.getIntegrantesDTO().getList()) {
						nuevosIntegrantes.add(new Integrante(integranteDTO.getNick(), integranteDTO.getNombre(),
								integranteDTO.getApellido(), artisRepos.findByNick(artistaDTO.getNick())));
					}
				}
			}

			if (!nuevosIntegrantes.isEmpty()) {
				integRepos.saveAll(nuevosIntegrantes);
			}
			
			numIntegrantes = nuevosIntegrantes.size();
			modelo.addAttribute("mensaje",
					String.format("Se han guardado %d Artistas y %d Integrantes.", numArtistas, numIntegrantes));
			return UsuarioController.importar(request);
		} catch (Exception e) {
			modelo.addAttribute("error", "Error al realizar la importación.");
			return UsuarioController.importar(request);
		}

	}

	@GetMapping("/artistas.xml")
	public ResponseEntity<String> descargarArtistasXML(HttpServletRequest request) throws JAXBException {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde crear musica");
				return null;
			}

		} catch (Exception e) {
			return null;
		}
		String xml = XMLRhythmManager.generateArtistasXML(Utilidades.listArtistas(artisRepos.findAll()));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=artistas.xml");
		return new ResponseEntity<>(xml, headers, HttpStatus.OK);
	}

	@GetMapping("/artistas.json")
	public ResponseEntity<String> descargarArtistasJSON(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (!session.getAttribute("codigo").equals("ADMIN")) {
				System.out.println("login desde crear musica");
				return null;
			}

		} catch (Exception e) {
			return null;
		}
		String json = JSONRhythmManager.generateArtistasJson(Utilidades.listArtistas(artisRepos.findAll()));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=artistas.json");
		return new ResponseEntity<>(json, headers, HttpStatus.OK);
	}

}
