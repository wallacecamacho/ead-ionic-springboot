import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: './pages/login/module/login.module#LoginPageModule' },
  { path: 'register', loadChildren: './pages/register/module/register.module#RegisterPageModule' },
  { path: 'cursos', loadChildren: './pages/curso/module/curso.module#CursoModule' },
  { path: 'avaliacoes', loadChildren: './pages/avaliacoes/module/avaliacoes.module#AvaliacoesModule' },
  { path: 'notas', loadChildren: './pages/notas/module/nota.module#NotaModule' },
  { path: 'about', loadChildren: './pages/about/about.module#AboutPageModule' },
  { path: 'settings', loadChildren: './pages/settings/settings.module#SettingsPageModule' },
  { path: 'edit-profile', loadChildren: './pages/edit-profile/edit-profile.module#EditProfilePageModule' },
  { path: 'home-results', loadChildren: './pages/home-results/home-results.module#HomeResultsPageModule' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
