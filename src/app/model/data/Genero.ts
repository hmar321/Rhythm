import { Cancion } from './Cancion';

export class Genero {
  id: number | null;
  titulo: string | null;
  visitas: number | null;
  portada: string | null;
  canciones: Cancion[] | null;
  constructor() {
    this.id = 0;
    this.titulo = null;
    this.visitas = null;
    this.portada = null;
    this.canciones = null;
  }
}
