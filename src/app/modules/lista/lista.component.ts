import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cancion } from 'src/app/model/data/Cancion';
import { Lista } from 'src/app/model/data/Lista';
import { Usuario } from 'src/app/model/data/Usuario';
import { ListaService } from 'src/app/services/data/lista.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css'],
})
export class ListaComponent implements OnInit {
  id: number = -1;
  lista: any = {
    id: 1,
    portada: 'https://primefaces.org/cdn/primeng/images/usercard.png',
    titulo: 'Mi Lista',
    visitas: 1,
    usuarios: [
      {
        id: 1,
        nombre: 'pepe',
      },
      {
        id: 2,
        nombre: 'luis',
      },
    ],
    canciones: [
      {
        id: 1,
        titulo: 'asd',
        duracion: new Date(),
        reproducciones: 3,
        artistas: [{ id: 1, nombre: 'artista 1' },{ id: 3, nombre: 'artista 3' }],
      },
      {
        id: 2,
        titulo: 'xcv',
        duracion: new Date().setSeconds(new Date().getSeconds() + 57),
        reproducciones: 30,
        artistas: [{ id: 3, nombre: 'artista 3' }],
      },
      {
        id: 3,
        titulo: 'bnm',
        duracion: new Date().setSeconds(new Date().getSeconds() + 31),
        reproducciones: 300,
        artistas: [{ id: 2, nombre: 'artista 2' }],
      },
      {
        id: 1,
        titulo: 'asd',
        duracion: new Date(),
        reproducciones: 3,
        artistas: [{ id: 1, nombre: 'artista 1' },{ id: 3, nombre: 'artista 3' }],
      },
      {
        id: 2,
        titulo: 'xcv',
        duracion: new Date().setSeconds(new Date().getSeconds() + 57),
        reproducciones: 30,
        artistas: [{ id: 3, nombre: 'artista 3' }],
      },
      {
        id: 3,
        titulo: 'bnm',
        duracion: new Date().setSeconds(new Date().getSeconds() + 31),
        reproducciones: 300,
        artistas: [{ id: 2, nombre: 'artista 2' }],
      },
      {
        id: 1,
        titulo: 'asd',
        duracion: new Date(),
        reproducciones: 3,
        artistas: [{ id: 1, nombre: 'artista 1' },{ id: 3, nombre: 'artista 3' }],
      },
      {
        id: 2,
        titulo: 'xcv',
        duracion: new Date().setSeconds(new Date().getSeconds() + 57),
        reproducciones: 30,
        artistas: [{ id: 3, nombre: 'artista 3' }],
      },
      {
        id: 3,
        titulo: 'bnm',
        duracion: new Date().setSeconds(new Date().getSeconds() + 31),
        reproducciones: 300,
        artistas: [{ id: 2, nombre: 'artista 2' }],
      },
      {
        id: 4,
        titulo: 'qwe',
        duracion: new Date().setSeconds(new Date().getSeconds() + 6),
        reproducciones: 1,
        artistas: [{ id: 1, nombre: 'artista 1' }],
      },
    ],
  };
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private listaService: ListaService
  ) {
    const id = this.route.snapshot.paramMap.get('id');
    if (id !== null) {
      this.id = +id;
      // this.listaService.findListaById(this.id);
    } else {
      router.navigate(['']);
    }
  }

  ngOnInit() {
  }
}
