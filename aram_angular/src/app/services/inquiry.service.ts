import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';

import * as config from '../../globalConfig';
import {InquiryType} from '../types/inquiry.type';

const httpHeaders ={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
}

@Injectable({
    providedIn:'root'
})
export class InquiryService{
    inquiryUrl=config.gServiceUrl+"joinUS";
    constructor(private http:HttpClient){
    }

    submitInquiry(data):Observable<InquiryType[]>{
        return this.http.post<InquiryType[]>(this.inquiryUrl,data,httpHeaders);
    }
    
}