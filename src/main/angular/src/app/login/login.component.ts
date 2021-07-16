import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../auth/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  returnUrl: string;
  err = '';
  hide = true;


  constructor(private formBuilder: FormBuilder,
              private _authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private toastrService: ToastrService) {
  }

  ngOnInit() {
    this.initForm();
    this._authService.logout();
    this.returnUrl = this.activatedRoute.snapshot.queryParams['returnUrl'] || '/';
  }

  login() {
    if (this.loginForm.invalid) return;

    this._authService.login(this.loginForm.value).subscribe(
      response => {
        if (response) this.router.navigateByUrl("/projects")
      },
      httpError => this.toastrService.error(httpError.error.message, httpError.status)
    )
  }

  get controls() {
    return this.loginForm.controls;
  }

  private initForm() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
}
