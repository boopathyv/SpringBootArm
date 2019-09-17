import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';

import { TestscoreType } from '../types/testscore.type';
import * as config from '../../globalConfig';

const httpHeaders ={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
}

@Injectable({
    providedIn:'root'
})

export class TestscoreService{
    getscoreUrl;
    allTest;
    constructor(private http:HttpClient){
    }

    getAllTests(){
        this.allTest = config.gServiceUrl+"getAllTests";
        return this.http.get(this.allTest,httpHeaders);
    }

    getUserScores(userID):Observable<TestscoreType[]>{
        this.getscoreUrl=config.gServiceUrl+"getTestAttemptDetails?userLoginId="+userID;
        return this.http.get<TestscoreType[]>(this.getscoreUrl,httpHeaders)
    }
    setSelectedTestId(tID){
        localStorage.setItem('tstID', tID);
    }
    
}