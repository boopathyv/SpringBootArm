import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, NgForm, FormGroupDirective } from '@angular/forms';
import { Observable} from 'rxjs';
import { ErrorStateMatcher } from '@angular/material/core';

import {registrationService} from '../../services/registration.service';
import {AccountService} from '../../login.account.service';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const invalidCtrl = !!(control && control.invalid && control.parent.dirty);
    const invalidParent = !!(control && control.parent && control.parent.invalid && control.parent.dirty);

    return (invalidCtrl || invalidParent);
  }
}

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  changepassControlForm: FormGroup;
  userLoginId;
  sessionId;
  password;
  newPassword;
  rePassword;
  responseMSG;
  matcher = new MyErrorStateMatcher();
  constructor(fb: FormBuilder, 
    private changepassService:registrationService,
    private sessionUser:AccountService) { 
      this.changepassControlForm = fb.group({
        userLoginId:["", Validators.required],
        password:["", Validators.required],
        newPassword:["", Validators.required],
        rePassword:[""], 
        role:'Student',
      },{validator: this.checkPasswords});
      this.password = this.changepassControlForm.get('password');
      this.newPassword = this.changepassControlForm.get('newPassword');
      this.rePassword = this.changepassControlForm.get('rePassword');
      this.sessionId = this.sessionUser.getUserSessionId();
    }
  ngOnInit() {
  }
  checkPasswords(group: FormGroup) {
    let pass = group.controls.newPassword.value;
    let confirmPass = group.controls.rePassword.value;
    return pass === confirmPass ? null : { notSame: true }
  }
  changePassword(){
    this.changepassService.changepassRequest(this.changepassControlForm.value).subscribe((response)=>{
      this.responseMSG = response;
      if(this.responseMSG.authStatus=='SUCCESS'){
        this.responseMSG = "Password changed successfully";
      }else{
        this.responseMSG = "old password is incorrect";
      }
    });
  }

}
