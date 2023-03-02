package com.dam2.rhythm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dam2.rhythm.model.Rol;
import com.dam2.rhythm.model.Usuario;
import com.dam2.rhythm.repository.RolRepositorio;
import com.dam2.rhythm.repository.UsuarioRepositorio;

@SpringBootApplication
public class RhythmApplication {
	public static void main(String[] args) {
		SpringApplication.run(RhythmApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner cargarDatos(RolRepositorio rolRepos,UsuarioRepositorio usuarRepos) {
		return (args) -> {
			if (rolRepos.count()==0) {
				rolRepos.save(new Rol("ADMIN"));
				rolRepos.save(new Rol("USER"));
			}
			if (usuarRepos.count()==0) {
				usuarRepos.save(new Usuario(rolRepos.findByNombre("ADMIN"), "admin", "nombre", "apellido", "admin@correo.com", "admin"));
				usuarRepos.save(new Usuario(rolRepos.findByNombre("USER"), "Pepe", "Pepe", "Perez", "pepe@correo.com", "pepe"));
				usuarRepos.save(new Usuario(rolRepos.findByNombre("USER"), "Luis", "Luis", "Perez", "luis@correo.com", "luis"));
			}
			
		};
	}
}