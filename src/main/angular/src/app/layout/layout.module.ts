import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LayoutRoutingModule} from "./layout.routing.module";
import {LayoutComponent} from "./layout.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";
import {ProjectComponent} from "./component/project/project.component";
import {IssueListComponent} from "./component/issue/issue-list/issue-list.component";
import {ProjectDetailsComponent} from './component/project/project-details/project-details.component';
import {ProjectListComponent} from './component/project/project-list/project-list.component';
import {StaffListComponent} from './component/staff/staff-list/staff-list.component';
import {IssueDetailsComponent} from './component/issue/issue-details/issue-details.component';
import {IssueComponent} from "./component/issue/issue.component";
import {StaffComponent} from './component/staff/staff.component';
import {StaffDetailsComponent} from './component/staff/staff-details/staff-details.component';
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    LayoutComponent,
    DashboardComponent,
    ProjectComponent,
    ProjectListComponent,
    ProjectDetailsComponent,
    IssueComponent,
    IssueListComponent,
    IssueDetailsComponent,
    StaffComponent,
    StaffListComponent,
    StaffDetailsComponent,
  ],
    imports: [
        CommonModule,
        LayoutRoutingModule,
        SharedModule,
    ],
  providers: [

  ]
})
export class LayoutModule { }
