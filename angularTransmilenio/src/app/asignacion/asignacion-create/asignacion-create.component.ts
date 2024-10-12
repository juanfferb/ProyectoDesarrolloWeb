import { Component, OnInit } from '@angular/core';
import { AsignacionService } from '../../shared/asignacion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AsignacionDTO } from '../../dto/AsignacionDTO';
import { ConductorService } from '../../shared/conductor.service';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-asignacion-create',
  standalone: true,
  imports: [NgFor, NgIf, FormsModule, RouterModule],
  templateUrl: './asignacion-create.component.html',
  styleUrls: ['./asignacion-create.component.css']
})
export class AsignacionCreateComponent implements OnInit {
  newAsignacion: AsignacionDTO = {} as AsignacionDTO;
  errorMessage: string = '';
  conductor: any; // Puedes definir un tipo más específico si lo deseas

  constructor(
    private asignacionService: AsignacionService,
    private router: Router,
    private route: ActivatedRoute,
    private conductorService: ConductorService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log('ID del conductor:', id);
    
    if (isNaN(id) || id <= 0) {
      console.error('ID de conductor no válido:', id);
      return;
    }
  
    this.conductorService.buscarConductoraPorId(id).subscribe({
      next: (conductor) => {
        this.conductor = conductor;
        this.newAsignacion.conductor = conductor; 
      },
      error: (error) => {
        console.error('Error al obtener el conductor', error);
        this.errorMessage = 'No se pudo obtener el conductor';
      }
    });
  }
  
  createAsignacion(): void {
    this.asignacionService.crearAsignacion(this.newAsignacion).subscribe({
      next: (response) => {
        this.router.navigate(['/asignacion/asignacion-list']);
      },
      error: (error) => {
        console.error('Error al crear la asignación', error);
        this.errorMessage = 'Error al crear la asignación';
      },
      complete: () => {
        console.log('Asignación creada exitosamente');
      }
    });
  }
}
