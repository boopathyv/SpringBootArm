import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as config from '../../../globalConfig';
@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  students: any[] = [];
  originalArr: any[] = [];
  config: any;
  search: any = { text: "", registeredWithUPSC: false };
  isActive: boolean = true;
  reqCount: number = 0;
  constructor(private http: HttpClient) {
    this.config = {
      currentPage: 1,
      itemsPerPage: 20,
      totalItems: 0
    };
  }

  ngOnInit() {
    this.getStudents();
  }

  getStudents() {
    const url = config.gServiceUrl + 'student/list/' + (this.isActive ? 'active' : 'inActive') + '?from=' + ((this.config.currentPage - 1) * this.config.itemsPerPage) +
     "&count=" + (this.config.itemsPerPage) + "&searchTxt=" + this.search.text + "&reqCount=" + this.reqCount + "&registeredWithUPSC=" + this.search.registeredWithUPSC;
    let self = this;
    this.http.post(url, {}).subscribe(response => {
      // console.log("success resp = ", response);
      if(self.reqCount != response['reqCount']) return;
      self.students = response['students'] as any[];
      self.config.totalItems = response["count"];
    }, failureResp => {
      console.log("failure resp = ", failureResp);

    })
  }

  pageChange(newPage: number) {
    // console.log(newPage);
    let config = JSON.parse(JSON.stringify(this.config));
    config.currentPage = newPage;
    this.config = config;
    this.reqCount++;
    this.getStudents();

  }

  filterStudents() {
    // console.log("filter students = ", this.search.text);

    this.config.currentPage = 1;
    this.reqCount++;
    this.getStudents();

  }

  changeActiveStatus(status: boolean) {
    this.isActive = status;

    this.reqCountAndApiCall();
    
  }

  reqCountAndApiCall() {
    this.config.currentPage = 1;
    this.reqCount++;
    this.getStudents();
  }

  changeStatusOfStudent(url: string) {
    let self = this;
    this.reqCount++;
    this.http.put(url, {}).subscribe(function (successResp) {
      // console.log("successResp = ", successResp);
      self.getStudents();

    }, function (failureResp) {
      console.log("failureResp = ", failureResp);

    })
  }

  activateStudent(studentId) {
    const url = config.gServiceUrl + "student/" + studentId + "/activate";
    this.changeStatusOfStudent(url);
  }

  inActivateStudent(studentId) {

    let isConfirm = window.confirm("Are you sure, want to delete this student ?");
    if (!isConfirm) return;
    const url = config.gServiceUrl + "student/" + studentId + "/inActivate";
    this.changeStatusOfStudent(url);
  }

  toggleVisibility(e){
    this.search.registeredWithUPSC = e.target.checked;
    this.reqCountAndApiCall();

  }

}
