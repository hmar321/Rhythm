import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Subject, debounceTime, forkJoin, of } from 'rxjs';
import { Album } from 'src/app/model/data/Album';
import { Artista } from 'src/app/model/data/Artista';
import { Cancion } from 'src/app/model/data/Cancion';
import { Lista } from 'src/app/model/data/Lista';
import { AlbumService } from 'src/app/services/data/album.service';
import { ArtistaService } from 'src/app/services/data/artista.service';
import { CancionService } from 'src/app/services/data/cancion.service';
import { ListaService } from 'src/app/services/data/lista.service';
import { SesionService } from 'src/app/services/util/sesion.service';
@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css'],
})
export class BuscarComponent implements OnInit {
  textoBuscar = new Subject<string>();
  busqueda: string = '';
  ultimoTermino: string = '';
  inconoLabel = 'pi-search';
  artistas: Artista[] = [];
  albums: Album[] = [];
  listas: Lista[] = [];
  canciones: Cancion[] = [];
  constructor(
    private artistaService: ArtistaService,
    private albumService: AlbumService,
    private listaService: ListaService,
    private cancionService: CancionService,
    private cookieService: CookieService,
    public sesionService: SesionService
  ) { }

  ngOnInit() {
    this.mostrarExitos();
    this.buscarTermino();
  }

  onKeyUpEvent() {
    this.textoBuscar.next(this.busqueda);
    this.inconoLabel='pi-spin pi-spinner';
  }

  buscarTermino() {
    this.textoBuscar.pipe(debounceTime(700)).subscribe((term) => {
      if (term.length != 0) {
        let resultados: any = {
          artistas: [],
          albums: [],
          listas: [],
          canciones: [],
        };
        if (term === this.ultimoTermino) {
          const resultadosCache = this.cookieService.get('resultadosCache');
          if (resultadosCache) {
            resultados = JSON.parse(resultadosCache);
            this.artistas = resultados.artistas;
            this.albums = resultados.albums;
            this.canciones = resultados.canciones;
            this.listas = resultados.canciones;
            this.inconoLabel='pi-search';
          }

        } else {
          const idUsuario = localStorage.getItem("UsuarioId");
          const observables = [
            this.artistaService.buscarArtistas(term),
            this.albumService.buscarAlbums(term),
            idUsuario ? this.listaService.buscarListas(term, idUsuario) : of([]),
            this.cancionService.buscarCanciones(term)
          ];
          forkJoin(observables).subscribe(([artistas, albums, listas, canciones]) => {
            this.artistas = artistas as Artista[];
            this.albums = albums as Album[];
            this.listas = listas as Lista[];
            this.canciones = canciones as Cancion[];

            const resultados = {
              artistas,
              albums,
              listas,
              canciones
            };
            this.cookieService.set('resultadosCache', JSON.stringify(resultados));
            this.inconoLabel='pi-search';
          });
        }
        if (!term.includes(this.ultimoTermino)) {
          this.ultimoTermino = term;
        }
      } else {
        this.mostrarExitos();
        this.inconoLabel='pi-search';
      }
    });
  }

  mostrarExitos() {
    const idUsuario = +localStorage.getItem("UsuarioId")!;
    this.albumService.albumsExitos().subscribe((albums) => (this.albums = albums));
    this.artistaService.artistasExitos().subscribe((artistas) => (this.artistas = artistas));
    this.cancionService.cancionesExitos().subscribe((canciones) => (this.canciones = canciones));
    this.listaService.listasExitos(idUsuario).subscribe((listas) => (this.listas = listas));
  }
}
