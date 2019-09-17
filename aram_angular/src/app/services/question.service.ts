import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';

import * as config from '../../globalConfig';
import { QuestionTypes } from '../types/question.type';


import { map } from 'rxjs/operators';

const httpHeaders={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
}


@Injectable({
    providedIn: 'root'
})

export class QuestionService{
    questionGetAPIurl: any = config.gServiceUrl+"getOnlineTest";
    questionSubmitAPIurl: any = config.gServiceUrl+"calculateTestResults";
   
    
    constructor(private http: HttpClient) { }


    getQuestionAll(testID): Observable<QuestionTypes[]>{
        return this.http.post<QuestionTypes[]>(this.questionGetAPIurl+"?testId="+testID, {testId:testID}, httpHeaders).pipe(
            map((response: any) => {                  
                    return response;
            })
        );

    }

    submitQuesion(data): Observable<{}>{
        return this.http.post<{}>(this.questionSubmitAPIurl, data, httpHeaders).pipe(
            map((response: any) => {                   
                    return response;
            })
        );
    }

    getSelectedTestID(){
        return localStorage.getItem('tstID');
    }

//     getAllassociates(id:number): Observable<AssociatesTypes[]>{
//         return this.http.get<AssociatesTypes[]>(this.associatesConnectUrl+id);
//     }
//     getassociates(id:number): Observable<AssociatesTypes[]>{
//         return this.http.get<AssociatesTypes[]>(this.associatesAPIurl+"/"+id);
//     }
//    addassociates(data): Observable<AssociatesTypes[]>{
//        console.log(this.associatesAPIurl, data, httpHeaders);
//        return this.http.post<AssociatesTypes[]>(this.associatesAPIurl, data, httpHeaders)  
//    }
//    editassociates(data,id:number): Observable<AssociatesTypes[]>{
//        console.log(this.associatesAPIurl, data, httpHeaders);
//        return this.http.put<AssociatesTypes[]>(this.associatesAPIurl+"/"+id, data, httpHeaders)  
//    }

//    deleteassociates(id:number): Observable<AssociatesTypes[]>{
//        return this.http.delete<AssociatesTypes[]>(this.associatesAPIurl+"/"+id, httpHeaders);
//    }

//    uploadPhoto(data): Observable<FormData[]> {
//        console.log(data);
//     return this.http.post<FormData[]>(this.associatesImageAPIurl, data, httpHeaders)
//    }
}