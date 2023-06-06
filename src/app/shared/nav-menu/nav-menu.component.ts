import {
  Component,
  EventEmitter,
  HostListener,
  OnInit,
  Output,
} from '@angular/core';
import { navLogin, navLogout, navbarData } from './nav-data';


import { MessageService } from 'primeng/api';
import { SesionService } from 'src/app/services/util/sesion.service';

interface SideNavToggle {
  screenWith: number;
  collabsed: boolean;
}

@Component({
  selector: 'app-nav-menu',
  templateUrl: './nav-menu.component.html',
  styleUrls: ['./nav-menu.component.css'],
})
export class NavMenuComponent implements OnInit {
  collapsed: boolean = false;
  screenWith: number = 0;
  navData = navbarData;
  navLoginData = navLogin;
  navLogoutData = navLogout;

  constructor(
    public sesionService: SesionService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.screenWith = window.innerWidth;
  }
  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.screenWith = window.innerWidth;
    if (this.screenWith <= 768) {
      this.closeSidenav();
    }
  }

  toggleCollapse(): void {
    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit({
      collabsed: this.collapsed,
      screenWith: this.screenWith,
    });
  }

  closeSidenav(): void {
    this.collapsed = false;
    this.onToggleSideNav.emit({
      collabsed: this.collapsed,
      screenWith: this.screenWith,
    });
  }

  cerrarSesion() {
    this.sesionService.cerrarSesion();
  }
  showInfo() {
    this.messageService.add({
      severity: 'info',
      summary: 'Información',
      detail: 'Se ha cerrado sesión.',
    });
  }
}
