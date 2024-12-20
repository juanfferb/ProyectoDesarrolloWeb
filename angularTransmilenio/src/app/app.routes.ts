import { Routes, provideRouter } from '@angular/router';
import { NgModule } from '@angular/core';
import { AsignacionCreateComponent } from './asignacion/asignacion-create/asignacion-create.component';
import { AsignacionViewComponent } from './asignacion/asignacion-view/asignacion-view.component';
import { ConductorCreateComponent } from './conductor/conductor-create/conductor-create.component';
import { ConductorEditComponent } from './conductor/conductor-edit/conductor-edit.component';
import { ConductorListComponent } from './conductor/conductor-list/conductor-list.component';
import { ConductorSearchComponent } from './conductor/conductor-search/conductor-search.component';
import { ConductorViewComponent } from './conductor/conductor-view/conductor-view.component';
import { RutaComponent } from './ruta/ruta.component';
import { BusComponent } from './bus/bus.component';
import { BusListComponent } from './bus/list/list.component';
import { BusCreateComponent } from './bus/create/create.component';
import { BusViewComponent } from './bus/view/view.component';
import { BusEditComponent } from './bus/edit/edit.component';
import { RutaListComponent } from './ruta/list/list.component';
import { RutaCreateComponent } from './ruta/create/create.component';
import { RutaViewComponent } from './ruta/view/view.component';
import { RutaEditComponent } from './ruta/edit/edit.component';
import { AuthGuard } from './guards/auth.guard';
import { LoginComponent } from './security/login/login.component';
import { AuthInterceptor } from './interceptor/auth.interceptor'; // Importa tu interceptor
import { bootstrapApplication } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { MainComponent } from './main/main.component';



export const routes: Routes = [
    { path: 'asignacion/asignacion-create/:id', component: AsignacionCreateComponent },
    { path: 'asignacion/view/:id', component: AsignacionViewComponent },  // Incluye el ID para asignación
    { path: 'conductor/conductor-create', component: ConductorCreateComponent },
    { path: 'conductor/edit/:id', component: ConductorEditComponent },  // Incluye el ID para editar
    { path: 'conductor/conductor-list', component: ConductorListComponent },
    { path: 'conductor/conductor-search', component: ConductorSearchComponent },
    { path: 'conductor/view/:id', component: ConductorViewComponent },
    { path: 'bus/list', component: BusListComponent },
    { path: 'bus/create', component: BusCreateComponent },
    { path: 'bus/view/:id', component: BusViewComponent },
    { path: 'ruta/list', component: RutaListComponent },
    { path: 'ruta/create', component: RutaCreateComponent },
    { path: 'ruta/view/:id', component: RutaViewComponent },
    { path: 'bus/edit/:id', component: BusEditComponent },
    { path: 'ruta/edit/:id', component: RutaEditComponent },
    { path: 'ruta', component: RutaComponent },
    { path: 'bus', component: BusComponent},
    { path: 'login', component: LoginComponent },
    { path: '', pathMatch: 'full', redirectTo: 'login' },
];

bootstrapApplication(MainComponent, {
    providers: [
        provideRouter(routes),
        provideHttpClient(withInterceptorsFromDi()),
        { 
            provide: HTTP_INTERCEPTORS, 
            useClass: AuthInterceptor, 
            multi: true 
        }
    ]
}).catch(err => console.error(err));

