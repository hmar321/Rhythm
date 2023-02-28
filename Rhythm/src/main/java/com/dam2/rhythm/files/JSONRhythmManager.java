package com.dam2.rhythm.files;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.dam2.rhythm.dto.ArtistasDTO;
import com.dam2.rhythm.dto.MusicasDTO;
import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Musica;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONRhythmManager {

	private static final Gson gson = new Gson();

	public static String generateMusicJson(List<Musica> musicas) {
		MusicasDTO musicasDTO=new MusicasDTO(musicas.stream().map(Musica::mappingToMusicaDTO).collect(Collectors.toList()));
		return gson.toJson(musicasDTO);
	}

	public static MusicasDTO readMusicasJSON(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String data = reader.lines().collect(Collectors.joining());
		return gson.fromJson(data, new TypeToken<MusicasDTO>() {}.getType());
	}
	
	public static String generateArtistasJson(List<Artista> artistas) {
		ArtistasDTO artistasDTO =new ArtistasDTO(artistas.stream().map(Artista::mappingToArtistaDTO).collect(Collectors.toList()));
		return gson.toJson(artistasDTO);
	}

	public static ArtistasDTO readArtistasJSON(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String data = reader.lines().collect(Collectors.joining());
		return gson.fromJson(data, new TypeToken<ArtistasDTO>() {}.getType());
	}
}
