import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as config from '../../../globalConfig';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-answers',
  templateUrl: './student-answers.component.html',
  styleUrls: ['./student-answers.component.css']
})
export class StudentAnswersComponent implements OnInit {

  answers: any[] = [];

  fileToUpload: File = null;

  testQuestionId : string = "";

  serviceUrl : string = config.gServiceUrl;
  constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    console.log(this.activatedRoute.params);
    let self = this;
    this.activatedRoute.params.subscribe(function(resp) {
      console.log(resp.id);
      if(resp.id) {
        self.testQuestionId = resp.id;
        self.getAnswers(resp.id)
      }
      
    });
  }

  getAnswers(id: string) {
    let self = this;
    const reqUrl = this.serviceUrl + "testQuestion/" + id + "/answers";
    this.http.get(reqUrl).subscribe(function(successResp) {
      console.log(successResp);
      self.answers = (successResp['studentAnswers'] as any[]) || [];
    }, function(failureResp){
      console.log(failureResp);
      
    })
  }

  getDateFormat(date) : string {

    let dateIns = new Date(date);
    let dateFormat = dateIns.getDate().toString() + "-" + (dateIns.getMonth() + 1).toString() + "-" + dateIns.getFullYear() ;
    return dateFormat;
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  uploadFeedback(studentAnswerId) {
    let formData = new FormData();
    // formData.set("type", this.uploadIns.currentAffairsType);
    formData.set("feedback", "");
    formData.set("file", this.fileToUpload);

    let self = this;
    const url = config.gServiceUrl + 'uploadFeedback/' + studentAnswerId;
    this.http.put(url, formData).subscribe(function(successResp) {
      console.log("success resp = ", successResp);
      self.getAnswers(self.testQuestionId);
      
    }, function(failureResp) {
      console.log("failure resp = ", failureResp);
      
    })
  }

}
