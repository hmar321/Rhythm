import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlbumService } from './../../../services/data/album.service';
import { CancionService } from './../../../services/data/cancion.service';
import { ListaService } from 'src/app/services/data/lista.service';
import { Lista } from 'src/app/model/data/Lista';
import { Cancion } from 'src/app/model/data/Cancion';
import { Observable, Subject, catchError, debounceTime, forkJoin, of } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { MessageService } from 'primeng/api';
import { DynamicDialogRef } from 'primeng/dynamicdialog';


@Component({
  selector: 'app-agregar-cancion',
  templateUrl: './agregar-cancion.component.html',
  styleUrls: ['./agregar-cancion.component.css']
})
export class AgregarCancionComponent implements OnInit {
  idLista: number = 0;
  textoBuscar = new Subject<string>();
  artistaBuscar: string = '';
  canciones: Cancion[] = [];
  selectedCanciones: Cancion[] = [];
  ultimoTermino: string = '';
  inconoLabel = 'pi-search';
  textoDropdown: string = 'Selecciona las canciones';
  constructor(private cancionService: CancionService, private cookieService: CookieService, private messageService: MessageService, private ref: DynamicDialogRef) {

  }
  ngOnInit() {
    this.idLista = +this.cookieService.get('ListaId');
    this.exitosCanciones();
    this.buscarCanciones();
  }
  onKeyUpEvent() {
    this.textoBuscar.next(this.artistaBuscar);
    this.inconoLabel = 'pi-spin pi-spinner';
  }
  buscarCanciones() {
    this.textoBuscar.pipe(debounceTime(700)).subscribe((term) => {
      if (term.length != 0) {
        if (term === this.ultimoTermino) {
          const resultadosCache = this.cookieService.get('cancionesCache');
          if (resultadosCache) {
            this.canciones = JSON.parse(resultadosCache);
            this.inconoLabel = 'pi-search';
          }
        } else {
          this.cancionService.buscarCancionesForLista(term, this.idLista).subscribe(canciones => {
            this.canciones = canciones;
            this.cookieService.set('cancionesCache', JSON.stringify(this.canciones));
            this.inconoLabel = 'pi-search';
            this.textoDropdown = 'Resultados busqueda';
          });
        }
        if (!term.includes(this.ultimoTermino)) {
          this.ultimoTermino = term;
        }
      } else {
        this.exitosCanciones();
        this.inconoLabel = 'pi-search';
        this.textoDropdown = 'Resultados busqueda';
      }
    });
  }
  enviarCanciones() {
    if (this.selectedCanciones != null) {
      const observables: Observable<boolean>[] = [];
      this.selectedCanciones.forEach(ca => {
        observables.push(this.cancionService.addListaIntoCancionListas(ca.id!, this.idLista));
      });
      forkJoin(observables)
        .pipe(
          catchError((error) => {
            return of(null);
          })
        )
        .subscribe({
          complete: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Éxito',
              detail: 'Se han añadido las canciones no repetidas.',
            });
            this.ref.close();
          },
        });
    }


  }

  exitosCanciones() {
    this.cancionService.cancionesExitos().subscribe((canciones) => (this.canciones = canciones));
  }


}
