import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {StaffService} from "../../../../service/staff.service";
import {Staff} from "../../../../model/staff";
import {ToastrService} from "ngx-toastr";
import {Issue} from "../../../../model/issue";
import {IssueService} from "../../../../service/issue.service";
import {ColumnMode} from "@swimlane/ngx-datatable";
import {Pageable} from "../../../../model/pageable";
import {ModalComponent} from "../../../../shared/modal/modal.component";
import {BsModalService} from "ngx-bootstrap/modal";

@Component({
  selector: 'app-staff-details',
  templateUrl: './staff-details.component.html',
  styleUrls: ['./staff-details.component.css']
})
export class StaffDetailsComponent implements OnInit {

  staffForm: FormGroup;
  staff: Staff;
  positionList: [];

  issueList: Issue[];
  pageable = new Pageable(5);

  columnMode = ColumnMode;
  updateMode = false;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private toastrService: ToastrService,
              private modalService: BsModalService,
              private _staffService: StaffService,
              private _issueService: IssueService) { }

  ngOnInit(): void {
    this.initForm();
    this.getStaff();
    this.getPositionList();
    this.getIssueList({offset: 0});
  }

  private getStaffIdFormUrl() {
    return +this.activatedRoute.snapshot.paramMap.get('id');
  }

  private getStaff() {
    const id = this.getStaffIdFormUrl();
    this._staffService.get(id).subscribe(
      response => {
        this.staff = response;
        this.setForm(response);
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private getPositionList() {
    this._staffService.getAllPositions().subscribe(
      response => {
        this.positionList = response;
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  getIssueList(event){
    this.pageable.page = event.offset;
    const id: number = this.getStaffIdFormUrl();
    this._issueService.getByStaffId(id, this.pageable).subscribe(
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

  private initForm() {
    this.staffForm = this.formBuilder.group({
      id: [null],
      name: [{value: null, disable: !this.updateMode}, Validators.required],
      email: [{value: null, disable: !this.updateMode}, Validators.required],
      position: [{value: null, disable: !this.updateMode}, Validators.required]
    })
  }

  private setForm(staff: Staff) {
    this.staffForm.controls.id.setValue(staff.id);
    this.staffForm.controls.name.setValue(staff.name);
    this.staffForm.controls.email.setValue(staff.email);
    this.staffForm.controls.position.setValue(staff.position);
  }

  get controls() {
    return this.staffForm.controls;
  }

  updateStaff() {
    if (!this.staffForm.valid) return;

    this._staffService.update(this.staffForm.value).subscribe(
      () => {
        this.toastrService.success("Staff successfully updated");
        this.changeEditMode();
        this.ngOnInit();
      },
      httpError =>  this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  changeEditMode() {
    this.updateMode = !this.updateMode;
    this.updateMode ? this.staffForm.enable() :this.staffForm.disable();
  }

  showConfirmDialog() {
    const modal = this.modalService.show(ModalComponent);
    (<ModalComponent>modal.content).show('Issue silmek istiyor musunuz?');
    (<ModalComponent>modal.content).onClose.subscribe(
      result => result ? this.deleteStaff() : null
    );
  }

  private deleteStaff() {
    this._issueService.delete(this.staffForm.value.id).subscribe(
      () => {
        this.toastrService.success("Staff successfully deleted");
        this.router.navigate(['/staff']);
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }
}
