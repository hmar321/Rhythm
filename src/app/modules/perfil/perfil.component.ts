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
  userForm: FormGroup;
  passForm: FormGroup;
  passActual: string = '';
  newPass: string = '';
  repNewPass: string = '';
  sinEnviar: boolean = true;
  sinEnviarPass: boolean = true;
  // se inicializan ambos formularios con sus valores
  constructor(
    private usuarioService: UsuarioService,
    private messageService: MessageService
  ) {
    this.userForm = new FormGroup({
      nick: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      nombre: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      email: new FormControl('', [Validators.email, Validators.required, Validators.maxLength(50)]),
    });
    this.passForm = new FormGroup({
      passActual: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      newPass: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      repNewPass: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    });
  }

  ngOnInit() {
    // relleno los valores con los datos de localStorage
    this.nick = localStorage.getItem('UsuarioNick') || '';
    this.email = localStorage.getItem('UsuarioEmail') || '';
    this.nombre = localStorage.getItem('UsuarioNombre') || '';
  }
  // método para actualizar los datos del usuario
  onSubmit() {
    this.sinEnviar = false;
    const usuario: Usuario = new Usuario();
    usuario.id = +localStorage.getItem("UsuarioId")!;
    usuario.nombre = this.nombre;
    usuario.nick = this.nick;
    usuario.email = this.email;
    usuario.password = localStorage.getItem("UsuarioPassword")!;
    // se envian los cambios a la api
    this.usuarioService.updateUsuario(usuario).subscribe({
      next: () => {
        localStorage.setItem('UsuarioNombre', usuario.nombre!);
        localStorage.setItem('UsuarioEmail', usuario.email!);
        localStorage.setItem('UsuarioNick', usuario.nick!);
        this.messageService.add({
          severity: 'success',
          summary: 'Perfil actualizado',
          detail: 'Se han guardado correctamente los cambios.',
        });
      },
      error: (err) => {
        this.messageService.add({
          severity: 'warn',
          summary: 'Error',
          detail: 'No se han guardado los cambios.',
        });
      },
    });
  }
  // método para actualizar la contraseña del usuario
  cambiarPassword() {
    this.sinEnviarPass = false;
    const usuario: Usuario = new Usuario();
    usuario.id = +localStorage.getItem("UsuarioId")!;
    usuario.nombre = localStorage.getItem('UsuarioNombre');
    usuario.nick = localStorage.getItem('UsuarioNick');;
    usuario.email = localStorage.getItem('UsuarioEmail');
    usuario.password = this.newPass;
    const oldPass = localStorage.getItem("UsuarioPassword")!;
    // si la contraseña no es la actual
    if (this.passActual != oldPass) {
      this.passForm.controls['passActual'].setErrors({ error: true });
      this.messageService.add({ severity: 'warn', summary: 'Error contraseña', detail: 'Contraseña incorrecta.' });
      return;
    }
    // si la contraseña nueva es igual a la actual
    if (this.passActual == this.newPass) {
      this.passForm.controls['newPass'].setErrors({ error: true });
      this.messageService.add({ severity: 'warn', summary: 'Error nueva contraseña', detail: 'La contraseña nueva no puede ser la misma.' });
      return;
    }
    // si la contrasela no se repite correctamente
    if (this.newPass != this.repNewPass) {
      this.passForm.controls['repNewPass'].setErrors({ error: true });
      this.messageService.add({ severity: 'warn', summary: 'Error', detail: 'Repetir contraseña nueva no es igual a nueva contraseña.' });
      return;
    }
    // se actualiza el usuario
    this.usuarioService.updateUsuario(usuario).subscribe({
      next: () => {
        localStorage.setItem('UsuarioPassword', usuario.password!);
        this.messageService.add({ severity: 'success', summary: 'Perfil actualizado', detail: 'Se han cambiado correctamente la contraseña.' });
      },
      error: (err) => {
        this.messageService.add({ severity: 'warn', summary: 'Error', detail: 'Error al cambiar de contraseña.' });
      },
    });
  }
}
