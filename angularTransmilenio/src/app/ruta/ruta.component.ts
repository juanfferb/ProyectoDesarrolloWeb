import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';  // Importar RouterModule

@Component({
  selector: 'app-ruta',
  standalone: true,
  imports: [RouterModule],  // Agregar RouterModule aquí
  templateUrl: './ruta.component.html',
  styleUrls: ['./ruta.component.css']  // Cambiado a styleUrls
})
export class RutaComponent {

}
