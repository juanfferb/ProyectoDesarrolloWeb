import { Component } from '@angular/core';
import { ReactiveFormsModule, FormControl } from '@angular/forms'; // Asegúrate de incluir ReactiveFormsModule
import { ActivatedRoute } from '@angular/router';
import { ConductorService } from '../../shared/conductor.service';
import { RouterModule } from '@angular/router';
import { ConductorDTO } from '../../dto/ConductorDTO';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-conductor-view',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe, CommonModule, ReactiveFormsModule],
  templateUrl: './conductor-view.component.html',
  styleUrls: ['./conductor-view.component.css']
})
export class ConductorViewComponent {
  conductor$!: Observable<ConductorDTO>;
  errorMessage: string = '';

  constructor(private conductorService: ConductorService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.conductor$ = this.conductorService.buscarConductoraPorId(id).pipe(
      catchError(error => {
        console.error("Error al buscar el conductor", error);
        this.errorMessage = "Error al buscar el conductor";
        return of({} as ConductorDTO); // Retorna un objeto vacío en caso de error
      })
    );
  }
}
