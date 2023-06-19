import { Usuario } from './Usuario';

export class Rol {
  id: number | null;
  nombre: string | null;
  descripcion: string | null;

  constructor() {
    this.id = 0;
    this.nombre = null;
    this.descripcion = null;
  }

}
