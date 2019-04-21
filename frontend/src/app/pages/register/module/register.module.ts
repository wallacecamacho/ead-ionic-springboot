import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';

import { RegisterPage } from '../register.page';
import { UsuarioService } from '../service/usuario.service';
import { UsuariosService } from '../service/usuarios.service';
import { CrudService } from 'src/app/shared/services/crud.service';
import { IonicStorageModule } from '@ionic/storage';

const routes: Routes = [
  {
    path: '',
    component: RegisterPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    IonicStorageModule.forRoot()
  ],
  declarations: [RegisterPage],
  providers: [
    UsuarioService,
    UsuariosService,
    CrudService
  ]
})
export class RegisterPageModule {}
