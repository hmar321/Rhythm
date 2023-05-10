import { Usuario } from './Usuario';

export interface Rol {
  id: number;
  nombre?: string;
  descripcion?: string;
  usuarios?: Usuario[];
}
