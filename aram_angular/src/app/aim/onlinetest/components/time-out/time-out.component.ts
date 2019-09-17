import { Component, OnInit, EventEmitter, Input, Output  } from '@angular/core';

import {timer, Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent} from 'rxjs';
import { map, filter, scan } from 'rxjs/operators';
@Component({
  selector: 'app-time-out',
  templateUrl: './time-out.component.html',
  styleUrls: ['./time-out.component.css']
})



export class TimeOutComponent implements OnInit {
 
  count;
  diff;
  todayDate = new Date();
  $counter;
  subscription:any;
  @Output() submitQuiz = new EventEmitter<boolean>();
  constructor() { }

  ngOnInit() {
    let todayDate = new Date();
    let countDownDate = new Date(todayDate.setHours( todayDate.getHours() + 2 )).getTime();

    this.subscription = timer(1000, 2000).subscribe(val =>{
          let now = new Date().getTime();
          let distance = countDownDate - now;
          let tim="";
          let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
          let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
          let seconds = Math.floor((distance % (1000 * 60)) / 1000);
          tim= (hours< 10 ? '0'+hours : hours) + " : " + (minutes< 10 ? '0'+minutes : minutes) + " : " + (seconds< 10 ? '0'+seconds : seconds)
          if (distance < 0) {
            tim= "EXPIRED";
            this.submitQuiz.emit();
            this.subscription.unsubscribe();
          }
          tim = (hours< 10 ? '0'+hours : hours) + " : " + (minutes< 10 ? '0'+minutes : minutes) + " : " + (seconds< 10 ? '0'+seconds : seconds);
          this.count= tim;
    });
  }

  counterTime(){
   
  }

 
}