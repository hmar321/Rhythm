import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css'],
})
export class BuscarComponent implements OnInit {
  textoBuscar$ = new Subject<string>();
  artisFil: any = [];
  albumFil: any = [];
  listaFil: any = [];

  artistas = [
    {
      titulo: 'Weeknd',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '/artista/1',
    },
    {
      titulo: 'Michael Jackson',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '/artista/2',
    },
  ];
  albumes = [
    {
      titulo: 'Dawn FM',
      subTitulo: 'Weeknd',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '',
    },
    {
      titulo: 'Thriller',
      subTitulo: 'Michael Jackson',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '',
    },
  ];
  listas = [
    {
      titulo: 'Core',
      subTitulo: '~Fate~',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '',
    },
    {
      titulo: 'Chill',
      subTitulo: 'XxdarkxX',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '',
    },
  ];
  constructor() {
    this.artisFil = this.artistas;
    this.albumFil = this.albumes;
    this.listaFil = this.listas;
  }

  ngOnInit() {
    this.filterList();
  }

  filterList(): void {
    this.textoBuscar$.subscribe((term) => {
      this.artisFil = this.artistas.filter(
        (item) => item.titulo.toLowerCase().indexOf(term.toLowerCase()) >= 0
      );
      this.albumFil = this.albumes.filter(
        (item) =>
          item.titulo.toLowerCase().indexOf(term.toLowerCase()) >= 0 ||
          item.subTitulo.toLowerCase().indexOf(term.toLowerCase()) >= 0
      );
      this.listaFil = this.listas.filter(
        (item) =>
          item.titulo.toLowerCase().indexOf(term.toLowerCase()) >= 0 ||
          item.subTitulo.toLowerCase().indexOf(term.toLowerCase()) >= 0
      );
    });
  }
}
