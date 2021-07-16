import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProjectService} from "../../../../service/project.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {StaffService} from "../../../../service/staff.service";
import {ToastrService} from "ngx-toastr";
import {Project} from "../../../../model/project";
import {Staff} from "../../../../model/staff";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects: Project[];
  managerOptions: Staff[];

  modalRef: BsModalRef;
  projectForm: FormGroup;

  @ViewChild('searchInput') searchBox;

  constructor(private _projectService: ProjectService,
              private _staffService: StaffService,
              private modalService: BsModalService,
              private formBuilder: FormBuilder,
              private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.getAllProjects();
    this.getManagers();
    this.initForm();
  }

  private getAllProjects(){
    this._projectService.getAllWithManager().subscribe(
      response => this.projects = response,
      (httpError) => this.toastrService.error(httpError.error.message, httpError.status)
    );
  }

  searchProject(keyword: string) {
    if(keyword != null && keyword.trim() != '')
      this._projectService.search(keyword).subscribe(
        response => this.projects = response,
        httpError => this.toastrService.error(httpError.error.message, httpError.status)
      )
  }

  private getManagers(){
    this._staffService.getAllManagerWithoutProject().subscribe(
      response => this.managerOptions = response,
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  createProject(){
    if (!this.projectForm.valid) return;

    this._projectService.save(this.projectForm.value).subscribe(
      () => {
        this.closeModal();
        this.toastrService.success("Project successfully added");
        this.ngOnInit();
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  private initForm(){
    this.projectForm = this.formBuilder.group({
      code: [null, [Validators.required, Validators.maxLength(6)]],
      description: [null, [Validators.required, Validators.maxLength(150)]],
      details: [null, Validators.required],
      manager_id: [null, Validators.required],
    });
  }

  get controls() { return this.projectForm.controls; }

  openModal(template: TemplateRef<any>){
    this.modalRef = this.modalService.show(template);
  }

  closeModal(){
    this.projectForm.reset();
    this.modalRef.hide();
  }

  clearSearch() {
    this.ngOnInit();
    this.searchBox.nativeElement.value = '';
  }
}
