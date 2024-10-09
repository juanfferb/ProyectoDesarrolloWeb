import { Component } from '@angular/core';
import { ConductorService } from '../../shared/conductor.service';
import { Router, RouterModule } from '@angular/router';  // Asegúrate de importar Router
import { ConductorDTO } from '../../dto/ConductorDTO';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-conductor-create',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe, CommonModule, FormsModule],
  templateUrl: './conductor-create.component.html',
  styleUrls: ['./conductor-create.component.css']
})
export class ConductorCreateComponent {
  newConductor: ConductorDTO = {} as ConductorDTO;
  errorMessage: string = '';

  constructor(private conductorService: ConductorService, private router: Router) {}

  createConductor(): void {
    this.conductorService.crearConductora(this.newConductor).subscribe(
      response => {
        this.router.navigate(['/conductores']); // Redirigir después de crear
      },
      error => {
        console.error('Error al crear el conductor', error);
        this.errorMessage = 'Error al crear el conductor';
      }
    );
  }
}
