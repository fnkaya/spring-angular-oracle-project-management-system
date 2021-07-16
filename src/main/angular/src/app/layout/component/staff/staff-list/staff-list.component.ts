import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Staff} from "../../../../model/staff";
import {ColumnMode} from "@swimlane/ngx-datatable";
import {Pageable} from "../../../../model/pageable";
import {StaffService} from "../../../../service/staff.service";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './staff-list.component.html',
  styleUrls: ['./staff-list.component.css']
})
export class StaffListComponent implements OnInit {

  staffList: Staff[];
  pageable: Pageable = new Pageable();

  columnMode = ColumnMode;

  @ViewChild('searchInput') searchBox;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private toastrService: ToastrService,
              private _staffService: StaffService) { }

  ngOnInit(): void {
    this.activatedRoute.queryParamMap.subscribe(() => {
      this.setPage({offset: 0});
    })
  }

  setPage(pagination){
    this.pageable.page = pagination.offset;

    if (this.activatedRoute.snapshot.queryParamMap.has('keyword')){
      const keyword = this.activatedRoute.snapshot.queryParamMap.get('keyword');
      this.searchStaff(keyword);
    }
    else{
      this.getStaffList();
    }
  }

  private getStaffList() {
    this._staffService.getAllWithAccount(this.pageable).subscribe(
      response => this.mapData(response),
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  navigateUrlForSearch(keyword: string) {
    if (keyword != null && keyword.trim() != "")
      this.router.navigate(['/staff/search'], { queryParams: { keyword } });
  }

  private searchStaff(keyword: string) {
    this._staffService.search(keyword, this.pageable).subscribe(
      response => this.mapData(response),
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private mapData(data) {
    this.pageable.page = data.page;
    this.pageable.size = data.size;
    this.pageable.totalElements = data.totalElements
    this.staffList = data.content;
  }

  openModal(template: TemplateRef<any>) {

  }

  clearSearch() {
    this.ngOnInit();
    this.router.navigateByUrl('/staff')
    this.searchBox.nativeElement.valid = '';
  }
}
