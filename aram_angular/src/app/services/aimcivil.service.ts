import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {AimcivilType} from '../types/aimcivil.type';
import * as config from '../../globalConfig';

@Injectable({
  providedIn: 'root'
})

export class AimcivilService {
  newsService;
  constructor(private http:HttpClient) { }
/*  prelimsCurrentAffairs(serviceURL): Observable<AimcivilType[]>{
    return this.http.get<AimcivilType[]>(serviceURL).pipe(
        map((response: any) => {
             return response;
        })
    );
  } */

  prelimsCurrentAffairs(serviceURL){
    return this.http.get(serviceURL,{observe: 'response'}).pipe(
        map((response: any) => {
             return response;
        })
    );
  }
 
  LatestNewsService(){
    this.newsService = config.gServiceUrl+'latestNews/getActive';
    return this.http.get(this.newsService ,{observe: 'response'}).pipe(
      map((response: any) => {
           return response;
      })
    );
  }
    
}
