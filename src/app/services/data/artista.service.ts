import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Artista } from 'src/app/model/data/Artista';

@Injectable({
  providedIn: 'root',
})
export class ArtistaService {
  host: string = 'https://rhythmback-production.up.railway.app';
  api: string = '/api/artista';

  constructor(private http: HttpClient) {}

  findAllArtistas(): Observable<Artista[]> {
    return this.http.get<Artista[]>(this.host + this.api);
  }

  findArtistaById(id: number): Observable<Artista> {
    return this.http.get<Artista>(this.host + this.api + '/' + id);
  }

  insertArtista(artista: Artista): Observable<Artista> {
    return this.http.post<Artista>(this.host + this.api, artista);
  }

  updateArtista(artista: Artista): Observable<boolean> {
    return this.http.put<boolean>(
      this.host + this.api + '/' + artista.id,
      artista
    );
  }

  deleteArtista(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }

  buscarArtistas(term: string): Observable<Artista[]> {
    return this.http.get<Artista[]>(
      this.host + this.api + '/buscador?termino=' + term
    );
  }
  artistasExitos(): Observable<Artista[]> {
    return this.http.get<Artista[]>(
      this.host + this.api + '/exitos'
    );
  }
}
