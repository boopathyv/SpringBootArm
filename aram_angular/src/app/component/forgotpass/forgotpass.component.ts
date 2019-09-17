import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Observable} from 'rxjs';

import {Router} from '@angular/router';
import {registrationService} from '../../services/registration.service';

@Component({
  selector: 'app-forgotpass',
  templateUrl: './forgotpass.component.html',
  styleUrls: ['./forgotpass.component.css']
})
export class ForgotpassComponent implements OnInit {
  forgotpassControlForm: FormGroup;
  responseMSG;
  userLoginId;
  mobileNumber;
  constructor(fb: FormBuilder, 
    private forgtpassService:registrationService,
    private router:Router) { 
      this.forgotpassControlForm = fb.group({
        userLoginId:["", Validators.required],
        mobileNumber:["",Validators.required],
        role:'Student',
      });
      this.userLoginId = this.forgotpassControlForm.get('userLoginId');
      this.mobileNumber = this.forgotpassControlForm.get('mobileNumber');
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
  forgotPassword(){
    this.forgtpassService.passwordRequest(this.forgotpassControlForm.value).subscribe((response)=>{
      this.responseMSG = response;
      //this.router.navigateByUrl("/about-upsc");
    });
    
  }
}
