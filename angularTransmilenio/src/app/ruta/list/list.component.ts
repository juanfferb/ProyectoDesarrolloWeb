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
    if (confirm('¿Estás seguro de que quieres eliminar este Ruta?')) {
        this.RutaService.eliminarRuta(id).subscribe(
            response => {
                // Manejar la respuesta, por ejemplo, refrescando la lista de Rutaes
                this.ngOnInit();
            },
            error => {
                console.error('Error al eliminar el Ruta', error);
                this.errorMessage = 'Error al eliminar el Ruta';
            }
        );
    }
}

}
