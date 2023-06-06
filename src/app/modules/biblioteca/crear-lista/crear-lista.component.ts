import { Component, OnInit } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Lista } from 'src/app/model/data/Lista';
import { ListaService } from 'src/app/services/data/lista.service';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { FavoritoService } from 'src/app/services/util/favorito.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-crear-lista',
  templateUrl: './crear-lista.component.html',
  styleUrls: ['./crear-lista.component.css'],
})
export class CrearListaComponent implements OnInit {
  tituloLista: string = '';
  lista: Lista = {};

  constructor(private listaService: ListaService, private ref: DynamicDialogRef, private favoritoService: FavoritoService, private messageService: MessageService) { }

  ngOnInit() { }

  crearLista() {
    if (this.tituloLista.length == 0) {
      this.messageService.add({
        severity: 'warn',
        summary: 'Error',
        detail: 'El título está vacío.',
      });
      return;
    }
    this.lista.titulo = this.tituloLista;
    this.lista.portada = 'Lista.png';
    this.lista.visitas = 0;
    this.lista.creadorNick = localStorage.getItem('UsuarioNick')!;
    this.lista.creadorId = +localStorage.getItem('UsuarioId')!;
    lastValueFrom(this.listaService.insertLista(this.lista))
      .then((lista) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Éxito',
          detail: 'Se ha creado la lista correctamente.',
        });
        this.favoritoService.agregarListaCreada(lista);
        this.ref.close();
      })
      .catch((error) => {
        this.messageService.add({
          severity: 'warn',
          summary: 'Error',
          detail: 'No se ha creado la lista.',
        });
      });
  }

}
