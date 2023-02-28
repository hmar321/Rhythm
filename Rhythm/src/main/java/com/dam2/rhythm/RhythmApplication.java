package com.dam2.rhythm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dam2.rhythm.model.Artista;
import com.dam2.rhythm.model.Genero;
import com.dam2.rhythm.model.Integrante;
import com.dam2.rhythm.model.Musica;
import com.dam2.rhythm.model.Rol;
import com.dam2.rhythm.model.Usuario;
import com.dam2.rhythm.repository.ArtistaRepositorio;
import com.dam2.rhythm.repository.GeneroRepositorio;
import com.dam2.rhythm.repository.IntegranteRepositorio;
import com.dam2.rhythm.repository.MusicaRepositorio;
import com.dam2.rhythm.repository.RolRepositorio;
import com.dam2.rhythm.repository.UsuarioRepositorio;

@SpringBootApplication
public class RhythmApplication {
	public static void main(String[] args) {
		SpringApplication.run(RhythmApplication.class, args);
	}

	//@Bean
	public CommandLineRunner cargarDatos(RolRepositorio rolRepos,UsuarioRepositorio usuarRepos,ArtistaRepositorio artisRepos,IntegranteRepositorio integRespos,GeneroRepositorio generRepos,MusicaRepositorio musicRepos) {
		return (args) -> {
			rolRepos.save(new Rol("ADMIN"));
			rolRepos.save(new Rol("USER"));
			usuarRepos.save(new Usuario(rolRepos.findByNombre("ADMIN"), "admin", "nombre", "apellido", "admin@correo.com", "admin"));
			usuarRepos.save(new Usuario(rolRepos.findByNombre("USER"), "Pepe", "Pepe", "Perez", "pepe@correo.com", "pepe"));
			usuarRepos.save(new Usuario(rolRepos.findByNombre("USER"), "Luis", "Luis", "Perez", "luis@correo.com", "luis"));
			/*
			Artista[] artistas = {
				    new Artista("Michael Jackson"),
				    new Artista("The Weeknd"),
				    new Artista("Queen"),
				    new Artista("BTS"),
				    new Artista("Metal Gear Rising: Revengeance"),
				    new Artista("GRIS"),
				    new Artista("Bee Gees"),
				    new Artista("Earth, Wind & Fire")
				};
			artisRepos.saveAll(Arrays.asList(artistas));
			Integrante[] integrantes = {
					new Integrante("Michael Jackson", "Michael Joseph", "Jackson", artisRepos.findByNick("Michael Jackson")),
					new Integrante("The Weeknd", "Abel Makkonen", "Tesfaye", artisRepos.findByNick("The Weeknd")),
					new Integrante("Freddie Mercury", "Freddie", "Mercury", artisRepos.findByNick("Queen")),
					new Integrante("Brian", "Brian", "May", artisRepos.findByNick("Queen")),
					new Integrante("Roger", "Roger", "Taylor", artisRepos.findByNick("Queen")),
					new Integrante("John", "John", "Deacon", artisRepos.findByNick("Queen")),
					new Integrante("Jin", "Kim", "Seok-jin", artisRepos.findByNick("BTS")),
					new Integrante("Suga", "Min", "Yoon-gi", artisRepos.findByNick("BTS")),
					new Integrante("J-Hope", "Jung", "Ho-seok", artisRepos.findByNick("BTS")),
					new Integrante("RM", "Kim", "Nam-joon", artisRepos.findByNick("BTS")),
					new Integrante("Jimin", "Park", "Ji-min", artisRepos.findByNick("BTS")),
					new Integrante("V", "Kim", "Tae-hyung", artisRepos.findByNick("BTS")),
					new Integrante("Jungkook", "Jeon", "Jung-kook", artisRepos.findByNick("BTS")),
					new Integrante("Metal Gear", "Konami", "game", artisRepos.findByNick("Metal Gear Rising: Revengeance")),
					new Integrante("GRIS", "Nomada Studio", "game", artisRepos.findByNick("GRIS")),
					new Integrante("Barry", "Barry", "Gibb", artisRepos.findByNick("Bee Gees")),
					new Integrante("Maurice", "Maurice", "Gibb", artisRepos.findByNick("Bee Gees")),
					new Integrante("Robin", "Robin", "Gibb", artisRepos.findByNick("Bee Gees")),
					new Integrante("Vince", "Vince", "Melouney", artisRepos.findByNick("Bee Gees")),
					new Integrante("Colin", "Colin", "Petersen", artisRepos.findByNick("Bee Gees")),
					new Integrante("Geoff", "Geoff", "Bridgford", artisRepos.findByNick("Bee Gees")),
					new Integrante("Philip", "Philip", "Bailey", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Verdine", "Verdine", "White", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Ralph", "Ralph", "Johnson", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("David", "David", "Whitworth", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Philip", "Philip", "Bailey Jr", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Myron", "Myron", "McKinley", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("John", "John", "Paris", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Morris", "Morris", "O'Connor", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Serg", "Serg", "Dimitrijevic", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Gary", "Gary", "Bias", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Reggie", "Reggie", "Young", artisRepos.findByNick("Earth, Wind & Fire")),
					new Integrante("Bobby", "Bobby", "Burns Jr", artisRepos.findByNick("Earth, Wind & Fire"))
				};
			integRespos.saveAll(Arrays.asList(integrantes));
			
			Genero[] generos = {
					new Genero("R&B", "Ritmo y Blues con influencias de Gospel y Jazz."),
					new Genero("New Jack Swing", "Fusión de R&B, Hip-hop y Funk con un ritmo pegajoso."),
					new Genero("Pop", "Género musical comercial y pegajoso con una gran base de fans."),
					new Genero("Funk", "Ritmos de bajo y batería destacados, con énfasis en la melodía."),
					new Genero("Soul", "Género musical emotivo y profundo que transmite sentimientos y experiencias personales."),
					new Genero("Opera", "Música clásica con voces poderosas y dramáticas que cuentan historias."),
					new Genero("Rock", "Música de guitarras con energía y actitud rebelde."),
					new Genero("New wave", "Género musical experimental con sintetizadores y efectos de guitarra."),
					new Genero("Electronic", "Género musical basado en sintetizadores y secuenciadores."),
					new Genero("Metal", "Género musical pesado y enérgico con guitarras distorsionadas y ritmos fuertes."),
					new Genero("Classical", "Música orquestal tradicional, a menudo interpretada por una orquesta sinfónica."),
					new Genero("Ambient", "Género musical tranquilo y relajante con sonidos atmosféricos y texturas."),
					new Genero("Disco", "Música bailable con ritmos pegajosos y énfasis en la sección de vientos."),
					new Genero("Soft-rock", "Género musical suave y fácil de escuchar, a menudo con baladas románticas."),
					new Genero("Hip-hop", "Género musical basado en ritmos y rimas habladas con una base electrónica."),
					new Genero("Dance-pop", "Música electrónica bailable con letras pegajosas y melodías fáciles de recordar."),
					new Genero("Hard-rock", "Género musical pesado y poderoso con guitarras y batería enérgicas."),
					new Genero("Heavy-metal", "Género musical agresivo con guitarras distorsionadas y ritmos fuertes."),
					new Genero("Synth-pop", "Género musical electrónico con sintetizadores y letras emotivas."),
					new Genero("Dark-pop", "Género musical melancólico y emotivo, a menudo con letras tristes."),
					new Genero("Progressive-rock", "Género musical experimental y progresivo con estructuras de canciones complejas y cambios de ritmo inesperados.")
			};
			generRepos.saveAll(Arrays.asList(generos));

			Map<String, Musica> musicas = new HashMap<>();
			musicas.put("mic1",new Musica("Thriller", artisRepos.findByNick("Michael Jackson"), "1982-11-29","https://www.youtube.com/embed/sOnqjkJTMaA"));
			musicas.put("mic2",new Musica("Remember the Time", artisRepos.findByNick("Michael Jackson"), "1992-01-14","https://www.youtube.com/embed/LeiFF0gvqcc"));
			musicas.put("mic3",new Musica("Slave to the Rhythm", artisRepos.findByNick("Michael Jackson"), "2014-05-13","https://www.youtube.com/embed/MuTgX_A52Eo"));
			musicas.put("wee1",new Musica("Is There Someone Else?", artisRepos.findByNick("The Weeknd"), "2022-01-03", "https://www.youtube.com/embed/1XqIWr_WqM4"));
			musicas.put("wee2",new Musica("After Hours", artisRepos.findByNick("The Weeknd"), "2020-02-20", "https://www.youtube.com/embed/ygTZZpVkmKg"));
			musicas.put("wee3",new Musica("Don't Break My Heart", artisRepos.findByNick("The Weeknd"), "2022-01-22", "https://www.youtube.com/embed/OeqV6oyNHc0"));
			musicas.put("que1",new Musica("Bohemian Rhapsody", artisRepos.findByNick("Queen"), "1975-10-31", "https://www.youtube.com/embed/fJ9rUzIMcZQ"));
			musicas.put("que2",new Musica("I Want to Break Free", artisRepos.findByNick("Queen"), "1984-04-02", "https://www.youtube.com/embed/f4Mc-NYPHaQ"));
			musicas.put("que3",new Musica("Another One Bites the Dust", artisRepos.findByNick("Queen"), "1980-09-13", "https://www.youtube.com/embed/rY0WxgSXdEE"));
			musicas.put("bts1",new Musica("DOPE", artisRepos.findByNick("BTS"), "2015-06-22", "https://www.youtube.com/embed/H8lYMWZD5P8"));
			musicas.put("bts2",new Musica("FIRE", artisRepos.findByNick("BTS"), "2016-05-02", "https://www.youtube.com/embed/4ujQOR2DMFM"));
			musicas.put("bts3",new Musica("Not Today", artisRepos.findByNick("BTS"), "2017-02-19", "https://www.youtube.com/embed/9DwzBICPhdM"));
			musicas.put("mgr1",new Musica("Rules of nature", artisRepos.findByNick("Metal Gear Rising: Revengeance"), "2013-02-19", "https://www.youtube.com/embed/jOpzP33_USs"));
			musicas.put("mgr2",new Musica("The only thing i know for real", artisRepos.findByNick("Metal Gear Rising: Revengeance"), "2013-02-19", "https://www.youtube.com/embed/CXRxRTW6nXg"));
			musicas.put("mgr3",new Musica("It has to be this way", artisRepos.findByNick("Metal Gear Rising: Revengeance"), "2013-02-19", "https://www.youtube.com/embed/N72U-NFu44k"));
			musicas.put("gri1",new Musica("Gris, Pt.1", artisRepos.findByNick("GRIS"), "2018-12-13", "https://www.youtube.com/embed/c_2gHmpZmzU"));
			musicas.put("gri2",new Musica("Gris, Pt.2", artisRepos.findByNick("GRIS"), "2018-12-13", "https://www.youtube.com/embed/InQk79Ll9u4"));
			musicas.put("gri3",new Musica("In Your Hands", artisRepos.findByNick("GRIS"), "2018-12-13", "https://www.youtube.com/embed/FLlenOmOWxI"));
			musicas.put("bee1",new Musica("Stayin' Alive", artisRepos.findByNick("Bee Gees"), "1977-12-31", "https://www.youtube.com/embed/fNFzfwLM72c"));
			musicas.put("bee2",new Musica("Night Fever", artisRepos.findByNick("Bee Gees"), "1977-12-31", "https://www.youtube.com/embed/SkypZuY6ZvA"));
			musicas.put("bee3",new Musica("How Deep Is Your Love", artisRepos.findByNick("Bee Gees"), "1977-12-31", "https://www.youtube.com/embed/XpqqjU7u5Yc"));
			musicas.put("ewf1",new Musica("September", artisRepos.findByNick("Earth, Wind & Fire"), "1978-09-22", "https://www.youtube.com/embed/Gs069dndIYk"));
			musicas.put("ewf2",new Musica("Boogie Wonderland", artisRepos.findByNick("Earth, Wind & Fire"), "1979-04-06", "https://www.youtube.com/embed/god7hAPv8f0"));
			musicas.put("ewf3",new Musica("Let's Groove", artisRepos.findByNick("Earth, Wind & Fire"), "1981-08-24", "https://www.youtube.com/embed/Lrle0x_DHBM"));
			
			musicas.get("mic1").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("R&B"),generRepos.findByNombre("New Jack Swing")));
			musicas.get("mic2").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("Pop"),generRepos.findByNombre("R&B")));
			musicas.get("mic3").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("Pop"),generRepos.findByNombre("R&B")));
			musicas.get("wee1").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("R&B"),generRepos.findByNombre("Soul")));
			musicas.get("wee2").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Dark-pop"),generRepos.findByNombre("Synth-pop"),generRepos.findByNombre("R&B")));
			musicas.get("wee3").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("R&B"),generRepos.findByNombre("Soul")));		
			musicas.get("que1").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Progressive-rock"),generRepos.findByNombre("Opera"),generRepos.findByNombre("Hard-rock"),generRepos.findByNombre("Heavy-metal")));
			musicas.get("que2").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Rock"),generRepos.findByNombre("New wave")));
			musicas.get("que3").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Rock"),generRepos.findByNombre("Funk")));			
			musicas.get("bts1").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Hip-hop"),generRepos.findByNombre("Dance-pop")));
			musicas.get("bts2").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Hip-hop"),generRepos.findByNombre("Dance-pop")));
			musicas.get("bts3").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Hip-hop"),generRepos.findByNombre("Dance-pop")));			
			musicas.get("mgr1").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Metal"),generRepos.findByNombre("Electronic")));
			musicas.get("mgr2").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Metal"),generRepos.findByNombre("Electronic")));
			musicas.get("mgr3").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Metal"),generRepos.findByNombre("Electronic")));			
			musicas.get("gri1").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Classical"),generRepos.findByNombre("Ambient")));
			musicas.get("gri2").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Classical"),generRepos.findByNombre("Ambient")));
			musicas.get("gri3").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Classical"),generRepos.findByNombre("Ambient")));			
			musicas.get("bee1").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Disco")));
			musicas.get("bee2").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Disco")));
			musicas.get("bee3").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Pop"),generRepos.findByNombre("Soft-rock")));			
			musicas.get("ewf1").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("Soul")));
			musicas.get("ewf2").getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("Disco")));
			musicas.get("ewf3")	.getGeneros().addAll(Arrays.asList(generRepos.findByNombre("Funk"),generRepos.findByNombre("R&B")));
			
			musicRepos.saveAll(musicas.values());
			*/
		};
	}
}