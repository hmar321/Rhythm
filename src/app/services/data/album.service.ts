import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { Album } from 'src/app/model/data/Album';

@Injectable({
  providedIn: 'root',
})
export class AlbumService {
  host: string = 'https://rhythmback-production.up.railway.app';
  api: string = '/api/album';

  constructor(private http: HttpClient) {}

  findAllAlbums(): Observable<Album[]> {
    return this.http.get<Album[]>(this.host + this.api);
  }

  findAlbumById(id: number): Observable<Album> {
    return this.http.get<Album>(this.host + this.api + '/' + id);
  }

  insertAlbum(album: Album): Observable<Album> {
    return this.http.post<Album>(this.host + this.api, album);
  }

  updateAlbum(album: Album): Observable<boolean> {
    return this.http.put<boolean>(this.host + this.api + '/' + album.id, album);
  }

  deleteAlbum(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.host + this.api + '/' + id);
  }
  buscarAlbums(term: string): Observable<Album[]> {
    return this.http.get<Album[]>(
      this.host + this.api + '/buscador?termino=' + term
    );
  }
  albumsExitos(): Observable<Album[]> {
    return this.http.get<Album[]>(
      this.host + this.api + '/exitos'
    );
  }
}
