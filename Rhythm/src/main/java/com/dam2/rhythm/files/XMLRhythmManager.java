package com.dam2.rhythm.files;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import com.dam2.rhythm.dto.ArtistasDTO;
import com.dam2.rhythm.dto.MusicasDTO;
import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Musica;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XMLRhythmManager {
	public static ArtistasDTO readArtistasXML(InputStream inputStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ArtistasDTO.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ArtistasDTO artistasDTO = (ArtistasDTO) jaxbUnmarshaller.unmarshal(inputStream);
		return artistasDTO;
	}
	
	public static String generateArtistasXML(List<Artista> artistas) throws JAXBException {
		ArtistasDTO artistasDTO=new ArtistasDTO(artistas.stream().map(Artista::mappingToArtistaDTO).collect(Collectors.toList()));
	    JAXBContext jaxbContext = JAXBContext.newInstance(ArtistasDTO.class);
	    Marshaller marshaller = jaxbContext.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    StringWriter sw = new StringWriter();
	    marshaller.marshal(artistasDTO, sw);
	    return sw.toString();
	}
	
	public static MusicasDTO readMusicasXML(InputStream inputStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MusicasDTO.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MusicasDTO musicasDTO = (MusicasDTO) jaxbUnmarshaller.unmarshal(inputStream);
		return musicasDTO;
	}
	
	public static String generateMusicasXML(List<Musica> musicas) throws JAXBException {
		MusicasDTO artistasDTO=new MusicasDTO(musicas.stream().map(Musica::mappingToMusicaDTO).collect(Collectors.toList()));
	    JAXBContext jaxbContext = JAXBContext.newInstance(MusicasDTO.class);
	    Marshaller marshaller = jaxbContext.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    StringWriter sw = new StringWriter();
	    marshaller.marshal(artistasDTO, sw);
	    return sw.toString();
	}
}
