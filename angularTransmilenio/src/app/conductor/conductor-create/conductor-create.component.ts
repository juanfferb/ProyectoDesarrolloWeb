import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Importa RouterModule

@Component({
  selector: 'app-conductor-create',
  standalone: true,
  imports: [RouterModule],  // Asegúrate de importar RouterModule aquí
  templateUrl: './conductor-create.component.html',
  styleUrls: ['./conductor-create.component.css']
})
export class ConductorCreateComponent {

}
