
import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { MainComponent } from './main/main.component';
import { ConductorListComponent } from "./conductor/conductor-list/conductor-list.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, MainComponent, ConductorListComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']  // Cambiado a styleUrls
})
export class AppComponent {
  title = 'angularTransmilenio';
}
