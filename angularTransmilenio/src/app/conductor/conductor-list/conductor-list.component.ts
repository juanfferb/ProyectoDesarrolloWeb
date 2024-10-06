import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';  // Importar RouterModule

@Component({
  selector: 'app-conductor-list',
  standalone: true,
  imports: [RouterModule],  // Agregar RouterModule aquí
  templateUrl: './conductor-list.component.html',
  styleUrls: ['./conductor-list.component.css']  // Cambiado a styleUrls
})
export class ConductorListComponent {

}
