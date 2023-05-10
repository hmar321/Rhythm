import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css'],
})
export class BannerComponent implements OnInit {
  @Output() buscarEvent = new EventEmitter<string>();
  busqueda:string='';
  textoBuscar$ = new Subject<string>();
  constructor() {
  }

  ngOnInit() {}
  emitir(busqueda: string) {
    this.buscarEvent.emit(busqueda);
  }

}
