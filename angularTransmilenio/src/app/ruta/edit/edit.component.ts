import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';  // Importar ActivatedRoute
import { RutaService } from '../../shared/ruta.service';
import { Router, RouterModule } from '@angular/router'; // Asegúrate de importar Router
import { RutaDTO } from '../../dto/RutaDTO';
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
export class RutaEditComponent {
  Ruta$!: Observable<RutaDTO>;
  updatedRuta: RutaDTO = {} as RutaDTO;
  errorMessage: string = '';

  constructor(private RutaService: RutaService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.Ruta$ = this.RutaService.buscarRutaPorId(id).pipe(
      catchError(error => {
        console.error("Error al buscar la Ruta", error);
        this.errorMessage = "Error al buscar la Ruta";
        return of({} as RutaDTO); // Retorna un objeto vacío o maneja el error según prefieras
      })
    );

    // Suscribirse a conductor$ para asignar los valores a updatedConductor
    this.Ruta$.subscribe(Ruta => {
      if (Ruta) {
        this.updatedRuta = Ruta;
      }
    });
  }

  updateRuta(): void {
    this.RutaService.actualizarRuta(this.updatedRuta).subscribe({
      next: (response) => {
        this.router.navigate(['/ruta/list']); // Redirigir después de editar
      },
      error: (error) => {
        console.error('Error al actualizar el Ruta', error);
        this.errorMessage = 'Error al actualizar el Ruta';
      }
    });
  }
}
