import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-canciones-tabla',
  templateUrl: './canciones-tabla.component.html',
  styleUrls: ['./canciones-tabla.component.css'],
})
export class CancionesTablaComponent implements OnInit {
  @Input() canciones: any;

  constructor() {}

  ngOnInit() {}
}
