import { Cancion } from './Cancion';

export interface Lista {
  id?: number;
  portada?: string|null;
  titulo?: string|null;
  visitas?: number|null;
  creadorNick?:string|null;
  creadorId?:number|null;
  canciones?: Cancion[]|null;
}

