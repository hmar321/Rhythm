import { Cancion } from './Cancion';

export interface Genero {
  id: number;
  titulo?: string;//nombre
  subTitulo?: string;//descripc
  visitas?: number;
  portada?: string;//new
  canciones?: Cancion[];
}
