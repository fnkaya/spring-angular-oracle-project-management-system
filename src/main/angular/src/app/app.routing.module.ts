import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PageNotFoundComponent} from "./shared/page-not-found/page-not-found.component";
import {LayoutModule} from "./layout/layout.module";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: '', loadChildren: () => LayoutModule},
  {path: 'login', component: LoginComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
