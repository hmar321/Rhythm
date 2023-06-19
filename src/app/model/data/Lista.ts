import { Cancion } from './Cancion';

export class Lista {
  id: number | null;
  portada: string | null;
  titulo: string | null;
  visitas: number | null;
  creadorNick: string | null;
  creadorId: number | null;
  canciones: Cancion[] | null;

  constructor() {
    this.id = 0;
    this.portada = null;
    this.titulo = null;
    this.visitas = null;
    this.creadorNick = null;
    this.creadorId = null;
    this.canciones = null;
  }
}

