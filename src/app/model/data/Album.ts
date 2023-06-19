import { Cancion } from './Cancion';

export class Album {
  id: number | null;
  portada: string | null;
  titulo: string | null;
  visitas: number | null;
  estreno: Date | null;
  artistasCadena: string | null;
  canciones: Cancion[] | null;
  constructor() {
    this.id = 0;
    this.portada = null;
    this.titulo = null;
    this.visitas = null;
    this.estreno = null;
    this.artistasCadena = null;
    this.canciones = null;
  }
}

