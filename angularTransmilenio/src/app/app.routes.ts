import { Routes } from '@angular/router';
import { AsignacionCreateComponent } from './asignacion/asignacion-create/asignacion-create.component';
import { AsignacionViewComponent } from './asignacion/asignacion-view/asignacion-view.component';
import { ConductorCreateComponent } from './conductor/conductor-create/conductor-create.component';
import { ConductorEditComponent } from './conductor/conductor-edit/conductor-edit.component';
import { ConductorListComponent } from './conductor/conductor-list/conductor-list.component';
import { ConductorSearchComponent } from './conductor/conductor-search/conductor-search.component';
import { ConductorViewComponent } from './conductor/conductor-view/conductor-view.component';
import { RutaComponent } from './ruta/ruta.component';
import { BusComponent } from './bus/bus.component';

export const routes: Routes = [
    { path: 'asignacion/asignacion-create', component: AsignacionCreateComponent },
    { path: 'asignacion/view/:id', component: AsignacionViewComponent },  // Incluye el ID para asignación
    { path: 'conductor/conductor-create', component: ConductorCreateComponent },
    { path: 'conductor/edit/:id', component: ConductorEditComponent },  // Incluye el ID para editar
    { path: 'conductor/conductor-list', component: ConductorListComponent },
    { path: 'conductor/conductor-search', component: ConductorSearchComponent },
    { path: 'conductor/view/:id', component: ConductorViewComponent },
    { path: 'ruta', component: RutaComponent },
    { path: 'bus', component: BusComponent},
    { path: '', pathMatch: 'full', redirectTo: 'conductor/conductor-list' },
];

