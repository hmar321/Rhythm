import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-biblioteca',
  templateUrl: './biblioteca.component.html',
  styleUrls: ['./biblioteca.component.css'],
})
export class BibliotecaComponent implements OnInit {
  items: MenuItem[];
  activeItem: MenuItem;
  artistas = [
    {
      titulo: 'Weeknd',
      subTitulo: '',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '/artista/1',
    },
    {
      titulo: 'Michael Jackson',
      subTitulo: '',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '/artista/2',
    },
    {
      titulo: 'Charlie Puth',
      subTitulo: '',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '/artista/3',
    }
  ];
  albumes = [
    {
      titulo: 'Dawn FM',
      subTitulo: 'Weeknd',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '1',
    },
    {
      titulo: 'Thriller',
      subTitulo: 'Michael Jackson',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '2',
    },
    {
      titulo: 'Voicenotes',
      subTitulo: 'Charlie Puth',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '3',
    }
  ];
  listas = [
    {
      titulo: 'Favoritos',
      subTitulo: 'cuenta123',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '/lista/1',
    },
    {
      titulo: 'Rock',
      subTitulo: 'cuenta123',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '/lista/2',
    },
    {
      titulo: 'Mi lista',
      subTitulo: 'cuenta123',
      portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
      link: '/lista/3',
    },
  ];
  @ViewChild('menuItems')
  menu: MenuItem[] = [];
  constructor() {
    this.items = [
      { label: 'Listas', icon: 'pi pi-save' },
      { label: 'Artistas', icon: 'pi pi-star' },
      { label: 'Álbumes', icon: 'pi pi-server' },
    ];

    this.activeItem = this.items[0];
  }
  ngOnInit() {}
  onActiveItemChange(event: MenuItem) {
    this.activeItem = event;
  }
}
