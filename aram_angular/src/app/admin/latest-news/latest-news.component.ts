import { Component, OnInit } from '@angular/core';

import * as config from '../../../globalConfig';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
@Component({
  selector: 'app-latest-news',
  templateUrl: './latest-news.component.html',
  styleUrls: ['./latest-news.component.css']
})
export class LatestNewsComponent implements OnInit {

  latestNews: any[] = [];

  latestNewsIns : any = {};
  showForm: boolean = false;

  latestNewsForm: FormGroup;
  constructor(private http: HttpClient, private fb: FormBuilder) { }

  ngOnInit() {
    this.latestNewsForm = this.fb.group({
      link: ["", [Validators.required]],
      news: ["", [Validators.required]]
    })
    this.getActiveLatestNews();
  }

  getActiveLatestNews() {
    const url = config.gServiceUrl+'latestNews/getAll';

    let $this = this;

    this.http.get(url).subscribe(function(successResp) {
      console.log(successResp);
      $this.latestNews = successResp as any[];

    }, function(failureResp) {

    })
  }

  edit(latestNewsIns: any) {
    console.log(latestNewsIns);
    
    this.latestNewsIns = JSON.parse(JSON.stringify(latestNewsIns));
    this.showForm = true;
  }

  addNewNews() {
    this.latestNewsIns = {};
    this.showForm = true;
  }

  cancel() {
    this.showForm = false;
  }

  submit() {
    
    console.log(this.latestNewsIns);
    
    if(this.latestNewsIns.latestNewsId) {
      this.updateLatestNews(this.latestNewsIns);
      return
    }
    const url = config.gServiceUrl + 'latestNews/save';

    this.latestNewsIns.activeFlag = 'Y';
    console.log(this.latestNewsIns);

    let $this = this;
    this.http.post(url, this.latestNewsIns).subscribe(function(successResp) {
      console.log(successResp);
      $this.getActiveLatestNews();
      $this.showForm = false;
      
    }, function(failureResp) {
      console.log("failure Resp = ", failureResp);
      
    })

  }

  updateLatestNews(latestNews) {
    const url = config.gServiceUrl + 'latestNews/update';

    let $this = this;
    this.http.put(url, latestNews).subscribe(function(successResp) {
      console.log(successResp);
      $this.getActiveLatestNews();
      $this.showForm = false;

    }, function(failureResp) {
      console.log("failure Resp = ", failureResp);
      
    });
  }

  inactivate(id) {
    console.log("inactivate");
    
    const url = config.gServiceUrl + 'latestNews/inActivateLatestNews/' + id;

    let $this = this;
    this.http.put(url, {}).subscribe(function(successResp) {
      console.log(successResp);
      $this.getActiveLatestNews();
      $this.showForm = false;

    }, function(failureResp) {
      console.log("failure Resp = ", failureResp);
      
    })
  }

  activate(id) {
    console.log("activate");
    
    const url = config.gServiceUrl + 'latestNews/activateLatestNews/' + id;

    let $this = this;
    this.http.put(url, {}).subscribe(function(successResp) {
      console.log(successResp);
      $this.getActiveLatestNews();
      $this.showForm = false;

    }, function(failureResp) {
      console.log("failure Resp = ", failureResp);
      
    })
  }

}
