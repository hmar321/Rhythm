import { Cancion } from './Cancion';

export interface Album {
  id: number;
  portada?: string;
  titulo?: string;
  estreno?: Date;
  visitas?: number;
  canciones?: Cancion[];
}
