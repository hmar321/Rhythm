import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-banner-listas',
  templateUrl: './banner-listas.component.html',
  styleUrls: ['./banner-listas.component.css'],
})
export class BannerListasComponent implements OnInit {
  @Input() titulo: any;
  @Input() portada: any;
  @Input() nombre: any;
  @Input() visitas: any;
  @Input() estreno: any;
  constructor() {}

  ngOnInit() {}
}
