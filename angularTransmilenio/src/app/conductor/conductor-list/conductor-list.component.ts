import { Component } from '@angular/core';
import { ConductorService } from '../../shared/conductor.service';
import { RouterModule } from '@angular/router';  // Importar RouterModule
import { ConductorDTO } from '../../dto/ConductorDTO';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-conductor-list',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe],  // Agregar RouterModule aquí
  templateUrl: './conductor-list.component.html',
  styleUrls: ['./conductor-list.component.css']  // Cambiado a styleUrls
})
export class ConductorListComponent {
  allConductors$!: Observable<ConductorDTO[]>;
  errorMessage: string = '';
 
  constructor(private ConductorService: ConductorService) {}
 
  ngOnInit(): void {
    this.allConductors$ = this.ConductorService.listarConductoras()
      .pipe(
        catchError(
          error => {
            console.log("Hubo un error");
            this.errorMessage = "Hubo un error";
            return of([]);
          }
 
        )
      )
    
    
    ;
  }

  deleteConductor(id: number): void {
    if (confirm('¿Estás seguro de que quieres eliminar este conductor?')) {
        this.ConductorService.eliminarConductor(id).subscribe(
            response => {
                // Manejar la respuesta, por ejemplo, refrescando la lista de conductores
                this.ngOnInit();
            },
            error => {
                console.error('Error al eliminar el conductor', error);
                this.errorMessage = 'Error al eliminar el conductor';
            }
        );
    }
}

}
