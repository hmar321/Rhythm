import { Cancion } from './Cancion';
import { Usuario } from './Usuario';

export interface Lista {
  id: number;
  portada?: string;
  titulo?: string;
  visitas?: number;
  subTitulo?: string;//new usuario
  link?: string; //new
  usuarios?: Usuario[];
  canciones?: Cancion[];
}
