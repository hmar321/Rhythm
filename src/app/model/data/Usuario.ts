import { Album } from './Album';
import { Artista } from './Artista';
import { Lista } from './Lista';
import { Rol } from './Rol';

export class Usuario {
  id: number | null;
  nombre: string | null;
  nick: string | null;
  email: string | null;
  password: string | null;
  rol: Rol | null;
  listasCreadas: Lista[] | null;
  listas: Lista[] | null;
  albums: Album[] | null;
  artistas: Artista[] | null;
  constructor() {
    this.id = 0;
    this.nombre = null;
    this.nick = null;
    this.email = null;
    this.password = null;
    this.rol = null;
    this.listasCreadas = null;
    this.listas = null;
    this.albums = null;
    this.artistas = null;
  }
}
