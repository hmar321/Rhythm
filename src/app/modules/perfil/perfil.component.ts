import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  Validators
} from '@angular/forms';
import { MessageService } from 'primeng/api';
import { UsuarioService } from 'src/app/services/data/usuario.service';
import { Usuario } from './../../model/data/Usuario';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css'],
})
export class PerfilComponent implements OnInit {
  portada: string = 'assets/images/Perfil.png';
  nick: string = '';
  nombre: string = '';
  email: string = '';
  loginForm: FormGroup;
  passForm: FormGroup;
  sinEnviar: boolean = true;
  sinEnviarPass: boolean = true;
  passActual: string = '';
  newPass: string = '';
  repNewPass: string = '';

  constructor(
    private usuarioService: UsuarioService,
    private messageService: MessageService
  ) {
    this.loginForm = new FormGroup({
      nick: new FormControl('', Validators.required),
      nombre: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.email, Validators.required]),
    });

    this.passForm = new FormGroup({
      passActual: new FormControl('', Validators.required),
      newPass: new FormControl('', Validators.required),
      repNewPass: new FormControl('', Validators.required),
    });
  }

  ngOnInit() {
    this.nick = localStorage.getItem('UsuarioNick') || '';
    this.email = localStorage.getItem('UsuarioEmail') || '';
    this.nombre = localStorage.getItem('UsuarioNombre') || '';
  }
  onSubmit() {
    this.sinEnviar = false;
    const messageService = this.messageService;
    if (!this.loginForm.valid) {
      return;
    }
    const usuario: Usuario = {
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
    usuario.id = +localStorage.getItem("UsuarioId")!;
    usuario.nombre = this.nombre;
    usuario.nick = this.nick;
    usuario.email = this.email;
    usuario.password = localStorage.getItem("UsuarioPassword")!;
    this.usuarioService.updateUsuario(usuario).subscribe({
      next() {
        localStorage.setItem('UsuarioNombre', usuario.nombre!);
        localStorage.setItem('UsuarioEmail', usuario.email!);
        localStorage.setItem('UsuarioNick', usuario.nick!);
        messageService.add({
          severity: 'success',
          summary: 'Perfil actualizado',
          detail: 'Se han guardado correctamente los cambios.',
        });
      },
      error(err) {
        messageService.add({
          severity: 'warn',
          summary: 'Error',
          detail: 'No se han guardado los cambios.',
        });
      },
    });


  }
  cambiarPassword() {
    this.sinEnviarPass = false;
    const messageService = this.messageService;
    const usuario: Usuario = {
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
    usuario.id = +localStorage.getItem("UsuarioId")!;
    usuario.nombre = localStorage.getItem('UsuarioNombre');
    usuario.nick = localStorage.getItem('UsuarioNick');;
    usuario.email = localStorage.getItem('UsuarioEmail');
    usuario.password = this.newPass;
    const oldPass=localStorage.getItem("UsuarioPassword")!;
    if (this.passActual!=oldPass) {
      this.passForm.controls['passActual'].setErrors({error:true});
      messageService.add({
        severity: 'warn',
        summary: 'Error contraseña',
        detail: 'Contraseña incorrecta.',
      });
      return;
    }
    if (this.passActual == this.newPass) {
      this.passForm.controls['newPass'].setErrors({error:true});
      messageService.add({
        severity: 'warn',
        summary: 'Error nueva contraseña',
        detail: 'La contraseña nueva no puede ser la misma.',
      });
      return;
    }
    if (this.newPass != this.repNewPass) {
      this.passForm.controls['repNewPass'].setErrors({error:true});
      messageService.add({
        severity: 'warn',
        summary: 'Error',
        detail: 'Repetir contraseña nueva no es igual a nueva contraseña.',
      });
      return;
    }
    this.usuarioService.updateUsuario(usuario).subscribe({
      next() {
        localStorage.setItem('UsuarioPassword', usuario.password!);
        messageService.add({
          severity: 'success',
          summary: 'Perfil actualizado',
          detail: 'Se han cambiado correctamente la contraseña.',
        });
      },
      error(err) {
        messageService.add({
          severity: 'warn',
          summary: 'Error',
          detail: 'Error al cambiar de contraseña.',
        });
      },
    });
  }
}
