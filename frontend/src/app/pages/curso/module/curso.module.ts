import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';


import { CursoService } from '../service/curso.service';
import { CursosService } from '../service/cursos.service';
import { CursoEditComponent } from '../edit/curso-edit.component';
import { CursoListPageComponent } from '../list/curso-list.page';

const routes: Routes = [
  {
    path: '',
    component: CursoListPageComponent,
    resolve: {
      data: CursosService
    }
  },
  {
    path: 'edit/:id',
    component: CursoEditComponent,
    resolve: {
      data: CursoService
    }
  },
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [CursoListPageComponent, CursoEditComponent],
  providers: [
    CursoService,
    CursosService,
  ]
})
export class CursoModule { }
