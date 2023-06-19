
export class Cancion {
  id: number | null;
  titulo: string | null;
  portada: string | null;
  visitas: number | null;
  duracion: Date | null;
  estreno: Date | null;
  artistasCadena: string | null;
  lyrics: string | null;
  enFavorito: boolean | null;
  constructor() {
    this.id = 0;
    this.titulo = null;
    this.portada = null;
    this.visitas = null;
    this.duracion = null;
    this.estreno = null;
    this.artistasCadena = null;
    this.lyrics = null;
    this.enFavorito = null;
  }
}

