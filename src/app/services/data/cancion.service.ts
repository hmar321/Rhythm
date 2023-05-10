import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Cancion } from 'src/app/model/data/Cancion';

@Injectable({
  providedIn: 'root',
})
export class CancionService {
  host: string = 'http://localhost:7116';
  api: string = '/api/cancion';

  constructor(private http: HttpClient) {}

  findAllCanciones(): Observable<Cancion[]> {
    return this.http.get<Cancion[]>(this.host + this.api);
  }

  findCancionById(id: number): Observable<Cancion> {
    return this.http.get<Cancion>(this.host + this.api + '/' + id);
  }

  insertCancion(cancion: Cancion): Observable<boolean> {
    return this.http.post<boolean>(this.host + this.api, cancion);
  }

  updateCancion(cancion: Cancion): Observable<boolean> {
    return this.http.put<boolean>(this.host + this.api + '/' + cancion.id, cancion);
  }

  deleteCancion(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }
}
