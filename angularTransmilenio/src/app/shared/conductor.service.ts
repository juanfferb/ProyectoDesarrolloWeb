import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { ConductorDTO } from '../dto/ConductorDTO';

@Injectable({
  providedIn: 'root'
})
export class ConductorService {
  private httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
    })
  };

  constructor(private http: HttpClient) {}

  // Método para listar todos los conductores
  listarConductoras(): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${environment.SERVER_URL}/conductor/list`);
  }

  // Método para recuperar un conductor por ID
  buscarConductoraPorId(id: number): Observable<ConductorDTO> {
    return this.http.get<ConductorDTO>(`${environment.SERVER_URL}/conductor/view/${id}`);
  }

  // Método para crear un nuevo conductor
  crearConductora(conductorDTO: ConductorDTO): Observable<ConductorDTO> {
    return this.http.post<ConductorDTO>(
      `${environment.SERVER_URL}/conductor/create`,
      conductorDTO,
      this.httpOptions
    );
  }

  actualizarConductora(conductor: ConductorDTO): Observable<ConductorDTO> {
    return this.http.put<ConductorDTO>(`${environment.SERVER_URL}/conductor/update/${conductor.id}`, conductor)
  }

  // Método para buscar conductores por nombre
  buscarConductoresPorNombre(textoBusqueda: string): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${environment.SERVER_URL}/conductor/search?nombre=${textoBusqueda}`);
  }

  // Método para eliminar un conductor por ID
  eliminarConductor(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.SERVER_URL}/conductor/delete/${id}`);
  }
}
