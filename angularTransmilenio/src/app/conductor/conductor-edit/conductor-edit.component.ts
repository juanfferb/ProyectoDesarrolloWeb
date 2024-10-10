import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';  // Importar ActivatedRoute
import { ConductorService } from '../../shared/conductor.service';
import { Router, RouterModule } from '@angular/router'; // Asegúrate de importar Router
import { ConductorDTO } from '../../dto/ConductorDTO';
import { catchError, Observable, of, tap } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-conductor-edit',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe, CommonModule, FormsModule],
  templateUrl: './conductor-edit.component.html',
  styleUrls: ['./conductor-edit.component.css']
})
export class ConductorEditComponent {
  conductor$!: Observable<ConductorDTO>;
  updatedConductor: ConductorDTO = {} as ConductorDTO;
  errorMessage: string = '';

  constructor(private conductorService: ConductorService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.conductor$ = this.conductorService.buscarConductoraPorId(id).pipe(
      catchError(error => {
        console.error("Error al buscar el conductor", error);
        this.errorMessage = "Error al buscar el conductor";
        return of({} as ConductorDTO); // Retorna un objeto vacío o maneja el error según prefieras
      })
    );

    // Suscribirse a conductor$ para asignar los valores a updatedConductor
    this.conductor$.subscribe(conductor => {
      if (conductor) {
        this.updatedConductor = conductor;
      }
    });
  }

  updateConductor(): void {
    this.conductorService.actualizarConductora(this.updatedConductor).subscribe({
      next: (response) => {
        this.router.navigate(['/conductor/conductor-list']); // Redirigir después de editar
      },
      error: (error) => {
        console.error('Error al actualizar el conductor', error);
        this.errorMessage = 'Error al actualizar el conductor';
      }
    });
  }
  
}
