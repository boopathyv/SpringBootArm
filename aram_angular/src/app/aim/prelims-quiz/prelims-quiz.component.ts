import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {formatDate } from '@angular/common';
import * as $ from 'jquery';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import {NgbDateStruct,NgbModule,NgbCalendar} from '@ng-bootstrap/ng-bootstrap';

import {AimcivilService} from '../../services/aimcivil.service';
import * as config from '../../../globalConfig';
import { retryWhen } from 'rxjs/internal/operators/retryWhen';

@Component({
  selector: 'app-prelims-quiz',
  templateUrl: './prelims-quiz.component.html',
  styleUrls: ['./prelims-quiz.component.css']
})
export class PrelimsQuizComponent implements OnInit {
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
  pdfFilePath = config.pdfFileURL;
  minDate = '';
  maxDate = '';
  albabetsSew = ['a','b','c','d','e','f','g'];
  serviceUrl=config.gServiceUrl+"getPrelimsQuiz";
  nagativeMarks = config.nagavtiveMarks;
  correctAnsMarks = config.correctAnsMarks;
  unAnsweredQtns = 0;
  correctAnswers = 0;
  wrongAnswers = 0;
  numberofQtns;
  selectedAnswer=[];
  totalMarks;
  finalScore;
  answerdQtns;
  unAttendedQtns;
  expisOn = false;
  submitBtn = true;
  constructor(private AimcivilService:AimcivilService, private calendar: NgbCalendar) { 
    this.minDate = formatDate(this.tdate,'yyyy-M-dd', 'en-US', '+0530');
    this.maxDate = formatDate(this.tdate.setDate(this.tdate.getDate() + 3),'yyyy-M-dd', 'en-US', '+0530');
    this.datemodel = this.calendar.getToday();
  }
  ngOnInit() {
      this.getprelimsQuizContent(this.minDate, this.maxDate);
  }
  ondateChange(event){
    this.expisOn = false;
    if(event){
      this.minDate = event.year+'-'+event.month+'-'+event.day;
      let nwdate = new Date(this.minDate);
      this.maxDate = formatDate(nwdate.setDate(nwdate.getDate() + 3),'yyyy-M-dd', 'en-US', '+0530');
      this.getprelimsQuizContent(this.minDate, this.maxDate);
    }
  }
  getprelimsQuizContent(miDate,mxDate){
      let reqURL = this.serviceUrl+'?minDate='+miDate+'&maxDate='+mxDate;
      this.AimcivilService.prelimsCurrentAffairs(reqURL).subscribe((res)=>{
        if(res.status==200){
          this.prelimsData = res.body;
          this.numberofQtns = this.prelimsData.length;
          this.noRecords = false;
        }else{
          this.prelimsData = '';
          this.noRecords = true;
        }
      });
  }

  checkedAnswer(event){
    let currentSelect = Object.assign({},event.target.dataset);
    this.pushToArrayUnq(this.selectedAnswer,currentSelect)
  }
  
  submitPrelimzQuiz(){
    if(confirm("Are you sure to submit this prelims quiz ")) {
        this.totalMarks = this.numberofQtns * this.correctAnsMarks;
        this.answerdQtns = this.selectedAnswer.length;
        this.unAttendedQtns = this.numberofQtns - this.answerdQtns;
        for(var k=0; k<this.answerdQtns; k++){
          let qnum = this.selectedAnswer[k].qids-1;
          if(this.prelimsData[qnum].answer == this.selectedAnswer[k].answered){
            this.correctAnswers++;
            $('input[name=prelims-qus-'+this.selectedAnswer[k].qids+']:checked').parent().append(' <i class="fa fa-check"></i>');
          }else{
            this.wrongAnswers++;
            $('input[name=prelims-qus-'+this.selectedAnswer[k].qids+']:checked').parent().append(' <i class="fa fa-close"></i>');
          }
        }
        let earnedMarks = this.correctAnswers * this.correctAnsMarks;
        let nagMarks = this.wrongAnswers * this.nagativeMarks;
        this.finalScore = earnedMarks - nagMarks;
        this.expisOn = true;
        this.submitBtn = false;
    }
  }
  
  pushToArrayUnq(arr, obj) {
    const index = arr.findIndex((e) => e.qids === obj.qids);
    if (index === -1) {
        arr.push(obj);
    } else {
        arr[index] = obj;
    }
    return arr;
  }
}
