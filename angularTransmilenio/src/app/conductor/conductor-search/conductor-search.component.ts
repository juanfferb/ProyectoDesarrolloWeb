import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Importa RouterModule

@Component({
  selector: 'app-conductor-search',
  standalone: true,
  imports: [RouterModule],  // Asegúrate de importar RouterModule aquí
  templateUrl: './conductor-search.component.html',
  styleUrls: ['./conductor-search.component.css']
})
export class ConductorSearchComponent {

}
