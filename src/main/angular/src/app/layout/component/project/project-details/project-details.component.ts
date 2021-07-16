import {Component, OnInit, TemplateRef} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProjectService} from "../../../../service/project.service";
import {ToastrService} from "ngx-toastr";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StaffService} from "../../../../service/staff.service";
import {Project} from "../../../../model/project";
import {Staff} from "../../../../model/staff";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {ColumnMode} from "@swimlane/ngx-datatable";
import {Issue} from "../../../../model/issue";
import {IssueService} from "../../../../service/issue.service";
import {Pageable} from "../../../../model/pageable";

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  project: Project;
  managerOptions: Staff[];
  issueList: Issue[];
  pageable = new Pageable(5);

  projectForm: FormGroup;
  updateMode = false;

  issueForm: FormGroup;
  issueStatusList: [];
  minDate: Date;
  maxDate: Date;
  modalRef: BsModalRef;

  columnMode = ColumnMode;

  constructor(private activatedRoute: ActivatedRoute,
              private toastrService: ToastrService,
              private formBuilder: FormBuilder,
              private modalService: BsModalService,
              private _projectService: ProjectService,
              private _staffService: StaffService,
              private _issueService: IssueService) {
    this.minDate = new Date();
    this.maxDate = new Date();
    this.minDate.setDate(this.minDate.getDate() + 1);
    this.maxDate.setDate(this.maxDate.getDate() + 30);
  }

  ngOnInit(): void {
    this.getProject();
    this.getAllManagerWithoutProject();
    this.initProjectForm();
    this.getIssueList({offset: 0})
    this.getIssueStatusList();
    this.initIssueForm();
  }

  private getProjectIdFromUrl() {
    return +this.activatedRoute.snapshot.paramMap.get('id');
  }


  private getProject() {
    const id = this.getProjectIdFromUrl();
    this._projectService.get(id).subscribe(
      response => {
        this.project = response;
        this.setForm(response);
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private getAllManagerWithoutProject() {
    this._staffService.getAllManagerWithoutProject().subscribe(
      response => this.managerOptions = response,
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  getIssueList(event){
    this.pageable.page = event.offset;
    const id: number = this.getProjectIdFromUrl();
    this._issueService.getByProjectId(id, this.pageable).subscribe(
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

  updateProject() {
    if (!this.projectForm.valid) return;

    this._projectService.update(this.projectForm.value).subscribe(
      () => {
        this.toastrService.success("Project successfully updated");
        this.changeEditMode();
        this.ngOnInit();
      },
      httpError =>  this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private initProjectForm() {
    this.projectForm = this.formBuilder.group({
      id: [null],
      code: [{value: null, disabled: !this.updateMode}, Validators.required],
      description: [{value: null, disabled: !this.updateMode}, Validators.required],
      details: [{value: null, disabled: !this.updateMode}, Validators.required],
      active: [{value: null, disabled: !this.updateMode}, Validators.required],
      manager_id: [{value: null, disabled: !this.updateMode}, Validators.required]
    });
  }

  get pfControls() {
    return this.projectForm.controls;
  }

  private setForm(project: Project) {
    this.projectForm.controls.id.setValue(project.id);
    this.projectForm.controls.code.setValue(project.code);
    this.projectForm.controls.description.setValue(project.description);
    this.projectForm.controls.details.setValue(project.details);
    this.projectForm.controls.active.setValue(project.active);
    this.projectForm.controls.manager_id.setValue(project.manager_id);
  }

  changeEditMode() {
    this.updateMode = !this.updateMode;
    this.updateMode ? this.projectForm.enable() :this.projectForm.disable();
  }

  getColorClass() {
    return {'text-dark' : !this.updateMode,
      'text-danger' : this.updateMode}
  }

  /*showConfirmDialog() {
    const modal = this.modalService.show(ModalComponent);
    (<ModalComponent>modal.content).show('Projeyi silmek istiyor musunuz?');
    (<ModalComponent>modal.content).onClose.subscribe(
      result => result ? this.deleteProject() : null
    );
  }

  private deleteProject() {
    console.log(this.projectForm.value.id);
  }*/

  private initIssueForm() {
    this.issueForm = this.formBuilder.group({
      code: [null, [Validators.required, Validators.maxLength(6)]],
      description: [null, [Validators.required, Validators.maxLength(150)]],
      details: [null, Validators.required],
      due_date: [null, Validators.required],
      status: [null],
      project_id: [null],
    })
  }

  private getIssueStatusList() {
    this._issueService.getIssueStatusList().subscribe(
      response => this.issueStatusList = response,
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  get ifControls() {
    return this.projectForm.controls;
  }

  openModal(templateRef: TemplateRef<any>) {
    this.modalRef = this.modalService.show(templateRef);
  }

  closeModal(){
    this.modalRef.hide();
  }

  createIssue() {
    if (!this.issueForm.valid) return;

    this.issueForm.controls.project_id.setValue(this.project.id);

    this._issueService.save(this.issueForm.value).subscribe(
      () => {
        this.closeModal();
        this.toastrService.success("Issue successfully added");
        this.ngOnInit();
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }
}
