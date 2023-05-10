import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Usuario } from 'src/app/model/data/Usuario';
@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  host: string = 'http://localhost:7116';
  api: string = '/api/usuario';

  constructor(private http: HttpClient) {}

  findAllUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.host + this.api);
  }

  findUsuarioById(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(this.host + this.api + '/' + id);
  }

  insertUsuario(usuario: Usuario): Observable<boolean> {
    return this.http.post<boolean>(this.host + this.api, usuario);
  }

  updateUsuario(usuario: Usuario): Observable<boolean> {
    return this.http.put<boolean>(this.host + this.api + '/' + usuario.id, usuario);
  }

  deleteUsuario(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }

  findUsuarioByLogin(): Observable<Usuario> {
    return this.http.get<Usuario>(this.host + this.api);//hay que pasar parametros
  }
}