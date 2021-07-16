import {Component, OnInit} from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Title } from "@angular/platform-browser";
import { BreadcrumbService, Breadcrumb } from "angular-crumbs";
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit{

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  token = null;


  constructor(private breakpointObserver: BreakpointObserver,
              private titleService: Title,
              private breadcrumbService: BreadcrumbService,
              private _authService: AuthService,
              private router: Router) {
    this.token = _authService.getJwtToken();
    console.log(_authService.getAccount())
  }

  ngOnInit(): void {
    this.breadcrumbService.breadcrumbChanged.subscribe(
      crumbs => {
        this.titleService.setTitle(this.createTitle(crumbs));
      }
    )
  }

  private createTitle(routesCollection: Breadcrumb[]){
    const title = 'Project Management';
    const titles = routesCollection.filter(route => route.displayName);

    if (!titles.length) return title;

    const routeTitle = this.titlesToString(titles);
    return `${routeTitle} ${title};`
  }

  private titlesToString(titles){
    return titles.reduce((prev, curr) => {
      return `${curr.displayName} - ${prev}`;
    }, '');
  }

  logout() {
    this._authService.logout();
    this.router.navigate(['/']).then( () => location.reload() )
  }
}
