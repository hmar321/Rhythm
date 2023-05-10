import { Component, OnInit } from '@angular/core';
import { AlbumService } from 'src/app/services/data/album.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css'],
})
export class AlbumComponent implements OnInit {
  id: number = -1;
  album: any;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private albumService: AlbumService
  ) {
    const id = this.route.snapshot.paramMap.get('id');
    if (id !== null) {
      this.id = +id;
      this.albumService.findAlbumById(this.id);
    } else {
      router.navigate(['']);
    }
  }

  ngOnInit() {}
}
