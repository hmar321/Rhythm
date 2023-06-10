import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Cancion } from 'src/app/model/data/Cancion';
import { CancionService } from 'src/app/services/data/cancion.service';
import { ListaService } from 'src/app/services/data/lista.service';
import { FavoritoService } from 'src/app/services/util/favorito.service';
import { CookieService } from 'ngx-cookie-service';
import { SesionService } from './../../services/util/sesion.service';

@Component({
  selector: 'app-canciones-tabla',
  templateUrl: './canciones-tabla.component.html',
  styleUrls: ['./canciones-tabla.component.css'],
})
export class CancionesTablaComponent implements OnInit {
  @Input() canciones: any;
  @Input() addBoton: any;
  @Input() inListaCreada: any;
  @Input() inFavoritos: any;
  @Output() addCanciones = new EventEmitter<string>();
  btnEliminar: boolean = false;
  isLoged:boolean=false;
  constructor(private sesionService:SesionService,public favoritoService: FavoritoService, private listaService: ListaService, private cancionService: CancionService, private cookieService: CookieService) {
  }

  ngOnInit() {
    this.isLoged=this.sesionService.isLoged();
    if (this.addBoton == null) {
      this.addBoton = false;
    }
    if (this.inListaCreada == null) {
      this.inListaCreada = false;
    }
    if (this.inFavoritos == null) {
      this.inFavoritos = false;
    }
    if (this.canciones != null) {
      this.canciones.forEach((cancion: Cancion) => {
        cancion.enFavorito = this.favoritoService.existeEnCanciones(cancion);
      });
    }
  }

  accionFavorito(cancion: Cancion) {
    if (cancion.enFavorito) {
      this.removeFavorito(cancion);
    }
    else {
      this.addFavorito(cancion);
    }

  }

  removeFavorito(cancion: Cancion) {
    const listFavId = JSON.parse(localStorage.getItem('UsuarioListaFavoritos')!).id;
    this.listaService
      .removeCancionFromListaCanciones(listFavId, cancion.id!)
      .subscribe(() => {
        this.favoritoService.eliminarCancion(cancion);
        cancion.enFavorito = false;

        if (this.inListaCreada && this.inFavoritos) {
          const idsCanciones = this.canciones.map((ca: Cancion) => ca.id);
          const indice = idsCanciones.indexOf(cancion.id);
          this.canciones.splice(indice, 1);
        }
      });
  }

  addFavorito(cancion: Cancion) {
    const listFavId = JSON.parse(localStorage.getItem('UsuarioListaFavoritos')!).id;
    this.listaService
      .addCancionIntoUsuarioCanciones(listFavId, cancion.id!)
      .subscribe(() => {
        this.favoritoService.agregarCancion(cancion);
        cancion.enFavorito = true;
      });
  }

  emitirAddCanciones() {
    this.addCanciones.emit();
  }

  quitarCancion(cancion: Cancion) {
    const listaId=+this.cookieService.get('ListaId');
    this.cancionService.removeListaFromCancionListas(cancion.id!,listaId).subscribe(()=>{
      const ids = this.canciones.map((o:Cancion) => o.id);
     const index = ids.indexOf(cancion.id);
      this.canciones.splice(index, 1);
    });;
  }

  asignarCancion(id:number){
    this.cookieService.set('CancionId',id+"");
  }
}
