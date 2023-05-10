import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Lista } from 'src/app/model/data/Lista';

@Injectable({
  providedIn: 'root',
})
export class ListaService {
  host: string = 'http://localhost:7116';
  api: string = '/api/lista';

  constructor(private http: HttpClient) {}

  findAllListas(): Observable<Lista[]> {
    return this.http.get<Lista[]>(this.host + this.api);
  }

  findListaById(id: number): Observable<Lista> {
    return this.http.get<Lista>(this.host + this.api + '/' + id);
  }

  insertLista(lista: Lista): Observable<boolean> {
    return this.http.post<boolean>(this.host + this.api, lista);
  }

  updateLista(lista: Lista): Observable<boolean> {
    return this.http.put<boolean>(this.host + this.api + '/' + lista.id, lista);
  }

  deleteLista(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }
}
