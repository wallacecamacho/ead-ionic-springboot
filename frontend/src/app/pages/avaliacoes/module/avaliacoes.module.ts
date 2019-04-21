import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';


import { AvaliacaoService } from '../service/avaliacao.service';
import { AvaliacoesService } from '../service/avaliacoes.service';
import { AvaliacaoEditComponent } from '../edit/avaliacao-edit.component';
import { AvaliacaoListPageComponent } from '../list/avaliacao-list.page';
import { CursosService } from '../../curso/service/cursos.service';

const routes: Routes = [
  {
    path: '',
    component: AvaliacaoListPageComponent,
    resolve: {
      data: AvaliacoesService
    }
  },
  {
    path: 'edit/:id',
    component: AvaliacaoEditComponent,
    resolve: {
      data: AvaliacaoService
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
  declarations: [AvaliacaoListPageComponent, AvaliacaoEditComponent],
  providers: [
    AvaliacaoService,
    AvaliacoesService,
    CursosService

  ]
})
export class AvaliacoesModule { }
