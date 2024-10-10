import { Component } from '@angular/core';
import { ReactiveFormsModule, FormControl } from '@angular/forms'; // Asegúrate de incluir ReactiveFormsModule
import { ActivatedRoute } from '@angular/router';
import { BusService } from '../../shared/bus.service';
import { RouterModule } from '@angular/router';
import { BusDTO } from '../../dto/BusDTO';
import { catchError, Observable, of } from 'rxjs';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-Bus-view',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf, AsyncPipe, CommonModule, ReactiveFormsModule],
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class BusViewComponent {
  Bus$!: Observable<BusDTO>;
  errorMessage: string = '';

  constructor(private BusService: BusService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.Bus$ = this.BusService.buscarBusPorId(id).pipe(
      catchError(error => {
        console.error("Error al buscar el Bus", error);
        this.errorMessage = "Error al buscar el Bus";
        return of({} as BusDTO); // Retorna un objeto vacío en caso de error
      })
    );
  }
}
