import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {formatDate } from '@angular/common';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import {NgbDateStruct,NgbModule,NgbCalendar} from '@ng-bootstrap/ng-bootstrap';

import {AimcivilService} from '../../services/aimcivil.service';
import * as config from '../../../globalConfig';

@Component({
  selector: 'app-mains-qa',
  templateUrl: './mains-qa.component.html',
  styleUrls: ['./mains-qa.component.css']
})
export class MainsQaComponent implements OnInit {
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
  pdfFilePath = config.gServiceUrl + 'getPdf';
  minDate = '';
  maxDate = '';
  exceptionMessage = '';
  serviceUrl=config.gServiceUrl+"getMainsQuiz";
  constructor(private AimcivilService:AimcivilService,private calendar: NgbCalendar) { 
    this.minDate = formatDate(this.tdate,'yyyy-M-dd', 'en-US', '+0530');
    this.maxDate = formatDate(this.tdate.setDate(this.tdate.getDate() + 3),'yyyy-M-dd', 'en-US', '+0530');
    this.datemodel = this.calendar.getToday();
  }
  ngOnInit() {
    this.getmainsqaContent(this.minDate, this.maxDate);
  }
  ondateChange(event){
    if(event){
      this.minDate = event.year+'-'+event.month+'-'+event.day;
      let nwdate = new Date(this.minDate);
      this.maxDate = formatDate(nwdate.setDate(nwdate.getDate() + 3),'yyyy-M-dd', 'en-US', '+0530');
      this.getmainsqaContent(this.minDate, this.maxDate);
    }
  }
  getmainsqaContent(miDate,mxDate){
    let reqURL = this.serviceUrl+'?minDate='+miDate+'&maxDate='+mxDate;
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
