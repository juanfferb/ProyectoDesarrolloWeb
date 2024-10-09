import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { RutaDTO } from '../dto/RutaDTO';

@Injectable({
  providedIn: 'root'
})
export class RutaService {
  private httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
    })
  };

  constructor(private http: HttpClient) {}

  // Método para listar todos los Rutaes
  listarRutaes(): Observable<RutaDTO[]> {
    return this.http.get<RutaDTO[]>(`${environment.SERVER_URL}/ruta/list`);
  }

  // Método para recuperar un Ruta por ID
  RutacarRutaPorId(id: number): Observable<RutaDTO> {
    return this.http.get<RutaDTO>(`${environment.SERVER_URL}/ruta/view/${id}`);
  }

  // Método para crear un nuevo Ruta
  crearRuta(RutaDTO: RutaDTO): Observable<RutaDTO> {
    return this.http.post<RutaDTO>(
      `${environment.SERVER_URL}/ruta/create`,
      RutaDTO,
      this.httpOptions
    );
  }

  // Método para Rutacar Rutaes por nombre
  RutacarRutaPorNombre(textoRutaqueda: string): Observable<RutaDTO[]> {
    return this.http.get<RutaDTO[]>(`${environment.SERVER_URL}/ruta/search?nombre=${textoRutaqueda}`);
  }

  // Método para eliminar un Ruta por ID
  eliminarRuta(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.SERVER_URL}/ruta/delete/${id}`);
  }
}
