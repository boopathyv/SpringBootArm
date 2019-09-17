import { Component, OnInit } from '@angular/core';

import {TestscoreService} from '../../services/testscore.service';
import {AccountService} from '../../login.account.service';


@Component({
  selector: 'app-test-instruction',
  templateUrl: './test-instruction.component.html',
  styleUrls: ['./test-instruction.component.css'],
  
})
export class TestInstructionComponent {
  testScores;
  allTests;
  constructor(private scroeService:TestscoreService,private sessionService:AccountService) { }

  ngOnInit() {
    
    this.scroeService.getAllTests().subscribe(
      res =>{
        this.allTests = res;
      }
    );
    this.scroeService.getUserScores(this.sessionService.getUserSessionId()).subscribe(
      response => {
          this.testScores = response;
          let arrlen = this.testScores.length;
          let tcount = (response)?this.testScores[arrlen-1].attemptId:1;
          this.sessionService.setTestCount(tcount);
      }
    );

  }
  selectedtstID(ent){
    this.scroeService.setSelectedTestId(ent.target.dataset.tstqid);
  }

}
