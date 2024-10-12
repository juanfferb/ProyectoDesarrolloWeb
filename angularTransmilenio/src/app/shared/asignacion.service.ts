import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { AsignacionDTO } from '../dto/AsignacionDTO';

@Injectable({
  providedIn: 'root'
})
export class AsignacionService {
  private httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
    })
  };

  constructor(private http: HttpClient) {}

  // Método para obtener asignaciones por ID de conductor
  buscarAsignacionesByConductorId(conductorId: number): Observable<AsignacionDTO[]> {
    return this.http.get<AsignacionDTO[]>(`${environment.SERVER_URL}/asignacion/list/${conductorId}`);
}

  // Método para crear una nueva asignación
  crearAsignacion(asignacionDTO: AsignacionDTO): Observable<AsignacionDTO> {
    return this.http.post<AsignacionDTO>(
      `${environment.SERVER_URL}/asignacion/create`,
      asignacionDTO,
      this.httpOptions
    );
  }

  // Método para obtener una asignación por ID
  getAsignacionById(id: number): Observable<AsignacionDTO> {
    return this.http.get<AsignacionDTO>(`${environment.SERVER_URL}/asignacion/view/${id}`);
  }

  // Método para eliminar una asignación por ID
  eliminarAsignacion(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.SERVER_URL}/asignacion/delete/${id}`);
  }
}
