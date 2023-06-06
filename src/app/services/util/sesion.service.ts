import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

import { MessageService } from 'primeng/api';
import { Usuario } from 'src/app/model/data/Usuario';
import { AlbumService } from 'src/app/services/data/album.service';
import { ListaService } from 'src/app/services/data/lista.service';
import { UsuarioService } from 'src/app/services/data/usuario.service';
import { ArtistaService } from 'src/app/services/data/artista.service';

@Injectable({
  providedIn: 'root',
})
export class SesionService {
  private Loged: boolean;
  constructor(
    private usuarioService: UsuarioService,
    private listasService: ListaService,
    private albumService: AlbumService,
    private artistaService: ArtistaService,
    private router: Router,
    private cookieService: CookieService,
    private messageService: MessageService
  ) {
    this.Loged = false;
    if (localStorage.getItem('UsuarioId') != null) {
      this.actualizarUsuario(+localStorage.getItem('UsuarioId')!);
      this.Loged = true;
    }
  }
  loguear(email: string, password: string) {
    this.usuarioService.findUsuarioByLogin(email, password).subscribe({
      next: (usuario) => {
        localStorage.setItem('UsuarioId', usuario.id + '');
        this.listasService
          .findListaById(usuario.listasCreadas?.at(0)?.id!)
          .subscribe((lista) => {
            usuario.listasCreadas![0] = lista;
            this.guardarUsuario(usuario);
            this.iniciarSesion();
          });
      },
      error: (error) => {
        this.mostrarError();
        console.error(error);
      },
    });
  }

  cerrarSesion() {
    this.cookieService.deleteAll();
    localStorage.clear();
    this.Loged = false;
  }
  actualizarUsuario(id: number) {
    this.usuarioService.findUsuarioById(id).subscribe((usuario) => {
      this.listasService
        .findListaById(usuario.listasCreadas?.at(0)?.id!)
        .subscribe((lista) => {
          usuario.listasCreadas![0] = lista;
          this.guardarUsuario(usuario);
        });
    });
  }

  isLoged() {
    return this.Loged;
  }

  guardarUsuario(usuario: Usuario) {
    localStorage.setItem('UsuarioId', usuario.id + '');
    localStorage.setItem('UsuarioNombre', usuario.nombre!);
    localStorage.setItem('UsuarioNick', usuario.nick!);
    localStorage.setItem('UsuarioEmail', usuario.email!);
    localStorage.setItem('UsuarioPassword', usuario.password!);
    localStorage.setItem('UsuarioRol', JSON.stringify(usuario.rol));
    localStorage.setItem('UsuarioArtistas', JSON.stringify(usuario.artistas));
    localStorage.setItem('UsuarioAlbums', JSON.stringify(usuario.albums));
    localStorage.setItem('UsuarioListasCreadas', JSON.stringify(usuario.listasCreadas));
    localStorage.setItem('UsuarioListaFavoritos', JSON.stringify(usuario.listasCreadas?.at(0)));
    localStorage.setItem('UsuarioCanciones', JSON.stringify(usuario.listasCreadas?.at(0)?.canciones));
    localStorage.setItem('UsuarioListas', JSON.stringify(usuario.listas));
    this.Loged = true;
  }

  iniciarSesion() {
    this.mostrarExito();
    this.router.navigate(['']);
  }

  mostrarExito() {
    this.messageService.add({
      severity: 'success',
      summary: 'Éxito',
      detail: 'Se ha iniciado sesión correctamente.',
    });
  }
  mostrarError() {
    this.messageService.add({
      severity: 'warn',
      summary: 'Error',
      detail: 'No se ha encontrado el usuario.',
    });
  }
}
