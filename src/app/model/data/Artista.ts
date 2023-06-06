import { Album } from './Album';

export interface Artista {
  id?: number;
  titulo?: string|null;
  portada?: string|null;
  visitas: number|null;
  albums?: Album[]|null;
}
