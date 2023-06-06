import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Usuario } from 'src/app/model/data/Usuario';
import { UsuarioService } from 'src/app/services/data/usuario.service';
import { SesionService } from 'src/app/services/util/sesion.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
  registroForm: FormGroup;
  constructor(private usuarioService: UsuarioService, private sesionService: SesionService) {
    this.registroForm = new FormGroup({
      nombre: new FormControl('', [Validators.required,Validators.maxLength(50)]),
      nick: new FormControl('', [Validators.required,Validators.maxLength(50)]),
      email: new FormControl('', [Validators.required,Validators.email,Validators.maxLength(50)]),
      password: new FormControl('', [Validators.required,Validators.maxLength(50)])
    });
  }

  ngOnInit() {
  }
  onSubmit() {
    const sesionService=this.sesionService;
    let usuario: Usuario = {
      id: undefined,
      nombre: null,
      nick: null,
      email: null,
      password: null,
      rol: null,
      listasCreadas: null,
      listas: null,
      albums: null,
      artistas: null,
    };
    usuario.nombre = this.registroForm.controls['nombre'].value;
    usuario.nick = this.registroForm.controls['nick'].value;
    usuario.email = this.registroForm.controls['email'].value;
    usuario.password = this.registroForm.controls['password'].value;
    this.usuarioService.insertUsuario(usuario).subscribe(
      {
        next(userCreado) {
          usuario=userCreado;
          sesionService.guardarUsuario(usuario);
          sesionService.iniciarSesion();
        },
        error(err) {
          console.log(err);
        },
      }
    );
  }
}
