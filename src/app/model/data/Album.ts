import { Artista } from './Artista';
import { Cancion } from './Cancion';

export interface Album {
  id?: number;
  portada?: string|null;
  titulo?: string|null;
  visitas?: number|null;
  estreno?: Date|null;
  artistas?: string|null;
  canciones?: Cancion[]|null;
}

