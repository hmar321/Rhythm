import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UsuarioService } from './data/usuario.service';

@Injectable({
  providedIn: 'root',
})
export class SesionService {
  isLoged: boolean;
  idUsuario: number;
  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private cookieService: CookieService
  ) {
    this.isLoged = false;
    this.idUsuario = -1;
  }
  loguear(email: string, password: string) {
    this.usuarioService.findUsuarioByLogin().subscribe((dataUsuario) => {
      this.idUsuario = dataUsuario.id;
    });
    if (this.idUsuario != -1) {
      this.cookieService.set('usuarioId',this.idUsuario.toString());
      this.isLoged = true;
      this.router.navigate(['']);
    }
  }
  cerrarSesion() {
    this.isLoged = false;
    this.cookieService.deleteAll();
  }
}
