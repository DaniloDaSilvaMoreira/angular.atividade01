import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FuncionarioListComponent } from './components/funcionario-list/funcionario-list.component';
import { FuncionarioFormComponent } from './components/funcionario-form/funcionario-form.component';

const routes: Routes = [
  {path: 'list', component: FuncionarioListComponent},
  {path: 'new', component: FuncionarioFormComponent},
  {path: 'edit/:id', component: FuncionarioFormComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FuncionarioRoutingModule { }