import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Album } from 'src/app/model/data/Album';
import { Artista } from 'src/app/model/data/Artista';
import { Lista } from 'src/app/model/data/Lista';
import { FavoritoService } from 'src/app/services/util/favorito.service';
import { SesionService } from 'src/app/services/util/sesion.service';
import { CrearListaComponent } from './crear-lista/crear-lista.component';

@Component({
  selector: 'app-biblioteca',
  templateUrl: './biblioteca.component.html',
  styleUrls: ['./biblioteca.component.css'],
})
export class BibliotecaComponent implements OnInit {
  items: MenuItem[];
  @ViewChild('menuItems')
  menu: MenuItem[] = [];
  activeItem: MenuItem;
  artistas: Artista[] | undefined;
  albums: Album[] | undefined;
  listasOtros: Lista[] | undefined;
  listasCreadas: Lista[] | undefined;
  listas: Lista[] | undefined;
  ref: DynamicDialogRef|undefined;
  constructor(
    public dialogService: DialogService,
    private router: Router,
    public sesionService: SesionService
  ) {
    // inizializo el menu de pestañas
    this.items = [
      { label: 'Listas', icon: 'pi pi-save' },
      { label: 'Artistas', icon: 'pi pi-star' },
      { label: 'Álbums', icon: 'pi pi-server' },
    ];
    this.activeItem = this.items[0];
    this.actualizarBiblioteca();
  }
  ngOnInit() {
  }
  // cuando se cambia de pestaña se actualiza el item activo
  onActiveItemChange(event: MenuItem) {
    this.activeItem = event;
  }
  // metodo para realizar añadir elementos dependiendo de la pestaña
  botonAddPresionado(boton: boolean) {
    if (boton && this.activeItem == this.items[0]) {
      this.crearLista();
    } else if (boton && this.activeItem == this.items[1]) {
      this.router.navigate(['/buscar']);
    } else if (boton && this.activeItem == this.items[2]) {
      this.router.navigate(['/buscar']);
    }
  }
  // actualizo los valores con los datos de localStorage
  actualizarBiblioteca(){
    let cadena = localStorage.getItem('UsuarioArtistas');
    if (cadena) {
      this.artistas = JSON.parse(cadena);
    }
    cadena = localStorage.getItem('UsuarioAlbums');
    if (cadena) {
      this.albums = JSON.parse(cadena);
    }
    cadena = localStorage.getItem('UsuarioListasCreadas');
    if (cadena) {
      this.listasCreadas = JSON.parse(cadena);
      this.listasCreadas!.splice(0, 1);
    }
    cadena = localStorage.getItem('UsuarioListas');
    if (cadena) {
      this.listasOtros = JSON.parse(cadena);
    }
    if (this.listasOtros?.length != 0) {
      this.listas=this.listasCreadas?.concat(this.listasOtros!);
    }else{
      this.listas=this.listasCreadas;
    }
  }
  // creo un dialogo para crear una nueva lista
  crearLista() {
    this.ref = this.dialogService.open(CrearListaComponent, {
      header: 'Título lista',
    });
    this.ref.onClose.subscribe(()=>{
      this.actualizarBiblioteca();
    });
  }

}
