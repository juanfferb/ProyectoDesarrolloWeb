import { Component, OnInit } from '@angular/core';
import { AsignacionService } from '../../shared/asignacion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AsignacionDTO } from '../../dto/AsignacionDTO';
import { ConductorService } from '../../shared/conductor.service';
import { BusService } from '../../shared/bus.service'; 
import { RutaService } from '../../shared/ruta.service'; 
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-asignacion-create',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, FormsModule],
  templateUrl: './asignacion-create.component.html',
  styleUrls: ['./asignacion-create.component.css']
})
export class AsignacionCreateComponent implements OnInit {
  newAsignacion: AsignacionDTO = {} as AsignacionDTO;
  buses: any[] = []; 
  rutas: any[] = []; 
  diasSemana: string[] = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo']; 
  diasSeleccionados: string[] = []; 
  errorMessage: string = '';
  conductor: any;

  constructor(
    private asignacionService: AsignacionService,
    private router: Router,
    private route: ActivatedRoute,
    private conductorService: ConductorService,
    private busService: BusService,
    private rutaService: RutaService
  ) {}

  ngOnInit(): void {
    this.obtenerConductor();
    this.cargarBusesYRutas();
  }

  private obtenerConductor(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (isNaN(id) || id <= 0) {
      console.error('ID de conductor no válido:', id);
      this.errorMessage = 'ID de conductor no válido';
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

  private cargarBusesYRutas(): void {
    this.busService.listarBuses().subscribe(
      (buses) => this.buses = buses,
      (error) => {
        console.error('Error al cargar buses', error);
        this.errorMessage = 'No se pudo cargar la lista de buses';
      }
    );

    this.rutaService.listarRutas().subscribe(
      (rutas) => this.rutas = rutas,
      (error) => {
        console.error('Error al cargar rutas', error);
        this.errorMessage = 'No se pudo cargar la lista de rutas';
      }
    );
  }

  createAsignacion(): void {
    if (this.diasSeleccionados.length === 0) {
      this.errorMessage = 'Debes seleccionar al menos un día de asignación.';
      return;
    }
  
    this.newAsignacion.diasAsignacion = this.diasSeleccionados.join(',');
  
    // Obtén el ID del conductor que ya tienes en el objeto this.conductor
    const conductorId = this.conductor.id;
  
    this.asignacionService.crearAsignacion(this.newAsignacion).subscribe({
      next: () => this.router.navigate(['/asignacion/view', conductorId]), // Redirigir con el ID del conductor
      error: (error) => {
        console.error('Error al crear la asignación', error);
        this.errorMessage = 'Error al crear la asignación';
      }
    });
  }
  

  onDiaChange(dia: string, event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.checked) {
      this.diasSeleccionados.push(dia);
    } else {
      const index = this.diasSeleccionados.indexOf(dia);
      if (index > -1) {
        this.diasSeleccionados.splice(index, 1);
      }
    }
  }
}
