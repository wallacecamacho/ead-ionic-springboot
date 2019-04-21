import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';

import { LoginPage } from '../login.page';
import { CrudService } from 'src/app/shared/services/crud.service';
import { IonicStorageModule } from '@ionic/storage';
import { MenuService } from 'src/app/shared/services/menu.service';

const routes: Routes = [
  {
    path: '',
    component: LoginPage
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
  declarations: [LoginPage],
  providers: [ CrudService, MenuService ]
})
export class LoginPageModule {}
