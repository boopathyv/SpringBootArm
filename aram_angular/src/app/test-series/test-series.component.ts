import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'test-series-submenu',
  templateUrl: './test-series.component.html',
  styleUrls: ['./test-series.component.css']
})
export class TestSeriesPage implements OnInit {

  isAdmin : boolean = localStorage.getItem("ROLE") && localStorage.getItem("ROLE").toLowerCase() == "admin" ? true : false;
  uid = localStorage.getItem("UID");

  constructor() { }

  ngOnInit() {
  }

}
