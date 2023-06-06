import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Usuario } from 'src/app/model/data/Usuario';
@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  host: string = 'https://rhythmback-production.up.railway.app';
  api: string = '/api/usuario';

  constructor(private http: HttpClient) { }

  findAllUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.host + this.api);
  }

  findUsuarioById(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(this.host + this.api + '/' + id);
  }

  insertUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.host + this.api, usuario);
  }

  updateUsuario(usuario: Usuario): Observable<boolean> {
    return this.http.put<boolean>(
      this.host + this.api + '/' + usuario.id,
      usuario
    );
  }

  deleteUsuario(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }

  findUsuarioByLogin(email: string, password: string): Observable<Usuario> {
    const params = new HttpParams()
      .set('email', email)
      .set('password', password);
    return this.http.get<Usuario>(this.host + this.api + '/login?', { params });
  }

  removeListaFromUsuarioListas(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.delete<boolean>(this.host + this.api + '/QuitarLista', { params });
  }

  removeAlbumFromUsuarioAlbums(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.delete<boolean>(this.host + this.api + '/QuitarAlbum', { params });
  }

  removeArtistaFromUsuarioArtistas(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.delete<boolean>(this.host + this.api + '/QuitarArtista', { params });
  }

  addListaIntoUsuarioListas(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.put<boolean>(this.host + this.api + '/AddLista?', {}, { params });
  }

  addAlbumIntoUsuarioAlbums(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.put<boolean>(this.host + this.api + '/AddAlbum?', {}, { params });
  }

  addArtistaIntoUsuarioArtistas(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.put<boolean>(this.host + this.api + '/AddArtista?', {}, { params });
  }
}
