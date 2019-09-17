import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Observable} from 'rxjs';


import {Router} from '@angular/router';
import {registrationService} from '../../services/registration.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerControlForm: FormGroup;
  userLoginId;
  mobileNumber;
  idval;
  responseMSG;

  user: any = {};

  userID: string = "";
  otp: string = "";
  signupForm: FormGroup;

  isLoading: boolean = false;

  constructor(fb: FormBuilder, 
    private registrationService:registrationService,
    private router:Router) { 
      this.registerControlForm = fb.group({
        userLoginId:["", Validators.required],
        mobileNumber:['', Validators.required],
        role:'Student',
      });
      this.userLoginId = this.registerControlForm.get('userLoginId');
      this.mobileNumber = this.registerControlForm.get('mobileNumber');

      let self = this;
      this.signupForm = fb.group({
        studentName: [self.user.studentName, [Validators.required]],
        emailId: [self.user.emailId, [Validators.required, Validators.email]],
        mobileNumber: [self.user.mobileNumber, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
        password: [self.user.password, [Validators.required]],
        confirmPassword: [self.user.confirmPassword, [Validators.required]],
        registeredWithUPSC: [self.user.registeredWithUPSC, [Validators.required]],
        rollNoUPSC: [self.user.rollNoUPSC, [self.user.registeredWithUPSC ? Validators.required : Validators.nullValidator]]
      });

      this.userID = "";
    }

  ngOnInit() {
  }
  numberOnly(event): boolean {
    this.responseMSG = '';
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }
  creatingAccount(){
  /* let formData: FormData = new FormData();
   this.idval = parseInt(this.id);
    formData.append('id', this.idval);
    formData.append('role','Student'); */
    this.registrationService.createAccount(this.registerControlForm.value).subscribe((response)=>{
      this.responseMSG = response;
      //this.router.navigateByUrl("/about-upsc");
    });
  }

  signup() {
    console.log("signup = ", this.user);
    let self = this;
    this.isLoading = true;
    this.registrationService.signup(this.user).subscribe(function(successResp) {
      console.log("signup success resp = ", successResp);
      self.userID = successResp["userID"];
      self.isLoading = false;
      
    }, function(failureResp) {
      console.log("signup failure resp = ", failureResp);
      self.isLoading = false;
      if(failureResp.status == 400) {
        self.responseMSG = {message: failureResp.error.error};
      }
    });
  }

  resendOTP() {
    console.log("resend OTP");
    this.isLoading = true;
    let self = this;
    this.registrationService.resendOTP(this.userID).subscribe(function(successResp) {
      console.log("signup success resp = ", successResp);
      alert("New OTP has been sent to your email ID");
      self.isLoading = false;
      
    }, function(failureResp) {
      console.log("signup failure resp = ", failureResp);
      self.isLoading = false;
      if(failureResp.status == 400) {
        alert(failureResp.error.error);
      }
    });
    
  }

  verifyOTP() {
    console.log("verify OTP");
    this.isLoading = true;
    const reqData = {
      userID: this.userID,
      otp: this.otp
    }
    let self = this;
    this.registrationService.verifyOTP(reqData).subscribe(function(successResp) {
      console.log("signup success resp = ", successResp);
      alert("Your User ID has been sent to your email address. Kindly use that ID to login");
      self.isLoading = false;
      self.router.navigate(['/login']);
      
    }, function(failureResp) {
      console.log("signup failure resp = ", failureResp);
      self.isLoading = false;
      if(failureResp.status == 400) {
        alert(failureResp.error.error);

      }
    });
  
  }

}
