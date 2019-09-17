import { Component, OnInit, HostListener, Inject } from '@angular/core';  
import { Router, RouterOutlet } from '@angular/router';
import { DOCUMENT } from '@angular/common';
import * as $ from 'jquery';


import {AccountService} from '../../login.account.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  userName;
  yourNavigation;
  stickyDiv;
  yourHeader;
  isAdmin : boolean = localStorage.getItem("UID") == "0" ? true : false;
  constructor(private router: Router, private accountService:AccountService,@Inject(DOCUMENT) document) {
    this.userName = accountService.getUserName();
  }

  ngOnInit() {
    var dropDown = $('li.dropdown');
		var subdropdown = $('li.dropdown-submenu');
        //Show dropdown on hover only for desktop devices
        if($(window).innerWidth() > 767){
            dropDown.on({
                mouseenter: function () {
                    dropDown.clearQueue();
				    $(this).find('.dropdown-menu').eq(0).addClass('show');
                },
                mouseleave: function () {
                    $(this).find('.dropdown-menu').removeClass('show');
                }
            });
			
			subdropdown.on({
                mouseenter: function () {
                    dropDown.clearQueue();
                    $(this).find('.dropdown-menu').addClass('show');
                },
                mouseleave: function () {
                    $(this).find('.dropdown-menu').removeClass('show');
                }
            });	
        }
        //Show dropdown on click only for mobile devices
        if($(window).innerWidth() < 768) {
            dropDown.on('click', function(event){
                // Avoid having the menu to close when clicking
                event.stopPropagation();
                // close all the siblings
                $(this).siblings().removeClass('show');
                $(this).siblings().find('.dropdown-menu').removeClass('show');

                // close all the submenus of siblings
                $(this).siblings().find('.dropdown-menu').parent().removeClass('show');

                // opening the one you clicked on
                $(this).find('.dropdown-menu').toggleClass('show');
                $(this).siblings('.dropdown-menu').toggleClass('show');
            });
        }
  }
  logout(){
    localStorage.clear();
    this.router.navigateByUrl("/login");
  }

  /** Sticky MenuBar **/
  @HostListener('window:scroll', ['$event'])
  onWindowScroll(e) {
     if (window.pageYOffset > 50) {
       let element = document.getElementById('navbar'); 
       element.classList.add('sticky');
     } else {
      let element = document.getElementById('navbar');
        element.classList.remove('sticky'); 
     }
  }



  
} 
