import { Album } from './Album';
import { Artista } from './Artista';
import { Lista } from './Lista';
import { Rol } from './Rol';

export interface Usuario {
  id?: number;
  nombre?: string|null;
  nick?: string|null;
  email?: string|null;
  password?: string|null;
  rol?: Rol|null;
  listasCreadas?: Lista[]|null;
  listas?: Lista[]|null;
  albums?: Album[]|null;
  artistas?: Artista[]|null;
}
