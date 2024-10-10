import { Component } from '@angular/core';
import { BusService } from '../../shared/bus.service';
import { Router } from '@angular/router';
import { BusDTO } from '../../dto/BusDTO';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';  // Importar RouterModule

@Component({
  selector: 'app-Bus-create',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, FormsModule],
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class BusCreateComponent {
  newBus: BusDTO = {} as BusDTO;
  errorMessage: string = '';

  constructor(private BusService: BusService, private router: Router) {}

  createBus(): void {
    this.BusService.crearBus(this.newBus).subscribe({
      next: (response) => {
        this.router.navigate(['/bus/list']); // Redirigir a la lista de Buses
      },
      error: (error) => {
        console.error('Error al crear el Bus', error);
        this.errorMessage = 'Error al crear el Bus';
      },
      complete: () => {
        console.log('Bus creado exitosamente');
      }
    });
  }

}

