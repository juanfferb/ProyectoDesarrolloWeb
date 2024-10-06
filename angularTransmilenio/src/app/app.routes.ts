import { Routes } from '@angular/router';
import { AsignacionCreateComponent } from './asignacion/asignacion-create/asignacion-create.component';
import { AsignacionViewComponent } from './asignacion/asignacion-view/asignacion-view.component';
import { ConductorCreateComponent } from './conductor/conductor-create/conductor-create.component';
import { ConductorEditComponent } from './conductor/conductor-edit/conductor-edit.component';
import { ConductorListComponent } from './conductor/conductor-list/conductor-list.component';
import { ConductorSearchComponent } from './conductor/conductor-search/conductor-search.component';
import { ConductorViewComponent } from './conductor/conductor-view/conductor-view.component';
import { RutaComponent } from './ruta/ruta.component';

export const routes: Routes = [
    { path: 'asignacion/asignacion-create', component: AsignacionCreateComponent },
    { path: 'asignacion/view/:id', component: AsignacionViewComponent },  // Incluye el ID para asignación
    { path: 'conductor/conductor-create', component: ConductorCreateComponent },
    { path: 'conductor/edit/:id', component: ConductorEditComponent },  // Incluye el ID para editar
    { path: 'conductor/conductor-list', component: ConductorListComponent },
    { path: 'conductor/conductor-search', component: ConductorSearchComponent },
    { path: 'conductor/view/:id', component: ConductorViewComponent },  // Incluye el ID para ver detalles del conductor
    { path: 'ruta', component: RutaComponent },
    { path: '', pathMatch: 'full', redirectTo: 'conductor/conductor-list' },
];


/*import { Routes } from '@angular/router';
import { AsignacionCreateComponent } from './asignacion/asignacion-create/asignacion-create.component';
import { AsignacionViewComponent } from './asignacion/asignacion-view/asignacion-view.component';
import { ConductorCreateComponent } from './conductor/conductor-create/conductor-create.component';
import { ConductorEditComponent } from './conductor/conductor-edit/conductor-edit.component';
import { ConductorListComponent } from './conductor/conductor-list/conductor-list.component';
import { ConductorSearchComponent } from './conductor/conductor-search/conductor-search.component';
import { ConductorViewComponent } from './conductor/conductor-view/conductor-view.component';

export const routes: Routes = [
    { path: 'asignacion/asignacion-create', component: AsignacionCreateComponent }, // Corregido 'asigancion'
    { path: 'asignacion/asignacion-view', component: AsignacionViewComponent },
    { path: 'conductor/conductor-create', component: ConductorCreateComponent },
    { path: 'conductor/conductor-edit', component: ConductorEditComponent },
    { path: 'conductor/conductor-list', component: ConductorListComponent },
    { path: 'conductor/conductor-search', component: ConductorSearchComponent },
    { path: 'conductor/conductor-view', component: ConductorViewComponent },
    { path: '', pathMatch: 'full', redirectTo: 'conductor/conductor-list' },
];*/
