import { Artista } from './Artista';

export interface Cancion {
  id?: number;
  titulo?: string|null;
  portada?:string|null;
  visitas?: number|null;
  duracion?: Date|null;
  estreno?:Date|null;
  artistas?: string|null;
  lyrics?:string|null;
  enFavorito:boolean|null;
}

