import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Lista } from 'src/app/model/data/Lista';

@Injectable({
  providedIn: 'root',
})
export class ListaService {
  host: string = 'https://rhythmback-production.up.railway.app';
  api: string = '/api/lista';

  constructor(private http: HttpClient) { }

  findAllListas(): Observable<Lista[]> {
    return this.http.get<Lista[]>(this.host + this.api);
  }

  findListaById(id: number): Observable<Lista> {
    return this.http.get<Lista>(this.host + this.api + '/' + id);
  }

  insertLista(lista: Lista): Observable<Lista> {
    return this.http.post<Lista>(this.host + this.api, lista);
  }

  updateLista(lista: Lista): Observable<boolean> {
    return this.http.put<boolean>(this.host + this.api + '/' + lista.id, lista);
  }

  deleteLista(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }
  buscarListas(term: string, idCreador: string): Observable<Lista[]> {
    let params = new HttpParams()
      .set('termino', term)
      .set('id', idCreador);

    return this.http.get<Lista[]>(this.host + this.api + '/buscador', { params });
  }
  listasExitos(id: number): Observable<Lista[]> {
    const params = new HttpParams()
      .set('id', id);

    return this.http.get<Lista[]>(this.host + this.api + '/exitos?', { params });
  }


  removeCancionFromListaCanciones(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.delete<boolean>(this.host + this.api + '/QuitarCancion', { params });
  }

  addCancionIntoUsuarioCanciones(id: number, idElemento: number): Observable<boolean> {
    const params = new HttpParams()
      .set('id', id)
      .set('idElemento', idElemento);
    return this.http.put<boolean>(this.host + this.api + '/AddCancion?', {}, { params });
  }
}
