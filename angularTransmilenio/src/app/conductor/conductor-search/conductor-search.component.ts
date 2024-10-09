import { Component } from '@angular/core';
import { ReactiveFormsModule, FormControl } from '@angular/forms'; // Asegúrate de incluir ReactiveFormsModule
import { ConductorService } from '../../shared/conductor.service';
import { RouterModule } from '@angular/router';
import { ConductorDTO } from '../../dto/ConductorDTO';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-conductor-search',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe, CommonModule, ReactiveFormsModule], // Aquí se incluye ReactiveFormsModule
  templateUrl: './conductor-search.component.html',
  styleUrls: ['./conductor-search.component.css']
})
export class ConductorSearchComponent {
  conductores$: Observable<ConductorDTO[]> = of([]);
  searchControl: FormControl = new FormControl();
  errorMessage: string = '';

  constructor(private conductorService: ConductorService) {}

  ngOnInit(): void {
    this.searchControl.valueChanges.subscribe((nombre: string) => {
      if (nombre) {
        this.conductores$ = this.conductorService.buscarConductoresPorNombre(nombre).pipe(
          catchError(error => {
            console.error("Error al buscar conductores", error);
            this.errorMessage = "Error al buscar conductores";
            return of([]); // Retorna un array vacío en caso de error
          })
        );
      } else {
        this.conductores$ = of([]); // Si no hay texto, retorna un array vacío
      }
    });
  }

  searchConductors(): void {
    const nombre = this.searchControl.value;
    if (nombre) {
      this.conductores$ = this.conductorService.buscarConductoresPorNombre(nombre).pipe(
        catchError(error => {
          console.error("Error al buscar conductores", error);
          this.errorMessage = "Error al buscar conductores";
          return of([]); // Retorna un array vacío en caso de error
        })
      );
    }
  }
}
