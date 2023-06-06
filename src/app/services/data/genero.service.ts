import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Genero } from 'src/app/model/data/Genero';
@Injectable({
  providedIn: 'root',
})
export class GeneroService {
  host: string = 'https://rhythmback-production.up.railway.app';
  api: string = '/api/genero';

  constructor(private http: HttpClient) {}

  findAllGeneros(): Observable<Genero[]> {
    return this.http.get<Genero[]>(this.host + this.api);
  }

  findGeneroById(id: number): Observable<Genero> {
    return this.http.get<Genero>(this.host + this.api + '/' + id);
  }

  insertGenero(genero: Genero): Observable<Genero> {
    return this.http.post<Genero>(this.host + this.api, genero);
  }

  updateGenero(genero: Genero): Observable<boolean> {
    return this.http.put<boolean>(this.host + this.api + '/' + genero.id, genero);
  }

  deleteGenero(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }
}
