import { Album } from './Album';

export class Artista {
  id: number | null;
  titulo: string | null;
  portada: string | null;
  visitas: number | null;
  albums: Album[] | null;
  constructor() {
    this.id = 0;
    this.titulo = null;
    this.portada = null;
    this.visitas = null;
    this.albums = null;
  }
}
