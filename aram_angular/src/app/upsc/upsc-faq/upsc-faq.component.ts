import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-upsc-faq',
  templateUrl: './upsc-faq.component.html',
  styleUrls: ['./upsc-faq.component.css']
})
export class UpscFaqComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    var acc = document.getElementsByClassName("accordion");
		var i;

		for (i = 0; i < acc.length; i++) {
		    acc[i].addEventListener("click", function() {
		        this.classList.toggle("active");
		        var panel = this.nextElementSibling;
		        if (panel.style.display === "block") {
		            panel.style.display = "none";
		        } else {
		            panel.style.display = "block";
		        }
		    });
		}
  }

}
