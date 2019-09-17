import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';

import {LoginType} from '../types/login.type';

import * as config from '../../globalConfig';


const httpHeaders ={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
}


@Injectable({
    providedIn:'root'
})

export class LoginService{
    loginUrl=config.gServiceUrl+"user/login";
    constructor(private http:HttpClient){
    }

    loginCheck(data):Observable<LoginType[]>{
        return this.http.post<LoginType[]>(this.loginUrl,data,httpHeaders)
    }
    
}