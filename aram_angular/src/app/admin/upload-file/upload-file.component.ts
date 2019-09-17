import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as config from '../../../globalConfig';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  fileToUpload: File = null;

  uploadIns: any = {};

  constructor(public http: HttpClient) { }

  ngOnInit() {
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  submit() {

    if(!this.uploadIns.uploadType) return;
    if(this.uploadIns.uploadType == 'uploadCurrentAffairs') {
      this.uploadCurrentAffairs();
      return;
    }

    if(this.uploadIns.uploadType == 'uploadPrelimsQuiz') {
      this.uploadPrelimsQuiz();
      return;
    }

    if(this.uploadIns.uploadType == 'uploadOnlineTest') {
      this.uploadOnlineTest();
      return;
    }
  }

  uploadCurrentAffairs() {
    let formData = new FormData();
    formData.set("type", this.uploadIns.currentAffairsType);
    formData.set("heading", this.uploadIns.heading);
    formData.set("shortDescription", this.uploadIns.shortDescription);
    formData.set("file", this.fileToUpload);

    const url = config.gServiceUrl + 'uploadCurrentAffairs';
    this.http.post(url, formData).subscribe(function(successResp) {
      console.log("success resp = ", successResp);
      
    }, function(failureResp) {
      console.log("failure resp = ", failureResp);
      
    })

  }

  uploadPrelimsQuiz() {
    let formData = new FormData();
    formData.set("type", this.uploadIns.currentAffairsType);
    formData.set("quizDate", this.uploadIns.quizDate);
    formData.set("file", this.fileToUpload);

    const url = config.gServiceUrl + 'uploadPrelimsQuiz';
    this.http.post(url, formData).subscribe(function(successResp) {
      console.log("success resp = ", successResp);
      
    }, function(failureResp) {
      console.log("failure resp = ", failureResp);
      
    })
  }

  uploadOnlineTest() {
    let formData = new FormData();
    formData.set("heading", this.uploadIns.heading);
    formData.set("file", this.fileToUpload);

    const url = config.gServiceUrl + 'uploadOnlineTest';
    this.http.post(url, formData).subscribe(function(successResp) {
      console.log("success resp = ", successResp);
      
    }, function(failureResp) {
      console.log("failure resp = ", failureResp);
      
    })
  }
}
