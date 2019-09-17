import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  @ViewChild('openModal') openModal:ElementRef;

  ngOnInit() {
    this.openModal.nativeElement.click();
  }

  hideModal() {
    // let doc = document.getElementById('load-modal');
    // if(doc) {
    //   doc.hidden = true;
    // }
  }
}
