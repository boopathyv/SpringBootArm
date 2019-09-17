import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';

import {RegistrationType} from '../types/registration.type';
import {ForgotpassType} from '../types/forgotpass.type';
import {ChangepassType} from '../types/changepass.type';
import * as config from '../../globalConfig';


const httpHeaders ={
    headers: new HttpHeaders({'Content-Type': 'application/json' })
}


@Injectable({
    providedIn:'root'
})

export class registrationService{
    registerURL=config.gServiceUrl+"user/register";
    forgotpassURL=config.gServiceUrl+"user/forgotPwd";
    changepassURL=config.gServiceUrl+"user/changePassword";
    signupUrl = config.gServiceUrl + "user/signup";
    verifyOTPUrl: string = config.gServiceUrl + "user/verifyOTP";
    
    //registerURL="http://192.168.1.14:8080/user/register";
    constructor(private http:HttpClient){

    }
    createAccount(rdata):Observable<RegistrationType[]>{
       return this.http.post<RegistrationType[]>(this.registerURL,rdata,httpHeaders);
    }
    passwordRequest(fdata){
        return this.http.post<ForgotpassType[]>(this.forgotpassURL,fdata,httpHeaders);
    }
    changepassRequest(chdata){
        return this.http.post<ChangepassType[]>(this.changepassURL,chdata,httpHeaders);
    }

    signup(reqData: any) {
        return this.http.post(this.signupUrl, reqData, httpHeaders);
    }

    verifyOTP(reqData: any) {
        return this.http.post(this.verifyOTPUrl, reqData, httpHeaders);
    }

    resendOTP(userID: string) {
        return this.http.post(config.gServiceUrl + "user/" + userID + "/resendOTP", {}, httpHeaders);
    }
}