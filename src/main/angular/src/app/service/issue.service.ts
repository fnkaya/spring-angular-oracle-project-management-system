import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Issue} from "../model/issue";
import {Pageable} from "../model/pageable";
import {HttpParams} from "@angular/common/http";
import {IssueHistory} from "../model/issue-history";

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  private ISSUE_PATH = '/issues';

  constructor(private _apiService: ApiService) {
  }

  save(issue: Issue): Observable<any> {
    const url = `${this.ISSUE_PATH}`;
    return this._apiService.post(url, issue);
  }

  update(issue: Issue): Observable<any> {
    const url = `${this.ISSUE_PATH}`;
    return this._apiService.put(url, issue);
  }

  delete(id: number): Observable<any> {
    const url = `${this.ISSUE_PATH}/${id}`;
    return this._apiService.delete(url, null);
  }

  get(id: number): Observable<Issue> {
    let url = `${this.ISSUE_PATH}/${id}`;
    return this._apiService.get(url, null);
  }

  getAll(pageable: Pageable): Observable<Issue[]> {
    const url = `${this.ISSUE_PATH}`
    let httpParams = new HttpParams().append('page', pageable.page.toString())
      .append('size', pageable.size.toString());
    return this._apiService.get(url, httpParams);
  }

  search(keyword: string, pageable: Pageable): Observable<Issue[]> {
    const url = `${this.ISSUE_PATH}/search`;
    let httpParams = new HttpParams().append('keyword', keyword)
      .append('page', pageable.page.toString())
      .append('size', pageable.size.toString());
    return this._apiService.get(url, httpParams)
  }

  filter(status: string, pageable: Pageable): Observable<Issue[]> {
    const url = `${this.ISSUE_PATH}/filter`;
    let httpParams = new HttpParams().append('status', status)
      .append('page', pageable.page.toString())
      .append('size', pageable.size.toString());
    return this._apiService.get(url, httpParams);
  }

  getByProjectId(project_id: number, pageable: Pageable): Observable<Issue[]> {
    const url = `${this.ISSUE_PATH}/project-id/${project_id}`;
    let httpParams = new HttpParams().append('page', pageable.page.toString())
      .append('size', pageable.size.toString());
    return this._apiService.get(url, httpParams);
  }

  getIssueHistoryByIssueId(id: number, pageable: Pageable): Observable<IssueHistory[]> {
    const url = `/issue-histories/${id}`;
    let httpParams = new HttpParams().append('page', pageable.page.toString())
      .append('size', pageable.size.toString());
    return this._apiService.get(url, httpParams);
  }

  getIssueStatusList(): Observable<any> {
    const url = `${this.ISSUE_PATH}/status`;
    return this._apiService.get(url, null);
  }

  getByStaffId(staff_id: number, pageable: Pageable) {
    const url = `${this.ISSUE_PATH}/staff-id/${staff_id}`;
    let httpParams = new HttpParams().append('page', pageable.page.toString())
      .append('size', pageable.size.toString());
    return this._apiService.get(url, httpParams);
  }
}
