import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Album } from 'src/app/model/data/Album';
import { Artista } from 'src/app/model/data/Artista';
import { ArtistaService } from 'src/app/services/data/artista.service';
import { SesionService } from 'src/app/services/util/sesion.service';

@Component({
  selector: 'app-artista',
  templateUrl: './artista.component.html',
  styleUrls: ['./artista.component.css']
})
export class ArtistaComponent implements OnInit {
  tituloArtista: string = '';
  visitas: number = 0;
  portada: string = '';
  albums: Album[] | undefined;
  id:number=-1;
  artista: Artista | undefined;
  constructor(
    private router: Router,
    private artistaService: ArtistaService,
    private sesionService: SesionService,
    private cookieService: CookieService
  ) {
  }
  ngOnInit() {
    this.id = +this.cookieService.get('ArtistaId');
    this.artistaService.findArtistaById(this.id).subscribe((artista) => {
      this.artista = artista;
      this.tituloArtista = this.artista.titulo!;
      this.visitas = this.artista.visitas!;
      this.portada = 'assets/images/' + this.artista.portada!;
      this.albums = this.artista.albums!;
    });
  }

}
