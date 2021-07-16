import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LayoutComponent} from "./layout.component";
import {IssueListComponent} from "./component/issue/issue-list/issue-list.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";
import {ProjectComponent} from "./component/project/project.component";
import {ProjectListComponent} from "./component/project/project-list/project-list.component";
import {ProjectDetailsComponent} from "./component/project/project-details/project-details.component";
import {StaffListComponent} from "./component/staff/staff-list/staff-list.component";
import {IssueComponent} from "./component/issue/issue.component";
import {IssueDetailsComponent} from "./component/issue/issue-details/issue-details.component";
import {StaffComponent} from "./component/staff/staff.component";
import {StaffDetailsComponent} from "./component/staff/staff-details/staff-details.component";
import {AuthGuard} from "../auth/auth.guard";

const routes: Routes = [
  {path: '',
   component: LayoutComponent, canActivate: [AuthGuard],
   children: [
     {path: '', redirectTo: "dashboard", pathMatch: 'full'},
     {path: 'dashboard', component: DashboardComponent, data: { breadcrumb: 'Dashboard'}},
     {path: 'projects', component: ProjectComponent, data: { breadcrumb: 'Projects' },
     children: [
       {path: '', component: ProjectListComponent},
       {path: ':id', component: ProjectDetailsComponent, data: { breadcrumb: 'Details' }}
     ]},
     {path: 'issues', component: IssueComponent, data: { breadcrumb: 'Issues' },
     children: [
       {path: '', component: IssueListComponent},
       {path: 'search', component: IssueListComponent, data: { breadcrumb: 'Search' }},
       {path: 'filter', component: IssueListComponent, data: { breadcrumb: 'Filter' }},
       {path: ':id', component: IssueDetailsComponent, data: { breadcrumb: 'Details' }}
     ]},
     {path: 'staff', component: StaffComponent, data: { breadcrumb: 'Staff' },
     children: [
       {path: '', component: StaffListComponent},
       {path: 'search', component: StaffListComponent, data: { breadcrumb: 'Search' }},
       {path: ':id', component: StaffDetailsComponent, data: { breadcrumb: 'Details' }}
     ]}
   ]}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class LayoutRoutingModule { }
