import { Usuario } from './Usuario';

export interface Rol {
  id?: number;
  nombre?: string|null;
  descripcion?: string|null;
  usuarios?: Usuario[]|null;
}
