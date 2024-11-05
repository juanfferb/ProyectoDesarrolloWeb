
import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { MainComponent } from './main/main.component';
import { ConductorListComponent } from "./conductor/conductor-list/conductor-list.component";
import { AuthService } from './shared/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, MainComponent, ConductorListComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']  // Cambiado a styleUrls
})
export class AppComponent {
  constructor(private auth: AuthService, private router: Router) { }

  logout() {
    this.auth.logout();
    this.router.navigate(["/login"]);
  }

  showLogoutButton() {
    return this.auth.isAuthenticated();
  }
}
