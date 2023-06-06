import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Cancion } from 'src/app/model/data/Cancion';

@Injectable({
  providedIn: 'root',
})
export class CancionService {
  host: string = 'https://rhythmback-production.up.railway.app';
  api: string = '/api/cancion';

  constructor(private http: HttpClient) { }

  findAllCanciones(): Observable<Cancion[]> {
    return this.http.get<Cancion[]>(this.host + this.api);
  }

  findCancionById(id: number): Observable<Cancion> {
    return this.http.get<Cancion>(this.host + this.api + '/' + id);
  }

  insertCancion(cancion: Cancion): Observable<Cancion> {
    return this.http.post<Cancion>(this.host + this.api, cancion);
  }

  updateCancion(cancion: Cancion): Observable<boolean> {
    return this.http.put<boolean>(this.host + this.api + '/' + cancion.id, cancion);
  }

  deleteCancion(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }
  buscarCanciones(term: string): Observable<Cancion[]> {
    return this.http.get<Cancion[]>(
      this.host + this.api + '/buscador?termino=' + term
    );
  }
  cancionesExitos(): Observable<Cancion[]> {
    return this.http.get<Cancion[]>(
      this.host + this.api + '/exitos'
    );
  }
  buscarCancionesForLista(term: string, idLista: number): Observable<Cancion[]> {
    let params = new HttpParams()
      .set('termino', term)
      .set('idLista', idLista);
    return this.http.get<Cancion[]>(this.host + this.api + '/BuscadorForLista', { params });
  }

  removeListaFromCancionListas(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.delete<boolean>(this.host + this.api + '/QuitarLista', { params });
  }

  addListaIntoCancionListas(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.put<boolean>(this.host + this.api + '/AddLista?', {}, { params });
  }

  removeAlbumFromCancionAlbums(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.delete<boolean>(this.host + this.api + '/QuitarAlbum', { params });
  }

  addAlbumIntoCancionAlbums(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.put<boolean>(this.host + this.api + '/AddAlbum?', {}, { params });
  }

  removeArtistaFromCancionArtistas(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.delete<boolean>(this.host + this.api + '/QuitarArtista', { params });
  }

  addArtistaIntoCancionArtistas(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.put<boolean>(this.host + this.api + '/AddArtista?', {}, { params });
  }

  removeGeneroFromCancionGeneros(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.delete<boolean>(this.host + this.api + '/QuitarGenero', { params });
  }

  addGeneroIntoCancionGeneros(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.put<boolean>(this.host + this.api + '/AddGenero?', {}, { params });
  }
}
