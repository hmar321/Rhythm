import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArtistaService } from 'src/app/services/data/artista.service';

@Component({
  selector: 'app-artista',
  templateUrl: './artista.component.html',
  styleUrls: ['./artista.component.css']
})
export class ArtistaComponent implements OnInit {
  id:number=-1;
  artista:any;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private artistaService: ArtistaService
  ) {
    const id = this.route.snapshot.paramMap.get('id');
    if (id !== null) {
      this.id = +id;
      this.artista=this.artistaService.findArtistaById(this.id);
    } else {
      router.navigate(['']);
    }
  }

  ngOnInit() {
  }

}
