import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private  http: HttpClient) { }

  get(path: string, httpParams: any): Observable<any> {
    return this.http.get(environment.API_BASE_PATH + path, {params: httpParams})
      .pipe(
        catchError(this.handleError)
      );
  }

  post(path: string, httpParams: any): Observable<any>{
    return this.http.post(environment.API_BASE_PATH + path, JSON.stringify(httpParams), this.httpOptions)
      .pipe(
        catchError(this.handleError)
    );
  }

  put(path: string, httpParams: any): Observable<any>{
    return this.http.put(environment.API_BASE_PATH + path, JSON.stringify(httpParams), this.httpOptions)
      .pipe(
        catchError(this.handleError)
    );
  }

  delete(path: string, httpParams: any): Observable<any>{
    return this.http.delete(environment.API_BASE_PATH + path, {params: httpParams})
      .pipe(
        catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse){
    return throwError(error)
  }
}
