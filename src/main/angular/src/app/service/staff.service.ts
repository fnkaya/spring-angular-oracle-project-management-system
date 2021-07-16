import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ApiService} from "./api.service";
import {Staff} from "../model/staff";
import {Pageable} from "../model/pageable";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  private STAFF_PATH = "/staff"

  constructor(private _apiService: ApiService) { }

  get(id: number): Observable<Staff> {
    const url = `${this.STAFF_PATH}/${id}`;
    return this._apiService.get(url, null);
  }

  getAllWithAccount(pageable: Pageable): Observable<Staff[]>{
    const url = `${this.STAFF_PATH}/with-account`;
    let httpParams = new HttpParams().append('page', pageable.page.toString())
      .append('size', pageable.size.toString());
    return this._apiService.get(url, httpParams);
  }

  getAllStaffNonManagerWithoutIssue(): Observable<Staff[]> {
    const url = `${this.STAFF_PATH}/non-manager`;
    return this._apiService.get(url, null);
  }

  getAllManagerWithoutProject(): Observable<Staff[]> {
    const url = `${this.STAFF_PATH}/managers/without-project`;
    return this._apiService.get(url, null);
  }

  search(keyword: string, pageable: Pageable): Observable<Staff[]> {
    const url = `${this.STAFF_PATH}/search`;
    let httpParams = new HttpParams().append('keyword', keyword)
      .append('page', pageable.page.toString())
      .append('size', pageable.size.toString());
    return this._apiService.get(url, httpParams);
  }

  getAllPositions(): Observable<any>{
    const url = `${this.STAFF_PATH}/positions`;
    return  this._apiService.get(url, null);
  }

  update(staff: Staff) {
    const url = `${this.STAFF_PATH}`;
    return this._apiService.put(url, staff);
  }
}
