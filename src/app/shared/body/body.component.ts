import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css'],
})
export class BodyComponent implements OnInit {
  @Input() collabsed = false;
  @Input() screenWith = 0;

  constructor() {}

  ngOnInit() {}

  getBodyClass(): string {
    let styleClass = '';
    if (this.collabsed && this.screenWith > 768) {
      styleClass = 'body-trimmed';
    } else if (
      this.collabsed &&
      this.screenWith <= 768 &&
      this.screenWith > 0
    ) {
      styleClass='body-md-screen'
    }
    return styleClass;
  }
}
