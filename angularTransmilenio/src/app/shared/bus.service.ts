import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { BusDTO } from '../dto/BusDTO';

@Injectable({
  providedIn: 'root'
})
export class BusService {
  private httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
    })
  };

  constructor(private http: HttpClient) {}

  // Método para listar todos los Buses
  listarBuses(): Observable<BusDTO[]> {
    return this.http.get<BusDTO[]>(`${environment.SERVER_URL}/bus/list`);
  }

  // Método para recuperar un Bus por ID
  buscarBusPorId(id: number): Observable<BusDTO> {
    return this.http.get<BusDTO>(`${environment.SERVER_URL}/bus/view/${id}`);
  }

  // Método para crear un nuevo Bus
  crearBus(BusDTO: BusDTO): Observable<BusDTO> {
    return this.http.post<BusDTO>(
      `${environment.SERVER_URL}/bus/create`,
      BusDTO,
      this.httpOptions
    );
  }

  // Método para buscar Buses por nombre
  buscarBusPorNombre(textoBusqueda: string): Observable<BusDTO[]> {
    return this.http.get<BusDTO[]>(`${environment.SERVER_URL}/bus/search?nombre=${textoBusqueda}`);
  }

  // Método para eliminar un Bus por ID
  eliminarBus(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.SERVER_URL}/bus/delete/${id}`);
  }
}
