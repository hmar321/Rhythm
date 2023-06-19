import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Album } from 'src/app/model/data/Album';
import { Cancion } from 'src/app/model/data/Cancion';
import { AlbumService } from 'src/app/services/data/album.service';

@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css'],
})
export class AlbumComponent implements OnInit {
  tituloAlbum: string = '';
  artistasNombres: string = '';
  visitas: number = 0;
  portada: string = '';
  canciones: Cancion[] | undefined;
  estreno: Date | undefined;
  id: number = -1;
  album: Album | undefined;
  constructor(
    private albumService: AlbumService,
    private cookieService: CookieService
  ) {
  }
  ngOnInit() {
    if (this.id < 1) {
      this.id = +this.cookieService.get('AlbumId');
    }
    this.albumService.findAlbumById(this.id).subscribe((album) => {
      this.album = album;
      this.tituloAlbum = this.album.titulo!;
      this.artistasNombres = this.album.artistasCadena!;
      this.visitas = this.album.visitas!;
      this.portada = 'assets/images/' + this.album.portada!;
      this.canciones = this.album.canciones!;
      this.estreno = this.album.estreno!;
      album.canciones = [];
      album.visitas = (album.visitas || 0) + 1;
      this.albumService.updateAlbum(album).subscribe();
    });
  }
}
