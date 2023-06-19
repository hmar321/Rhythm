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
  // objeto de la librería rxjs que funciona como un emisor de eventos
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
    this.inconoLabel = 'pi-spin pi-spinner';
  }

  buscarTermino() {
    // espera 0.7s y si se escribe el el buscador se resetea
    // si se pasan los 0.7s realiza la busqueda
    this.textoBuscar.pipe(debounceTime(700)).subscribe((term) => {
      if (term.length != 0) {
        // si el termino es igual al ultimo termino escrito
        if (term === this.ultimoTermino) {
          // busco los resultados en cache
          const resultadosCache = this.cookieService.get('resultadosCache');
          // si tiene valor
          if (resultadosCache) {
            const resultados = JSON.parse(resultadosCache);
            this.artistas = resultados.artistas;
            this.albums = resultados.albums;
            this.canciones = resultados.canciones;
            this.listas = resultados.listas;
            this.inconoLabel = 'pi-search';
          }
        } else {
          // creo un array de observables
          const idUsuario = localStorage.getItem("UsuarioId");
          const observables = [
            this.artistaService.buscarArtistas(term),
            this.albumService.buscarAlbums(term),
            this.cancionService.buscarCanciones(term),
            idUsuario ? this.listaService.buscarListas(term, idUsuario) : of([])
          ];
          // se realizan las peticiones a la api
          forkJoin(observables).subscribe(([artistas, albums, canciones, listas]) => {
            this.artistas = artistas as Artista[];
            this.albums = albums as Album[];
            this.canciones = canciones as Cancion[];
            this.listas = listas as Lista[];
            // se guardan los resultados en cache
            const resultados = { artistas, albums, canciones, listas };
            this.cookieService.set('resultadosCache', JSON.stringify(resultados));
            this.inconoLabel = 'pi-search';
          });
        }
        // si el termino no contiene ultimoTermino se actualiza ultimoTermino
        if (!term.includes(this.ultimoTermino)) {
          this.ultimoTermino = term;
        }
      } else {
        // si el termino no tiene caracteres
        this.mostrarExitos();
        this.inconoLabel = 'pi-search';
      }
    });
  }

  mostrarExitos() {
    // recupero un valor de localStorage y si no ha sido inicilizado es null
    const idUsuario = +localStorage.getItem("UsuarioId")!;
    this.albumService.albumsExitos().subscribe((albums) => (this.albums = albums));
    this.artistaService.artistasExitos().subscribe((artistas) => (this.artistas = artistas));
    this.cancionService.cancionesExitos().subscribe((canciones) => (this.canciones = canciones));
    // operador ternario
    idUsuario ? this.listaService.listasExitos(idUsuario).subscribe((listas) => (this.listas = listas)) : this.listas = [];
  }
}
