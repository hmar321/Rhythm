import { Component, OnInit } from '@angular/core';
import {
  NavigationEnd,
  Router
} from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DynamicDialogRef, DialogService } from 'primeng/dynamicdialog';
import { Cancion } from 'src/app/model/data/Cancion';
import { Lista } from 'src/app/model/data/Lista';
import { ListaService } from 'src/app/services/data/lista.service';
import { AgregarCancionComponent } from './agregar-cancion/agregar-cancion.component';
import { FavoritoService } from 'src/app/services/util/favorito.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css'],
})
export class ListaComponent implements OnInit {
  tituloLista: string = '';
  usuarioNombre: string = '';
  visitas: number = 0;
  canciones: Cancion[] | undefined;
  portada: string = '';
  lista: Lista | undefined;
  id: number = -1;
  inFavoritos: boolean = false;
  inListasCreadas: boolean = false;
  ref: DynamicDialogRef | undefined;
  constructor(
    private router: Router,
    private listaService: ListaService,
    private cookieService: CookieService,
    private dialogService: DialogService,
    private favoritoService: FavoritoService
  ) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        if (event.url === '/favoritos') {
          this.inFavoritos = true;
          this.inListasCreadas = true;
        }
      }
    });
  }

  ngOnInit() {
    if (this.inFavoritos) {
      const cadena = localStorage.getItem('UsuarioListaFavoritos');
      if (cadena) {
        this.lista = JSON.parse(cadena);
        const auxLista = Object.assign({}, this.lista);
        auxLista.canciones = [];
        auxLista.visitas = (auxLista.visitas || 0) + 1;
        this.listaService.updateLista(auxLista).subscribe();
        this.asignarValores();
        this.cookieService.set('ListaId', this.lista!.id + "");
        this.id = this.lista!.id!;
      }
    } else {
      this.id = +this.cookieService.get('ListaId');
      this.buscarLista();
      const listasCreadas: Lista[] = JSON.parse(localStorage.getItem('UsuarioListasCreadas')!);
      const ids = listasCreadas.map(lista => lista.id);
      if (ids.indexOf(this.id) !== -1) {
        this.inListasCreadas = true;
      }
    }
  }

  buscarLista() {
    this.listaService.findListaById(this.id).subscribe((lista) => {
      this.lista = lista;
      if (this.inFavoritos) {
        localStorage.setItem('UsuarioListaFavoritos', JSON.stringify(this.lista));
        localStorage.setItem('UsuarioCanciones', JSON.stringify(this.lista.canciones));
      }
      if (this.lista!.canciones != null) {
        this.lista!.canciones.forEach((cancion: Cancion) => {
          cancion.enFavorito = this.favoritoService.existeEnCanciones(cancion);
        });
      }
      const auxLista = Object.assign({}, this.lista);
      auxLista.canciones = [];
      auxLista.visitas = (auxLista.visitas || 0) + 1;
      this.listaService.updateLista(auxLista).subscribe();
      this.asignarValores();
    });
  }


  asignarValores() {
    this.tituloLista = this.lista!.titulo!;
    this.usuarioNombre = this.lista!.creadorNick!;
    this.visitas = this.lista!.visitas!;
    this.canciones = this.lista!.canciones!;
    this.portada = 'assets/images/' + this.lista!.portada!;
  }

  addCancionesDialog() {
    this.ref = this.dialogService.open(AgregarCancionComponent, {
      header: 'Añadir cancion',
      height: '70%',
      contentStyle: { overflow: 'auto' },
      baseZIndex: 10000,
      maximizable: true
    });
    this.ref.onClose.subscribe(() => {
      this.buscarLista();
    });
  }
}
