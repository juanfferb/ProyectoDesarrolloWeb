import { Component } from '@angular/core';
import { BusService } from '../../shared/bus.service';
import { RouterModule } from '@angular/router';  // Importar RouterModule
import { BusDTO } from '../../dto/BusDTO';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-Bus-list',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe],  // Agregar RouterModule aquí
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']  // Cambiado a styleUrls
})
export class BusListComponent {
  allBuses$!: Observable<BusDTO[]>;
  errorMessage: string = '';
 
  constructor(private BusService: BusService) {}
 
  ngOnInit(): void {
    this.allBuses$ = this.BusService.listarBuses()
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

  deleteBus(id: number): void {
    if (confirm('Recuerde que al eliminar un bus se eliminarán todas las asignaciones asociadas a ese bus. ¿Desea continuar?')) {
        if (confirm('¿Estás seguro de que quieres eliminar este Bus?')) {
            this.BusService.eliminarBus(id).subscribe(
                response => {
                    // Manejar la respuesta, por ejemplo, refrescando la lista de Buses
                    this.ngOnInit();
                },
                error => {
                    console.error('Error al eliminar el Bus', error);
                    this.errorMessage = 'Error al eliminar el Bus';
                }
            );
        }
    }
  }


}
