import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ColumnMode} from "@swimlane/ngx-datatable";
import {Issue} from "../../../../model/issue";
import {IssueHistory} from "../../../../model/issue-history";
import {Staff} from "../../../../model/staff";
import {StaffService} from "../../../../service/staff.service";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, Router} from "@angular/router";
import {IssueService} from "../../../../service/issue.service";
import {Pageable} from "../../../../model/pageable";
import {ModalComponent} from "../../../../shared/modal/modal.component";
import {BsModalService} from "ngx-bootstrap/modal";

@Component({
  selector: 'app-issue-details',
  templateUrl: './issue-details.component.html',
  styleUrls: ['./issue-details.component.css']
})
export class IssueDetailsComponent implements OnInit {

  issue: Issue;
  issueHistoryList: IssueHistory[];
  staffOptions: Staff[];
  issueStatusList: [];
  pageable = new Pageable(5);

  issueForm: FormGroup;

  updateMode = false;
  error: string;

  columnMode = ColumnMode;

  constructor(private _staffService: StaffService,
              private _issueService: IssueService,
              private toastrService: ToastrService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private formBuilder: FormBuilder,
              private modalService: BsModalService) {
  }

  ngOnInit(): void {
    this.getIssue();
    this.getIssueStatusList();
    this.getAllIssueHistoryByIssueId({offset: 0});
    this.getAllStaffWithoutManager();
    this.initForm();
  }

  private getIssueStatusList() {
    this._issueService.getIssueStatusList().subscribe(
      response => this.issueStatusList = response,
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private getIssueIdFromUrl() {
    const id: number = +this.activatedRoute.snapshot.paramMap.get('id');
    return id;
  }

  private getIssue() {
    let id = this.getIssueIdFromUrl();
    this._issueService.get(id).subscribe(
      response => {
        this.issue = response;
        this.setForm(response);
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  getAllIssueHistoryByIssueId(event){
    this.pageable.page = event.offset;
    let id = this.getIssueIdFromUrl();
    this._issueService.getIssueHistoryByIssueId(id, this.pageable).subscribe(
      response => this.mapData(response),
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private mapData(data) {
    this.pageable.page = data.page;
    this.pageable.size = data.size;
    this.pageable.totalElements = data.totalElements
    this.issueHistoryList = data.content;
  }

  private getAllStaffWithoutManager() {
    this._staffService.getAllStaffNonManagerWithoutIssue().subscribe(
      response => this.staffOptions = response,
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private initForm() {
    this.issueForm = this.formBuilder.group({
      id: [null],
      code: [{value: null, disabled: !this.updateMode}],
      description: [{value: null, disabled: !this.updateMode}, Validators.required],
      details: [{value: null, disabled: !this.updateMode}, Validators.required],
      due_date: [{value: null, disabled: !this.updateMode}, Validators.required],
      status: [{value: null, disabled: !this.updateMode}, Validators.required],
      assignee_id: [{value: null, disabled: !this.updateMode}, Validators.required],
    })
  }

  private setForm(issue: Issue) {
    this.issueForm.controls.id.setValue(issue.id);
    this.issueForm.controls.code.setValue(issue.code);
    this.issueForm.controls.description.setValue(issue.description);
    this.issueForm.controls.details.setValue(issue.details);
    this.issueForm.controls.due_date.setValue(issue.due_date);
    this.issueForm.controls.status.setValue(issue.status);
    this.issueForm.controls.assignee_id.setValue(issue.assignee_id);
  }

  get controls() {
    return this.issueForm.controls;
  }

  changeEditMode() {
    this.updateMode = !this.updateMode;
    this.updateMode ? this.issueForm.enable() :this.issueForm.disable();
  }

  updateIssue() {
    if (!this.issueForm.valid) return;

    this._issueService.update(this.issueForm.value).subscribe(
      () => {
        this.toastrService.success("Issue successfully updated");
        this.changeEditMode();
        this.ngOnInit();
      },
      httpError =>  this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  showConfirmDialog() {
    const modal = this.modalService.show(ModalComponent);
    (<ModalComponent>modal.content).show('Issue silmek istiyor musunuz?');
    (<ModalComponent>modal.content).onClose.subscribe(
      result => result ? this.deleteIssue() : null
    );
  }

  private deleteIssue() {
    this._issueService.delete(this.issueForm.value.id).subscribe(
      () => {
        this.toastrService.success("Issue successfully deleted");
        this.router.navigate(['/issues']);
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  getColorClass() {
    return {
      'text-dark': !this.updateMode,
      'text-danger': this.updateMode
    }
  }


}
