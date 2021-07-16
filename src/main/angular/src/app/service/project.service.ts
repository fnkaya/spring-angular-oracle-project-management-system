import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";
import {Project} from "../model/project";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private PROJECT_PATH = "/projects";

  constructor(private _apiService: ApiService) {
  }

  save(project: Project): Observable<any> {
    const url = this.PROJECT_PATH;
    return this._apiService.post(url, project);
  }

  update(project: Project): Observable<any> {
    const url = this.PROJECT_PATH;
    return this._apiService.put(url, project);
  }

  delete(id: number): Observable<any> {
    const url = `${this.PROJECT_PATH}/${id}`;
    return this._apiService.put(url, null);
  }

  get(id: number): Observable<Project> {
    const url = `${this.PROJECT_PATH}/${id}`;
    return this._apiService.get(url, null);
  }

  getAll(): Observable<Project> {
    const url = `${this.PROJECT_PATH}`;
    return this._apiService.get(url, null);
  }

  getAllWithManager(): Observable<Project[]> {
    const url = `${this.PROJECT_PATH}/with-manager`;
    return this._apiService.get(url, null );
  }

  search(keyword: string): Observable<Project[]> {
    const url = `${this.PROJECT_PATH}/search`;
    let httpParams = new HttpParams().append('keyword', keyword)
    return this._apiService.get(url, httpParams)
  }
}
