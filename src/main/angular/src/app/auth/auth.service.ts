import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {ApiService} from "../service/api.service";
import {catchError, mapTo, tap} from "rxjs/operators";
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly LOGIN_URL = '/auth'
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private loggedUser = {};

  constructor(private http: HttpClient,
              private _apiService: ApiService,
              private toastrService: ToastrService) { }

  login(credentials: {username: string, password: string}): Observable<boolean> {
    const url = `${this.LOGIN_URL}/login`;
    return this._apiService.post(url, credentials)
      .pipe(
        tap(response => this.doLoginUser(response)),
        mapTo(true),
        catchError(err => {
          this.toastrService.error(err.error);
          return of(false);
        })
      )
  }

  logout() {
    this.loggedUser = null;
    localStorage.removeItem(this.JWT_TOKEN);
  }

  isLoggedIn() {
    return !!this.getJwtToken();
  }

  getJwtToken() {
    return localStorage.getItem(this.JWT_TOKEN);
  }

  getAccount() {
    console.log(this.loggedUser)
    return this.loggedUser;
  }

  private doLoginUser(response: any) {
    this.loggedUser = response.account;
    localStorage.setItem(this.JWT_TOKEN, response.token);
  }
}
