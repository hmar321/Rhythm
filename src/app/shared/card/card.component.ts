import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ListaService } from 'src/app/services/data/lista.service';
import { UsuarioService } from 'src/app/services/data/usuario.service';
import { SesionService } from 'src/app/services/util/sesion.service';
import { FavoritoService as FavoritoService } from '../../services/util/favorito.service';
import { ConfirmationService, MessageService, ConfirmEventType } from 'primeng/api';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent implements OnInit {
  @Input() card: any;
  @Input() ruta: any;
  portada: string = '';
  titulo: string = '';
  direccion: string = '';
  subTitulo: string = '';
  enFavoritos: string = '';
  mostrarAddFav: boolean = true;
  mostrarRemoveLista: boolean = false;
  @Output() elementoQuitado: EventEmitter<string> = new EventEmitter();

  constructor(
    private cookieService: CookieService,
    private sesionService: SesionService,
    private usuarioService: UsuarioService,
    private listaService: ListaService,
    private favoritoService: FavoritoService,
    private confirmationService:ConfirmationService,
    private messageService:MessageService
  ) { }
  ngOnInit() {
    if (!this.sesionService.isLoged()) {
      this.mostrarAddFav = false;
    }
    this.portada = this.card.portada;
    this.titulo = this.card.titulo;
    switch (this.ruta) {
      case 'lista':
        this.subTitulo = this.card.creadorNick;
        if (this.favoritoService.existeEnListaCreada(this.card)) {
          this.mostrarAddFav = false;
          this.mostrarRemoveLista = true;
        }
        if (this.favoritoService.existeEnLista(this.card)) {
          this.enFavoritos = 'favorito';
        }
        break;

      case 'genero':
        this.subTitulo = this.card.artistas;
        this.mostrarAddFav = false;
        break;

      case 'album':
        this.subTitulo = this.card.artistas;
        if (this.favoritoService.existeEnAlbum(this.card)) {
          this.enFavoritos = 'favorito';
        }
        break;

      case 'artista':
        this.subTitulo = this.card.artistas;
        if (this.favoritoService.existeEnArtista(this.card)) {
          this.enFavoritos = 'favorito';
        }
        break;
      case 'cancion':
        this.subTitulo = this.card.artistas;
        if (this.favoritoService.existeEnCanciones(this.card)) {
          this.enFavoritos = 'favorito';
        }
        break;
      default:
        break;
    }


    this.direccion = this.ruta;
  }

  abrirLista() {
    switch (this.ruta) {
      case 'lista':
        this.cookieService.set('ListaId', this.card.id + '');
        break;

      case 'genero':
        this.cookieService.set('GeneroId', this.card.id + '');
        break;

      case 'album':
        this.cookieService.set('AlbumId', this.card.id + '');
        break;

      case 'cancion':
        this.cookieService.set('CancionId', this.card.id + '');
        break;

      case 'artista':
        this.cookieService.set('ArtistaId', this.card.id + '');
        break;

      default:
        break;
    }
  }

  accionFavorito() {
    if (this.enFavoritos.length == 0) {
      this.addFavorito();
    } else {
      this.removeFavorito();
    }
  }

  addFavorito() {
    const usuarioId = +localStorage.getItem('UsuarioId')!;
    const listFavId = JSON.parse(
      localStorage.getItem('UsuarioListaFavoritos')!
    ).id;
    switch (this.ruta) {
      case 'lista':
        this.usuarioService
          .addListaIntoUsuarioListas(usuarioId, this.card.id)
          .subscribe(() => {
            this.favoritoService.agregarLista(this.card);
            this.enFavoritos = 'favorito';
          });
        break;

      case 'album':
        this.usuarioService
          .addAlbumIntoUsuarioAlbums(usuarioId, this.card.id)
          .subscribe(() => {
            this.favoritoService.agregarAlbum(this.card);
            this.enFavoritos = 'favorito';
          });
        break;

      case 'cancion':
        this.listaService
          .addCancionIntoUsuarioCanciones(listFavId, this.card.id)
          .subscribe(() => {
            this.favoritoService.agregarCancion(this.card);
            this.enFavoritos = 'favorito';
          });
        break;

      case 'artista':
        this.usuarioService
          .addArtistaIntoUsuarioArtistas(usuarioId, this.card.id)
          .subscribe(() => {
            this.favoritoService.agregarArtista(this.card);
            this.enFavoritos = 'favorito';
          });
        break;

      default:
        break;
    }
  }

  removeFavorito() {
    const usuarioId = +localStorage.getItem('UsuarioId')!;
    const listFavId = JSON.parse(
      localStorage.getItem('UsuarioListaFavoritos')!
    ).id;
    switch (this.ruta) {
      case 'lista':
        this.usuarioService
          .removeListaFromUsuarioListas(usuarioId, this.card.id)
          .subscribe(() => {
            this.favoritoService.eliminarLista(this.card);
            this.enFavoritos = '';
            this.elementoQuitado.emit();
          });
        break;

      case 'album':
        this.usuarioService
          .removeAlbumFromUsuarioAlbums(usuarioId, this.card.id)
          .subscribe(() => {
            this.favoritoService.eliminarAlbum(this.card);
            this.enFavoritos = '';
            this.elementoQuitado.emit();
          });
        break;

      case 'artista':
        this.usuarioService
          .removeArtistaFromUsuarioArtistas(usuarioId, this.card.id)
          .subscribe(() => {
            this.favoritoService.eliminarArtista(this.card);
            this.enFavoritos = '';
            this.elementoQuitado.emit();
          });
        break;

      case 'cancion':
        this.listaService
          .removeCancionFromListaCanciones(listFavId, this.card.id)
          .subscribe(() => {
            this.favoritoService.eliminarCancion(this.card);
            this.enFavoritos = '';
            this.elementoQuitado.emit();
          });
        break;

      default:
        break;
    }
  }

  borrarListaUsuario() {
    this.confirmationService.confirm({
      message: '¿Estás seguro de que deseas borrar la lista?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.messageService.add({ severity: 'info', summary: 'Aceptar', detail: 'Se ha borrado la lista.' });
          this.listaService.deleteLista(+this.card.id).subscribe(() => {
            this.favoritoService.eliminarListaCreada(this.card);
            this.elementoQuitado.emit();
          });
      },
      reject: (type:number) => {
          switch (type) {
              case ConfirmEventType.REJECT:
                  break;
              case ConfirmEventType.CANCEL:
                  break;
          }
      }
  });

  }

}
