import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';


import { HomeResultsPage } from './home-results.page';
import { PopmenuComponent } from 'src/app/shared/components/popmenu/popmenu.component';
import { CrudService } from 'src/app/shared/services/crud.service';
import { IonicStorageModule } from '@ionic/storage';

const routes: Routes = [
  {
    path: '',
    component: HomeResultsPage
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
  declarations: [HomeResultsPage, PopmenuComponent],
  providers: [ CrudService ]
})
export class HomeResultsPageModule {}
