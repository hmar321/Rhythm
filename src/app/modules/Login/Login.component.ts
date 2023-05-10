import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { SesionService } from './../../services/sesion.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-Login',
  templateUrl: './Login.component.html',
  styleUrls: ['./Login.component.css'],
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';

  constructor(private messageService: MessageService,private router: Router, private cookieService: CookieService,private sesionService:SesionService) {
    this.loginForm = new FormGroup({
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
  }

  loginForm: FormGroup;

  submitted = false;

  ngOnInit() {}

  onSubmit() {
    this.submitted = true;
    this.sesionService.loguear(this.loginForm.controls['email'].value,this.loginForm.controls['password'].value);
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Se ha iniciado sesión correctamente.' });
}
}
