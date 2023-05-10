import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { GeneroService } from 'src/app/services/data/genero.service';

import { SesionService } from 'src/app/services/sesion.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  generos: any = [];
  listas: any = [];
  constructor(
    public sesionService: SesionService,
    private coockieService: CookieService,
    private generoService: GeneroService
  ) {}

  ngOnInit() {
    this.generoService.findAllGeneros().subscribe(generos => {
      this.generos = generos;

    });
  }
}
