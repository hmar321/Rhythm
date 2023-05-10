import { Component } from '@angular/core';
interface SideNavToggle {
  screenWith: number;
  collabsed: boolean;
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Rhythm';
  isSideNavCollabsed = false;
  screenWith = 0;

  constructor() {}

  onToggleSideNav(data: SideNavToggle): void {
    this.screenWith=data.screenWith;
    this.isSideNavCollabsed=data.collabsed;
  }
}
