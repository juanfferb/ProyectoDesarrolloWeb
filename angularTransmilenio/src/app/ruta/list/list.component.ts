import { Component } from '@angular/core';
import { RutaService } from '../../shared/ruta.service';
import { RouterModule } from '@angular/router';  // Importar RouterModule
import { RutaDTO } from '../../dto/RutaDTO';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-Ruta-list',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe],  // Agregar RouterModule aquí
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']  // Cambiado a styleUrls
})
export class RutaListComponent {
  allRutas$!: Observable<RutaDTO[]>;
  errorMessage: string = '';
 
  constructor(private RutaService: RutaService) {}
 
  ngOnInit(): void {
    this.allRutas$ = this.RutaService.listarRutas()
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

  deleteRuta(id: number): void {
    if (confirm('Recuerde que al eliminar una ruta se eliminarán todas las asignaciones asociadas a esa ruta. ¿Desea continuar?')) {
        if (confirm('¿Estás seguro de que quieres eliminar esta ruta?')) {
            this.RutaService.eliminarRuta(id).subscribe(
                response => {
                    // Manejar la respuesta, por ejemplo, refrescando la lista de rutas
                    this.ngOnInit();
                },
                error => {
                    console.error('Error al eliminar la ruta', error);
                    this.errorMessage = 'Error al eliminar la ruta';
                }
            );
        }
    }
  }


}
