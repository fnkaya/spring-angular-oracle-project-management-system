import {Component, OnInit, ViewChild} from '@angular/core';
import {Issue} from "../../../../model/issue";
import {IssueService} from "../../../../service/issue.service";
import {ToastrService} from "ngx-toastr";
import {ColumnMode} from "@swimlane/ngx-datatable";
import {Pageable} from "../../../../model/pageable";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-issue',
  templateUrl: './issue-list.component.html',
  styleUrls: ['./issue-list.component.css']
})
export class IssueListComponent implements OnInit {

  issueList: Issue[];
  issueStatusList: [];
  pageable: Pageable = new Pageable();

  issueStatusForm: FormGroup;

  columnMode = ColumnMode;

  @ViewChild('searchInput') searchBox;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private formBuilder: FormBuilder,
              private toastrService: ToastrService,
              private _issueService: IssueService) {
  }

  ngOnInit(): void {
    this.getIssueStatusList();
    this.initForm();
    this.activatedRoute.queryParamMap.subscribe(() => {
      this.setPage({offset: 0});
    })
  }

  private getIssueStatusList() {
    this._issueService.getIssueStatusList().subscribe(
      response => this.issueStatusList = response,
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  setPage(pagination) {
    this.pageable.page = pagination.offset;

    if (this.activatedRoute.snapshot.queryParamMap.has('keyword')) {
      const keyword = this.activatedRoute.snapshot.queryParamMap.get('keyword');
      this.searchIssue(keyword);
    } else if (this.activatedRoute.snapshot.queryParamMap.has('status')) {
      const status = this.activatedRoute.snapshot.queryParamMap.get('status');
      this.filterByIssueStatus(status);
    } else {
      this.getIssueList()
    }
  }

  private getIssueList() {
    this._issueService.getAll(this.pageable).subscribe(
      response => this.mapData(response),
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  navigateUrlForSearch(keyword: string) {
    if (keyword != null && keyword.trim() != "")
      this.router.navigate(['/issues/search'], { queryParams: { keyword } });
  }

  private searchIssue(keyword: string) {
    this._issueService.search(keyword, this.pageable).subscribe(
      response => this.mapData(response),
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  navigateUrlForFilter() {
    const status = this.issueStatusForm.value.value;
    this.router.navigate(['/issues/filter'], { queryParams: { status } });
  }

  private filterByIssueStatus(status: string) {
    this._issueService.filter(status, this.pageable).subscribe(
      response => this.mapData(response),
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private mapData(data) {
    this.pageable.page = data.page;
    this.pageable.size = data.size;
    this.pageable.totalElements = data.totalElements
    this.issueList = data.content;
  }

  clearSearch() {
    this.ngOnInit();
    this.router.navigateByUrl('/issues');
    this.searchBox.nativeElement.valid = '';
  }

  private initForm() {
    this.issueStatusForm = this.formBuilder.group({
      value: null,
      description: null,
    })
  }
}
