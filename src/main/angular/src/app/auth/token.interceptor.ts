import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthService} from "./auth.service";
import {catchError} from "rxjs/operators";
import {ToastrService} from "ngx-toastr";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public authService: AuthService,
              public toastrService: ToastrService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.authService.getJwtToken()) {
      request = this.addToken(request, this.authService.getJwtToken())
    }

    return next.handle(request)
      .pipe(
        catchError(err => {
          if (err instanceof HttpErrorResponse && err.status === 401) {
            this.toastrService.error(err.error.message, err.error.status);
          }
          else {
            return throwError(err);
          }
        })
      )
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {'Authorization': `Bearer ${token}`}
    })
  }
}
