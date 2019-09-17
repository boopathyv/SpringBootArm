import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

import {AccountService} from '../login.account.service';
import {AimcivilService} from '../services/aimcivil.service';



@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  loginStatus;
  latestNews;
  constructor(private accountService:AccountService, private AimcivilService:AimcivilService,) {
    this.loginStatus = accountService.isLogged();
   }

  ngOnInit() {
    this.getLatestNews();
  }
  showMoreContent($event){
    if($('#morecontents').hasClass('hide')){
      $('#morecnts').text('Show Less');
      $('#morecontents').removeClass("hide").addClass('show');
      $('#readmorecnt').removeClass("hide").addClass('show');
    }else{
      $('#more_cnts').text('Know More');
      $('#morecontents').removeClass("show").addClass('hide');
      $('#readmorecnt').removeClass("show").addClass('hide');
    }
  }
  getLatestNews(){
    this.AimcivilService.LatestNewsService().subscribe((res)=>{
      console.log(res);
      
          if(res.status==200){
            this.latestNews = res.body.sort(function(a, b){
              return b.latestNewsId - a.latestNewsId;
            });
          }
      });
  }
}
