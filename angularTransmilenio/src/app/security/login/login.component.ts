import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDto } from '../../dto/login-dto';
import { AuthService } from '../../shared/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDto: LoginDto = new LoginDto("", "");
  loginError: string | null = null;

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.auth.logout(); // Cierra sesión al iniciar el componente
  }

  async login() {
    try {
      await this.auth.login(this.loginDto); // Espera a que login complete
      console.log("Login exitoso");

      // Obtén el rol del usuario desde el servicio AuthService
      const role = this.auth.role(); 

      // Redirige según el rol
      if (role === 'ADMIN') {
        this.router.navigate(['/ruta/list']);  // Redirige al panel de administración
      } else if (role === 'COORD') {
        this.router.navigate(['/conductor/conductor-list']);  // Redirige al listado de conductores
      } else if (role === 'USER') {
        this.router.navigate(['/ruta/list']);  // Redirige a la lista de rutas
      } else {
        this.router.navigate(['/login']);  // Redirige a login si no hay rol válido
      }
    } catch (err) {
      console.error("Error en el inicio de sesión:", err);
      this.loginError = "Credenciales incorrectas, por favor intenta nuevamente.";
    }
  }
}
