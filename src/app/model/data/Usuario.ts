import { Lista } from './Lista';
import { Rol } from './Rol';

export interface Usuario {
  id: number;
  nombre?: string;
  nick?: string;
  email?: string;
  password?: string;
  pais?: string;
  fechNac?: Date;
  rol?: Rol;
  albumes?: Lista[];
}
