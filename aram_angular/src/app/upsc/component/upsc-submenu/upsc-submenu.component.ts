import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upsc-submenu',
  templateUrl: './upsc-submenu.component.html',
  styleUrls: ['./upsc-submenu.component.css']
})
export class UpscSubmenuComponent implements OnInit {
  subMenuTitle;
  constructor() { }

  ngOnInit() {
  }
  usubmenuTitle(event){
    this.subMenuTitle = event.target.outerText;
    console.log(event.target.outerText);
  }
}
