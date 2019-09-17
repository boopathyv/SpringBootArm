import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {formatDate } from '@angular/common';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import {NgbDateStruct,NgbModule,NgbCalendar} from '@ng-bootstrap/ng-bootstrap';

import {AimcivilService} from '../../services/aimcivil.service';
import * as config from '../../../globalConfig';
@Component({
  selector: 'app-fortnight-compilations',
  templateUrl: './fortnight-compilations.component.html',
  styleUrls: ['./fortnight-compilations.component.css']
})
export class FortnightCompilationsComponent implements OnInit {
  datemodel: NgbDateStruct;
  tdate = new Date();
  calendarMin = {  
    'year' : 2018,
    'month' : 11,
    'day' : 1,
  };
  calendarMax = {  
    'year' : this.tdate.getFullYear(),
    'month' : this.tdate.getMonth()+1,
    'day' : this.tdate.getDate(),
  };
  prelimsData;  
  noRecords;
  pdfFilePath = config.gServiceUrl + "getPdf";
  maxDate = '';
  serviceUrl=config.gServiceUrl+"getFortnight";
  constructor(private AimcivilService:AimcivilService,private calendar: NgbCalendar) { 
    this.datemodel = this.calendar.getToday();
    this.maxDate = formatDate(this.tdate,'yyyy-M-dd', 'en-US', '+0530');
  }
  ngOnInit() {
      this.getfortnightContent(this.maxDate);
  }
  ondateChange(event){
    if(event){
      let month = event.month.toString();
      if(event.month < 10) {
        month = "0" + event.month.toString();
      }

      let day = event.day.toString();
      if(event.day < 10) {
        day = "0" + event.day.toString();
      }
      this.maxDate = event.year+'-'+month+'-'+day;
      this.getfortnightContent(this.maxDate);
    }
  }
  getfortnightContent(mxDate){
    console.log(mxDate);
      let reqURL = this.serviceUrl+'?maxDate='+mxDate;
      this.AimcivilService.prelimsCurrentAffairs(reqURL).subscribe((res)=>{
        if(res.status==200){
          this.prelimsData = res.body;
          this.noRecords = false;
        }else{
          this.prelimsData = '';
          this.noRecords = true;
        }
      });
  }


}
