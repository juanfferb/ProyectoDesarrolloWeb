import { Component } from '@angular/core';
import { ReactiveFormsModule, FormControl } from '@angular/forms'; // Asegúrate de incluir ReactiveFormsModule
import { ActivatedRoute } from '@angular/router';
import { RutaService } from '../../shared/ruta.service';
import { RouterModule } from '@angular/router';
import { RutaDTO } from '../../dto/RutaDTO';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-Ruta-view',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe, CommonModule, ReactiveFormsModule],
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class RutaViewComponent {
  Ruta$!: Observable<RutaDTO>;
  errorMessage: string = '';

  constructor(private RutaService: RutaService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.Ruta$ = this.RutaService.buscarRutaPorId(id).pipe(
      catchError(error => {
        console.error("Error al buscar la Ruta", error);
        this.errorMessage = "Error al buscar la Ruta";
        return of({} as RutaDTO); // Retorna un objeto vacío en caso de error
      })
    );
  }
}
