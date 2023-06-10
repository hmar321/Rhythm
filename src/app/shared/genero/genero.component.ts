import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Cancion } from 'src/app/model/data/Cancion';
import { Genero } from 'src/app/model/data/Genero';
import { GeneroService } from 'src/app/services/data/genero.service';

@Component({
  selector: 'app-genero',
  templateUrl: './genero.component.html',
  styleUrls: ['./genero.component.css'],
})
export class GeneroComponent implements OnInit {
  tituloGenero: string = '';
  visitas: number = 0;
  portada: string = '';
  canciones: Cancion[] | undefined;
  estreno: Date | undefined;
  id: number = -1;
  genero: Genero | undefined;
  constructor(
    private generoService: GeneroService,
    private cookieService: CookieService
  ) { }
  ngOnInit() {
    this.id = +this.cookieService.get('GeneroId');
    this.generoService.findGeneroById(this.id).subscribe((genero) => {
      this.genero = genero;
      this.tituloGenero = this.genero.titulo!;
      this.visitas = this.genero.visitas!;
      this.portada = 'assets/images/' + this.genero.portada!;
      this.canciones = this.genero.canciones!;
      genero.canciones=[];
      genero.visitas = (genero.visitas || 0) + 1;
      this.generoService.updateGenero(genero).subscribe();
    });
  }
}
