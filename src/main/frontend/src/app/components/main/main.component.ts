import { Component } from '@angular/core';
import { MatSidenav, MatSidenavContainer, MatSidenavContent } from "@angular/material/sidenav";
import { RouterLink, RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-main',
  imports: [
    MatSidenav,
    MatSidenavContainer,
    RouterLink,
    RouterOutlet,
    MatSidenavContent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css',
})
export class MainComponent {

}
