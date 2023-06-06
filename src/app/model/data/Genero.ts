import { Cancion } from './Cancion';

export interface Genero {
  id?: number;
  titulo?: string|null;
  visitas?: number|null;
  portada?: string|null;
  canciones?: Cancion[]|null;
}
