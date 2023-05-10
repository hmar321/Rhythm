import { Album } from './Album';
import { Artista } from './Artista';
import { Genero } from './Genero';
import { Lista } from './Lista';

export interface Cancion {
  id: number;
  titulo?: string;
  duracion?: Date;
  reproducciones?: number;
  albumes?: Album[];
  artistas: Artista[];
  generos?: Genero[];
  listas?: Lista[];
}
