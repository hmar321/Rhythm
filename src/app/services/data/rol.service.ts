import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Rol } from 'src/app/model/data/Rol';

@Injectable({
  providedIn: 'root',
})
export class RolService {
  host: string = 'https://rhythmback-production.up.railway.app';
  api: string = '/api/rol';

  constructor(private http: HttpClient) {}

  findAllRols(): Observable<Rol[]> {
    return this.http.get<Rol[]>(this.host + this.api);
  }

  findRolById(id: number): Observable<Rol> {
    return this.http.get<Rol>(this.host + this.api + '/' + id);
  }

  insertRol(rol: Rol): Observable<Rol> {
    return this.http.post<Rol>(this.host + this.api, rol);
  }

  updateRol(rol: Rol): Observable<boolean> {
    return this.http.put<boolean>(this.host + this.api + '/' + rol.id, rol);
  }

  deleteRol(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }
}
