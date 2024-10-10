import { Component } from '@angular/core';
import { ConductorService } from '../../shared/conductor.service';
import { Router } from '@angular/router';
import { ConductorDTO } from '../../dto/ConductorDTO';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';  // Importar RouterModule

@Component({
  selector: 'app-conductor-create',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, FormsModule],
  templateUrl: './conductor-create.component.html',
  styleUrls: ['./conductor-create.component.css']
})
export class ConductorCreateComponent {
  newConductor: ConductorDTO = {} as ConductorDTO;
  errorMessage: string = '';

  constructor(private conductorService: ConductorService, private router: Router) {}

  createConductor(): void {
    this.conductorService.crearConductora(this.newConductor).subscribe({
      next: (response) => {
        this.router.navigate(['/conductor/conductor-list']); // Redirigir a la lista de conductores
      },
      error: (error) => {
        console.error('Error al crear el conductor', error);
        this.errorMessage = 'Error al crear el conductor';
      },
      complete: () => {
        console.log('Conductor creado exitosamente');
      }
    });
  }

}

