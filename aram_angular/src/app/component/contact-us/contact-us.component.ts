import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import { Observable} from 'rxjs';

import {InquiryService} from '../../services/inquiry.service';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent implements OnInit {
  contactControlForm: FormGroup;
  userName;
  emailId;
  mobileNumber;
  message;
  strinfydata = [];
  constructor(fb: FormBuilder, 
    private inquiryservice:InquiryService,
    private router:Router) {
      this.contactControlForm = fb.group({
        userName:["", Validators.required],
        emailId:["", [Validators.required, Validators.email]],
        mobileNumber:["", [Validators.required, Validators.pattern(/^d{10}$/)]],
        message:["", Validators.required],
      });
      this.userName = this.contactControlForm.get('userName');
      this.emailId = this.contactControlForm.get('emailId');
      this.mobileNumber = this.contactControlForm.get('mobileNumber');
      this.message = this.contactControlForm.get('inquiryMsg');
    }

  ngOnInit() {
  }
  joinUs(){
      //this.strinfydata = JSON.stringify({'emailId':this.userEmail,'mobileNumber':this.mobileNumber,'userName':this.userName,'message':this.inquiryMsg});
      /*this.strinfydata['emailId']=this.userEmail;
      this.strinfydata['userName']=this.userName;
      this.strinfydata['mobileNumber']=this.mobileNumber;
      this.strinfydata['message']=this.inquiryMsg; */
      this.inquiryservice.submitInquiry(JSON.stringify(this.contactControlForm.value)).subscribe(
        (response)=>{
          this.contactControlForm.reset();
        });
  }

}
