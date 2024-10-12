import { BusDTO } from "./BusDTO";
import { ConductorDTO } from "./ConductorDTO";
import { RutaDTO } from "./RutaDTO";

export class AsignacionDTO {
    constructor(
        public id: null | number,
        public conductor: ConductorDTO,
        public bus: BusDTO,
        public ruta: RutaDTO,
        public diasAsignacion: string
    ){}
}
