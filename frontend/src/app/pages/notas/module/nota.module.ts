import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';


import { NotaService } from '../service/nota.service';
import { NotasService } from '../service/notas.service';
import { NotaEditComponent } from '../edit/nota-edit.component';
import { NotaListPageComponent } from '../list/nota-list.page';
import { AvaliacoesService } from '../../avaliacoes/service/avaliacoes.service';
import { CursosService } from '../../curso/service/cursos.service';
import { CrudService } from 'src/app/shared/services/crud.service';

const routes: Routes = [
  {
    path: '',
    component: NotaListPageComponent,
    resolve: {
      data: NotasService
    }
  },
  {
    path: 'edit/:id',
    component: NotaEditComponent,
    resolve: {
      data: NotaService
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
  declarations: [NotaListPageComponent, NotaEditComponent],
  providers: [
    NotaService,
    NotasService,
    AvaliacoesService,
    CursosService,
    CrudService

  ]
})
export class NotaModule { }
