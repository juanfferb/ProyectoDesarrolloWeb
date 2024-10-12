import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AsignacionService } from '../../shared/asignacion.service';
import { RouterModule } from '@angular/router';
import { AsignacionDTO } from '../../dto/AsignacionDTO';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-asignacion-view',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe, CommonModule, ReactiveFormsModule],
  templateUrl: './asignacion-view.component.html',
  styleUrls: ['./asignacion-view.component.css']
})
export class AsignacionViewComponent implements OnInit {
  asignaciones$: Observable<AsignacionDTO[]> = of([]);
  errorMessage: string = '';
  conductorId?: number; // Agrega esta variable

  constructor(private asignacionService: AsignacionService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.conductorId = Number(this.route.snapshot.paramMap.get('id')); // Guarda el ID
    this.asignaciones$ = this.asignacionService.buscarAsignacionesByConductorId(this.conductorId).pipe(
      catchError(error => {
        console.error("Error al buscar las asignaciones", error);
        this.errorMessage = "Error al buscar las asignaciones";
        return of([]);
      })
    );
  }

  eliminarAsignacion(id: number): void {
    if (confirm('¿Estás seguro de que quieres eliminar esta asignación?')) {
      this.asignacionService.eliminarAsignacion(id).subscribe(() => {
        this.ngOnInit(); // Recarga las asignaciones después de eliminar
      });
    }
  }
}
