import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { SesionService } from 'src/app/services/util/sesion.service';


@Injectable({
  providedIn: 'root'
})
export class LoginGuardian{
    constructor(private sesionService: SesionService, private router: Router,private messageService:MessageService) { }

    canActivate() {
        if(this.sesionService.isLoged()){
            return true;
        }
        else{
          this.messageService.add({
            severity: 'info',
            summary: 'Acceso denegado',
            detail: 'Necesitas iniciar sesión para acceder a esa página.',
          });
            this.router.navigate(['login']);
            return false;
        }
      }



}
