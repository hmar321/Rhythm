import { Album } from './Album';
import { Cancion } from './Cancion';
import { Usuario } from './Usuario';

export interface Artista {
  id: number;
  portada?: string; //foto
  titulo?: string; //nick
  nombre?: string;
  visitas: number;
  link: string; //new
  usuario?: Usuario;
  albumes?: Cancion[];
}
