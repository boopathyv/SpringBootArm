import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';

import {LoginService} from '../../services/login.service';
import {AccountService} from '../../login.account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginControlForm: FormGroup;
  userLoginId;
  responseMSG;
  password;
  token:null;
  role;
  redirectUrl : string = "";
  constructor(fb: FormBuilder, 
    private loginService:LoginService,
    private accountService:AccountService,
    private router:Router, private activateRoute: ActivatedRoute) {
    this.loginControlForm = fb.group({
      userLoginId:["", Validators.required],
      password:["", Validators.required],
      role:'Student',
    });
    this.userLoginId = this.loginControlForm.get('userLoginId');
    this.password = this.loginControlForm.get('password');
   }

  ngOnInit() {
    console.log(this.activateRoute);
    let self = this;
    this.activateRoute.queryParams.subscribe(function(routeIns) {
      console.log(routeIns);
      if(Object.keys(routeIns).length > 0 && routeIns.redirectUrl) {
        self.redirectUrl = routeIns.redirectUrl;
      }
    });
    
  }
  numberOnly(event): boolean {
    this.responseMSG = '';
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }
  loginAccount(){
    let user = this.loginControlForm.value;
    console.log("login = ", user);
    
    if(user.userLoginId == "0") {
      user.userLoginId = parseInt(user.userLoginId.toString());
      user.role = "admin";
    }
    let self = this;
    this.loginService.loginCheck(this.loginControlForm.value).subscribe((response)=>{
      this.responseMSG = response;
      if(this.responseMSG.authStatus=='SUCCESS'){
          this.accountService.setToken(response);
          
          if(self.redirectUrl) {
            this.router.navigateByUrl(self.redirectUrl);
            return;
          }
          if(user.role != "admin") {
            this.router.navigateByUrl("/test-series/prelims-test");  
            return;
          }
          this.router.navigateByUrl("");
      }
    });
  }
 
}
