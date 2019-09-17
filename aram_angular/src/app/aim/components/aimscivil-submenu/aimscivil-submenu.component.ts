import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-aimscivil-submenu',
  templateUrl: './aimscivil-submenu.component.html',
  styleUrls: ['./aimscivil-submenu.component.css']
})
export class AimscivilSubmenuComponent implements OnInit {

  isAdmin : boolean = localStorage.getItem("ROLE") && localStorage.getItem("ROLE").toLowerCase() == "admin" ? true : false;
  uid = localStorage.getItem("UID");

  constructor() { }

  ngOnInit() {
  }

}
