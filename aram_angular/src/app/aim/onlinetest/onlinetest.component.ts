import { Component, OnInit , NgZone, Input} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';

import {QuestionService} from '../../services/question.service';
import { retryWhen } from 'rxjs/internal/operators/retryWhen';
import {AccountService} from '../../login.account.service';

@Component({
  selector: 'app-onlinetest',
  templateUrl: './onlinetest.component.html',
  styleUrls: ['./onlinetest.component.css']
})
export class OnlinetestComponent {
  questions:any;
  config: any;
  selectedAnswer=[];
  albabetsSew = ['a','b','c','d','e','f','g'];
  submitBtn = true;
  responseMsg;
  expisOn = false;
  passageCount = 0;
  attemtCount;
  testID;
  constructor(private question: QuestionService, private NgZone: NgZone, private route: ActivatedRoute, private router: Router,private sessionUser:AccountService) {
    this.config = {
          currentPage: 1,
          itemsPerPage: 10
    };
    this.route.queryParamMap.pipe(map(params => params.get('page')))
            .subscribe(page => this.config.currentPage = (page)?page:1);
   }

  ngOnInit() {
    this.testID = this.question.getSelectedTestID();
    this.question.getQuestionAll(this.testID).subscribe(
      response=> {
          this.questions = response;
      },
      error => console.log(error,"error")
    )

    setInterval(() => {
      this.submitQuiz(false);
    }, 900000);

  }

  pageChange(newPage: number) {
    this.router.navigate(['aim/onlinetest'], { queryParams: { page: newPage } }).then(()=>{
      setTimeout(() => {
          this.selectedAnswer.map(function(val,i){        
              let selectedElemet = document.querySelectorAll("input[data-attendedquestionid='"+val.attendedquestionid+"'][data-attendedAnswer='"+val.attendedanswer+"']")[0];
              if(selectedElemet){
                selectedElemet["checked"] = true;
                selectedElemet.parentElement.parentElement.className="selected"
               // selectedElemet["checked"]

              }
        
            })
      },200)
    });
  }
  
  testSubmit(){
    if(confirm("Are you sure to submit this Test ")) {
      this.submitQuiz(true);
    }
  }

  submitQuiz(auto){
  
    if(auto == true){
      this.submitBtn = false;
      this.attemtCount = parseInt(this.sessionUser.getTestCount())+1;
      var result = {
          "userloginid": this.sessionUser.getUserSessionId(),
          "testid":this.testID,
          "attemptid":(this.attemtCount)?this.attemtCount:1,
          "qalist":[]
        };

      result.qalist.push(this.selectedAnswer);
      result.qalist=result.qalist[0];

      this.question.submitQuesion(result).subscribe(
        response=> {
           this.responseMsg = response;
           if(this.responseMsg.status == 'SUCCESS'){
              this.responseMsg = this.responseMsg.t
           }
        },
        error => console.log(error,"error")
      )    
      this.expisOn = true;
      }else{
        var result = {
          "userloginid": this.sessionUser.getUserSessionId(),
          "testid":this.testID,
          "attemptid":(this.attemtCount)?this.attemtCount:1,
          "qalist":[]
        };

        result.qalist.push(this.selectedAnswer);
        result.qalist=result.qalist[0];
        this.question.submitQuesion(result).subscribe(
        response=> {
          
        },
        error => console.log(error,"error")
      )    
      }
  }

  checkedAnswer(event){
    let currentSelect= Object.assign({},event.target.dataset);
    this.pushToArrayUnq(this.selectedAnswer,currentSelect);
    this.selectedAnswer.map(function(val,i){
      let selectedElemet = document.querySelectorAll("input[data-attendedquestionid='"+val.attendedquestionid+"'][data-attendedAnswer='"+val.attendedanswer+"']")[0];
      if(selectedElemet){
        selectedElemet.parentElement.parentElement.className="selected"
      }
    })

  }
  pushToArrayUnq(arr, obj) {
    const index = arr.findIndex((e) => e.attendedquestionid === obj.attendedquestionid);
    if (index === -1) {
        arr.push(obj);
    } else {
        arr[index] = obj;
    }
    return arr;
  }

  onsubmitQuiz(e){
    this.submitQuiz("auto")
  }

}
