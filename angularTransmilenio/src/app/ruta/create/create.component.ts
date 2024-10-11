import { Component } from '@angular/core';
import { RutaService } from '../../shared/ruta.service';
import { Router } from '@angular/router';
import { RutaDTO } from '../../dto/RutaDTO';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';  // Importar RouterModule

@Component({
  selector: 'app-Ruta-create',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, FormsModule],
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class RutaCreateComponent {
  newRuta: RutaDTO = {} as RutaDTO;
  errorMessage: string = '';

  constructor(private RutaService: RutaService, private router: Router) {}

  createRuta(): void {
    this.RutaService.crearRuta(this.newRuta).subscribe({
      next: (response) => {
        this.router.navigate(['/ruta/list']); // Redirigir a la lista de Rutaes
      },
      error: (error) => {
        console.error('Error al crear la Ruta', error);
        this.errorMessage = 'Error al crear la Ruta';
      },
      complete: () => {
        console.log('Ruta creado exitosamente');
      }
    });
  }

}

