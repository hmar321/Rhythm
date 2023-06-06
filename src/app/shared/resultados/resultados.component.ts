import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css'],
})

export class ResultadosComponent implements OnInit {
  @Input() resultados: any;
  @Input() insertAddButton: any;
  @Input() ruta: any;
  @Output() boton: EventEmitter<boolean> = new EventEmitter();
  @Output() listaBorrada: EventEmitter<string> = new EventEmitter();
  constructor() {
    if (this.insertAddButton==null) {
      this.insertAddButton=false;
    }

  }

  ngOnInit() {

  }

  botonPresionado(){
    this.boton.emit(true);
  }

  eventoListaBorrada(){
    this.listaBorrada.emit();
  }
}


