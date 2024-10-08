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
 
  listarConductoras(): Observable<ConductorDTO[]> {
    return this.http.get<ConductorDTO[]>(`${environment.SERVER_URL}/conductor/list`);
  }
 
  buscarConductoraPorId(id: number) : Observable<ConductorDTO> {
    return this.http.get<ConductorDTO>(`${environment.SERVER_URL}/conductor/view/${id}`);
  }
 
  crearConductora(ConductorDTO: ConductorDTO) : Observable<ConductorDTO> {
    return this.http.post<ConductorDTO>(
      `${environment.SERVER_URL}/conductor/create`,
      ConductorDTO,
      this.httpOptions
    )
  }
  eliminarConductor(id: number): Observable<ConductorDTO> {
    return this.http.delete<ConductorDTO>(`${environment.SERVER_URL}/conductor/delete/${id!}`);
  }
}
