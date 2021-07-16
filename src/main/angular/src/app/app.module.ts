import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {PageNotFoundComponent} from "./shared/page-not-found/page-not-found.component";
import {AppRoutingModule} from "./app.routing.module";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ModalComponent} from "./shared/modal/modal.component";
import {ApiService} from "./service/api.service";
import {ProjectService} from "./service/project.service";
import {StaffService} from "./service/staff.service";
import {LoginComponent} from "./login/login.component";
import {SharedModule} from "./shared/shared.module";
import {AuthService} from "./auth/auth.service";
import {AuthGuard} from "./auth/auth.guard";
import {TokenInterceptor} from "./auth/token.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    SharedModule,
  ],
  exports: [
  ],
  providers: [
    ApiService,
    ProjectService,
    StaffService,
    AuthService,
    AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
