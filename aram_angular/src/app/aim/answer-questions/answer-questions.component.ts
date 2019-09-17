import { Component, OnInit } from '@angular/core';
import * as config from '../../../globalConfig';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-answer-questions',
  templateUrl: './answer-questions.component.html',
  styleUrls: ['./answer-questions.component.css']
})
export class AnswerQuestionsComponent implements OnInit {

  fileToUpload: File = null;

  serviceUrl: string = config.gServiceUrl;
  testQuestions: any[] = [];

  testQuestion: any = {};

  todayStr = this.getTodayDateFormatForMin();

  studentAnswers: any[] = [];

  lastAnsweredSeqNo: number = 0;

  testQuestionType: string = "Governance";

  isAdmin: boolean = localStorage.getItem("ROLE") && localStorage.getItem("ROLE").toLowerCase() == "admin" ? true : false;
  uid = localStorage.getItem("UID");

  constructor(private http: HttpClient, private route: Router) { }

  ngOnInit() {
    this.getTestQuestions();
    this.getStudentAnswers();
  }

  getTestQuestions() {

    let self = this;
    const reqUrl = this.serviceUrl + "testQuestions" + "?questionType=" + this.testQuestionType;
    this.http.get(reqUrl).subscribe(function (successResp) {
      console.log(successResp);
      self.testQuestions = (successResp['testQuestions'] as any[]) || [];
    }, function (failureResp) {
      console.log(failureResp);

    })
  }

  getStudentAnswers() {

    if (this.isAdmin) return;
    let self = this;
    const reqUrl = this.serviceUrl + "testQuestions/" + this.uid + "?questionType=" + this.testQuestionType;
    this.http.get(reqUrl).subscribe(function (successResp) {
      console.log(successResp);
      self.studentAnswers = (successResp['testQuestions'] as any[]) || [];
      for (let sa of self.studentAnswers) {
        if (sa.testQuestion.sequenceNo > self.lastAnsweredSeqNo) {
          self.lastAnsweredSeqNo = sa.testQuestion.sequenceNo;
        }
      }
    }, function (failureResp) {
      console.log(failureResp);

    })
  }

  getAnswerForTest(testQuestionId): any {
    let studentAnswer = null;    
    for (let sa of this.studentAnswers) {
      if (sa.testQuestion.testQuestionId == testQuestionId) {
        studentAnswer = sa;
        break;
      }
    }
    return studentAnswer;
  }

  getDateFormat(date): string {

    let dateIns = new Date(date);
    let dateFormat = dateIns.getDate().toString() + "-" + (dateIns.getMonth() + 1).toString() + "-" + dateIns.getFullYear();
    return dateFormat;
  }

  getTodayDateFormatForMin() {
    let dateIns = new Date();
    let dateFormat = dateIns.getFullYear().toString() + "-" + (dateIns.getMonth() + 1).toString() + "-" + dateIns.getDate();
    return dateFormat;

  }

  goToAnswersList(testQuestionId) {
    this.route.navigate(['aim', 'test-question', testQuestionId, 'answers'])
  }

  handleFileInput(files: FileList) {
    console.log(files);
    const fileIns = files.item(0);
    if(fileIns.size/1024/1024 > 700) {
      alert('Kindly upload the file less than 700 MB');
      return;
    }
    
    this.fileToUpload = files.item(0);
  }

  checkPreviousQuestionIsAnswered(testQuestionId): boolean {
    let testQuestions = this.testQuestions.sort(function (obj1, obj2) {
      return obj1.sequenceNo - obj2.sequenceNo;
    });
    for (let i = 0; i < testQuestions.length; i++) {
      const ta = testQuestions[i];
      if (ta.testQuestionId == testQuestionId) {
        if (i == 0) return true;
        const previousId = testQuestions[i - 1].testQuestionId;
        console.log(previousId);
        
        const sa = this.getAnswerForTest(previousId);
        if (!sa) {
          alert('Kindly upload previous question answer first which has sequence number of ' + testQuestions[i - 1].sequenceNo);
          return false;
        }
      }
    }

    return true;
  }
  uploadAnswer(testQuestionId) {
    if(this.fileToUpload.size/1024/1024 > 700) {
      alert('Kindly upload the file less than 700 MB');
      return;
    }

    let formData = new FormData();
    // formData.set("type", this.uploadIns.currentAffairsType);
    // formData.set("quizDate", this.uploadIns.quizDate);
    formData.set("file", this.fileToUpload);

    let self = this;
    let url = config.gServiceUrl + 'uploadAnswer/' + testQuestionId;
    let apicall = this.http.put(url, formData);
    if (!this.isAdmin) {
      if (!this.checkPreviousQuestionIsAnswered(testQuestionId)) {
        return;
      }
      url = config.gServiceUrl + 'student/' + this.uid + "/answer/" + testQuestionId;
      apicall = this.http.post(url, formData);
    }
    apicall.subscribe(function (successResp) {
      console.log("success resp = ", successResp);
      self.getTestQuestions();
      self.getStudentAnswers();

    }, function (failureResp) {
      console.log("failure resp = ", failureResp);
      if (failureResp.status == 400 && failureResp.error) {
        alert(failureResp.error.error);
      }

    })
  }

  uploadDiscussion(testQuestionId: string) {
    if(this.fileToUpload.size/1024/1024 > 700) {
      alert('Kindly upload the file less than 700 MB');
      return;
    }
    let formData = new FormData();
    // formData.set("type", this.uploadIns.currentAffairsType);
    // formData.set("quizDate", this.uploadIns.quizDate);
    formData.set("file", this.fileToUpload);

    let self = this;
    let url = config.gServiceUrl + 'uploadDiscussion/' + testQuestionId;
    let apicall = this.http.put(url, formData);
    apicall.subscribe(function (successResp) {
      console.log("success resp = ", successResp);
      self.getTestQuestions();
      self.getStudentAnswers();

    }, function (failureResp) {
      console.log("failure resp = ", failureResp);
      if (failureResp.status == 400 && failureResp.error) {
        alert(failureResp.error.error);
      }

    })
  }

  createTestQuestion() {
    console.log(this.testQuestion);
    let date = new Date(this.testQuestion.testDate.toString());
    this.testQuestion.testDateStr = date.getDate().toString() + "-" + (date.getMonth() + 1).toString() + "-" + date.getFullYear();
    delete this.testQuestion.testDate;
    this.testQuestion.testCreatedById = 0;

    this.testQuestion.questionType = this.testQuestionType;

    let formData = new FormData();
    formData.set("file", this.fileToUpload);
    formData.set("testQuestion", JSON.stringify(this.testQuestion));

    const url = config.gServiceUrl + 'uploadTestQuestion';
    let self = this;
    this.http.post(url, formData, {}).subscribe(function (successResp) {
      console.log(successResp);
      self.getTestQuestions();
      document.getElementById("test-question-cancel").click();

    }, function (failureResp) {
      console.log("failure resp = ", failureResp);
      if (failureResp.status == 400 && failureResp.error) {
        alert(failureResp.error.error);
      }

    });
  }

  changeQuestionType(questionType: string) {
    this.testQuestionType = questionType;
    this.getTestQuestions();
  }
}
