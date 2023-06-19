import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SesionService } from 'src/app/services/util/sesion.service';

@Component({
  selector: 'app-Login',
  templateUrl: './Login.component.html',
  styleUrls: ['./Login.component.css'],
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';
  loginForm: FormGroup;

  constructor(private sesionService: SesionService) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(50)]),
      password: new FormControl('', [Validators.required, Validators.maxLength(50)])
    });
  }

  ngOnInit() { }

  onSubmit() {
    this.sesionService.loguear(this.loginForm.controls['email'].value, this.loginForm.controls['password'].value);
  }

}
