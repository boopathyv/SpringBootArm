import {Injectable} from '@angular/core';

const TOKEN = 'TOKEN';
const UID = 'UID';
const ROLE = 'ROLE';
const STATUS = 'STATUS';
const UNAME = 'UNAME';
const TESTCNT = 'TESTCNT';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  setToken(token: any): void { 
    if(token['authStatus']=='SUCCESS'){
      localStorage.setItem(TOKEN, token['token']);
      localStorage.setItem(ROLE, token['role']);
      localStorage.setItem(UID, token['userLoginId']);
      localStorage.setItem(UNAME, token['userName']);
    }
  }

  isLogged() {
    return localStorage.getItem(UID)?true:false;
  }
  
  getUserSessionId(){
    return localStorage.getItem(UID);
  }
  getUserName(){
    return localStorage.getItem(UNAME);
  }

  isAdmin(){
    return (localStorage.getItem("ROLE") == "ADMIN" || localStorage.getItem("ROLE") == "SUPER") ? true : false;
  }

  isSuper(){
    return localStorage.getItem("ROLE") == "SUPER" ? true : false;
  }
  
  setTestCount(lastTestCNT){
    let tcnt = (lastTestCNT)?lastTestCNT:1;
    localStorage.setItem(TESTCNT, tcnt);
  }

  getTestCount(){
    return localStorage.getItem(TESTCNT);
  }

}