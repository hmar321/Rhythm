import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css'],
})
export class ResultadosComponent implements OnInit {
  @Input() resultados: any;
  @Input() insertAddButton: any;

  constructor() {
    if (this.insertAddButton==null) {
      this.insertAddButton=false;
    }
  }

  ngOnInit() {

  }
}
