import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from 'rxjs/operators';

import * as config from '../../globalConfig';

const httpHeaders ={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
}


@Injectable({
    providedIn:'root'
})

export class AimcivilService{
    //civilServiceUrl=config.gServiceUrl+"user/login";
    civilServiceUrl='http://139.59.83.193:8080//aram/getPrelimsCurrentAffairs';
    constructor(private http:HttpClient){
    }
    
    prelimsCurrentAffairs(data){
        this.http.get('http://139.59.83.193:8080//aram/getPrelimsCurrentAffairs')
        /*this.http.get(this.civilServiceUrl).pipe(
            map(
                (response: Response) => {
                    const resdata = response.json();
                    //return resdata;
                }
            )); */
            console.log('Test..1'+data);
    }
}
