import { Component, OnInit } from '@angular/core';
import { CancionService } from 'src/app/services/data/cancion.service';
import { CookieService } from 'ngx-cookie-service';
import { Cancion } from 'src/app/model/data/Cancion';

@Component({
  selector: 'app-cancion',
  templateUrl: './cancion.component.html',
  styleUrls: ['./cancion.component.css'],
})
export class CancionComponent implements OnInit {
  tituloCancion: string = '';
  artistasNombres: string = '';
  visitas: number = 0;
  portada: string = '';
  estreno: Date | undefined;
  lyrics: string[] = [];
  letra: string = '';
  letra2: string = '';
  id: number = -1;
  cancion: Cancion | undefined;
  duracion: Date | undefined;
  constructor(
    private cancionService: CancionService,
    private cookieService: CookieService
  ) { }

  ngOnInit() {
    this.id = +this.cookieService.get('CancionId');
    this.cancionService.findCancionById(this.id).subscribe((cancion) => {
      this.cancion = cancion;
      this.tituloCancion = this.cancion.titulo!;
      this.artistasNombres = this.cancion.artistasCadena!;
      this.visitas = this.cancion.visitas!;
      this.portada = 'assets/images/' + this.cancion.portada!;
      this.estreno = this.cancion.estreno!;
      this.duracion = this.cancion.duracion!;
      if (
        this.cancion.lyrics?.length == 0 ||
        this.cancion.lyrics == null
      ) {
        this.letra = 'Letra sin asignar.';
      } else {
        this.lyrics = this.cancion.lyrics!.split(';');
        const halfLength = this.lyrics.length / 2;
        this.letra = this.lyrics.slice(0, halfLength).join('\n');
        this.letra2 = this.lyrics.slice(halfLength).join('\n');
      }
      cancion.visitas=(cancion.visitas||0)+1;
      this.cancionService.updateCancion(cancion).subscribe();
    });
  }
}
