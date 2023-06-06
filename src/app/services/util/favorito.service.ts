import { Injectable, OnInit } from '@angular/core';
import { Album } from 'src/app/model/data/Album';
import { Artista } from 'src/app/model/data/Artista';
import { Cancion } from 'src/app/model/data/Cancion';
import { Lista } from 'src/app/model/data/Lista';

@Injectable({
  providedIn: 'root'
})
export class FavoritoService {
  private listasCreadas: Lista[] = [];
  listas: Lista[] = [];
  albums: Album[] = [];
  artistas: Artista[] = [];
  canciones: Cancion[] = [];
  constructor() {
    this.actualizarValores();
  }
  actualizarValores(): void {
    const idUsuario = localStorage.getItem('UsuarioId');
    if (idUsuario) {
      this.listasCreadas = JSON.parse(localStorage.getItem('UsuarioListasCreadas')!) || [];
      this.listas = JSON.parse(localStorage.getItem('UsuarioListas')!) || [];
      this.albums = JSON.parse(localStorage.getItem('UsuarioAlbums')!) || [];
      this.artistas = JSON.parse(localStorage.getItem('UsuarioArtistas')!) || [];
      this.canciones = JSON.parse(localStorage.getItem('UsuarioCanciones')!) || [];
    }
  }

  agregarCancion(cancion: any) {
    this.canciones.push(cancion);
    localStorage.setItem('UsuarioCanciones', JSON.stringify(this.canciones));
    const listaFav: Lista = JSON.parse(localStorage.getItem('UsuarioListaFavoritos')!);
    listaFav.canciones = this.canciones;
    localStorage.setItem('UsuarioListaFavoritos', JSON.stringify(listaFav));
    this.actualizarValores();
  }



  agregarListaCreada(lista: any) {
    this.listasCreadas.push(lista);
    localStorage.setItem('UsuarioListasCreadas', JSON.stringify(this.listasCreadas));
    this.actualizarValores();
  }



  agregarLista(lista: any) {
    this.listas.push(lista);
    localStorage.setItem('UsuarioListas', JSON.stringify(this.listas));
    this.actualizarValores();
  }



  agregarAlbum(album: any) {
    this.albums.push(album);
    localStorage.setItem('UsuarioAlbums', JSON.stringify(this.albums));
    this.actualizarValores();
  }



  agregarArtista(artist: any) {
    this.artistas.push(artist);
    localStorage.setItem('UsuarioArtistas', JSON.stringify(this.artistas));
    this.actualizarValores();
  }

  eliminarCancion(cancion: any) {
    const ids = this.canciones.map(o => o.id);
    const index = ids.indexOf(cancion.id);
    if (index !== -1) {
      this.canciones.splice(index, 1);
      localStorage.setItem('UsuarioCanciones', JSON.stringify(this.canciones));
      const listaFav: Lista = JSON.parse(localStorage.getItem('UsuarioListaFavoritos')!);
      listaFav.canciones = this.canciones;
      localStorage.setItem('UsuarioListaFavoritos', JSON.stringify(listaFav));
    }
    this.actualizarValores();
  }

  eliminarListaCreada(lista: any) {
    const ids = this.listasCreadas.map(o => o.id);
    const index = ids.indexOf(lista.id);
    if (index !== -1) {
      this.listasCreadas.splice(index, 1);
      localStorage.setItem('UsuarioListasCreadas', JSON.stringify(this.listasCreadas));
    }
    this.actualizarValores();
  }

  eliminarLista(lista: any) {
    const ids = this.listas.map(o => o.id);
    const index = ids.indexOf(lista.id);
    if (index !== -1) {
      this.listas.splice(index, 1);
      localStorage.setItem('UsuarioListas', JSON.stringify(this.listas));
    }
    this.actualizarValores();
  }

  eliminarAlbum(album: any) {
    const ids = this.albums.map(o => o.id);
    const index = ids.indexOf(album.id);
    if (index !== -1) {
      this.albums.splice(index, 1);
      localStorage.setItem('UsuarioAlbums', JSON.stringify(this.albums));
    }
    this.actualizarValores();
  }

  eliminarArtista(artist: any) {
    const ids = this.artistas.map(o => o.id);
    const index = ids.indexOf(artist.id);
    if (index !== -1) {
      this.artistas.splice(index, 1);
      localStorage.setItem('UsuarioArtistas', JSON.stringify(this.artistas));
    }
    this.actualizarValores();
  }

  existeEnListaCreada(elemento: any): boolean {
    this.actualizarValores();
    const ids = this.listasCreadas.map(o => o.id);
    return ids.indexOf(elemento.id) !== -1;
  }

  existeEnLista(elemento: any): boolean {
    this.actualizarValores();
    const ids = this.listas.map(o => o.id);
    return ids.indexOf(elemento.id) !== -1;
  }

  existeEnAlbum(elemento: any): boolean {
    this.actualizarValores();
    const ids = this.albums.map(o => o.id);
    return ids.indexOf(elemento.id) !== -1;
  }

  existeEnArtista(elemento: any): boolean {
    this.actualizarValores();
    const ids = this.artistas.map(o => o.id);
    return ids.indexOf(elemento.id) !== -1;
  }

  existeEnCanciones(elemento: any): boolean {
    this.actualizarValores();
    const ids = this.canciones.map(o => o.id);
    return ids.indexOf(elemento.id) !== -1;
  }
}

