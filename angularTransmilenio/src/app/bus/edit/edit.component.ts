import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';  // Importar ActivatedRoute
import { BusService } from '../../shared/bus.service';
import { Router, RouterModule } from '@angular/router'; // Asegúrate de importar Router
import { BusDTO } from '../../dto/BusDTO';
import { catchError, Observable, of, tap } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit',
  standalone: true,
  imports: [RouterModule, AsyncPipe, NgFor, NgIf, FormsModule, CommonModule],
  templateUrl: './edit.component.html',
  styleUrls: [ './edit.component.css'] 
})
export class BusEditComponent {
  bus$!: Observable<BusDTO>;
  updatedBus: BusDTO = {} as BusDTO;
  errorMessage: string = '';

  constructor(private busService: BusService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.bus$ = this.busService.buscarBusPorId(id).pipe(
      catchError(error => {
        console.error("Error al buscar el bus", error);
        this.errorMessage = "Error al buscar el bus";
        return of({} as BusDTO); // Retorna un objeto vacío o maneja el error según prefieras
      })
    );

    // Suscribirse a conductor$ para asignar los valores a updatedConductor
    this.bus$.subscribe(bus => {
      if (bus) {
        this.updatedBus = bus;
      }
    });
  }

  updateBus(): void {
    this.busService.actualizarBus(this.updatedBus).subscribe({
      next: (response) => {
        this.router.navigate(['/bus/list']); // Redirigir después de editar
      },
      error: (error) => {
        console.error('Error al actualizar el bus', error);
        this.errorMessage = 'Error al actualizar el bus';
      }
    });
  }
}
