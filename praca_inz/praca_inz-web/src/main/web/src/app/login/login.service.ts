import { Injectable } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable()
export class LoginService {

    constructor(
        private _router: Router,private http: HttpClient, private cookieService:CookieService){}

    obtainAccessToken(loginData){
        let params = new URLSearchParams();
        params.append('username',loginData.username);
        params.append('password',loginData.password);
        params.append('grant_type','password');
        params.append('client_id','+');

        let headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa("fooClientIdPassword:secret")});
               console.log(params.toString());
        this.http.post('http://localhost:8081/oauth/token', params.toString(), { headers: headers })
            .subscribe(
                data => this.saveToken(data),
                err => alert('Invalid Credentials')
            );

    }

    saveToken(token){
        var expireDate = new Date().getTime() + (1000 * token.expires_in);
        this.cookieService.set('access_token', token.access_token, expireDate);
        console.log('Obtained Access token');
        localStorage.setItem('isLoggedin', 'true');
        this._router.navigate(['/dashboard']);
        localStorage.setItem('isLoggedin', 'true');
    }

    checkCredentials(){
        if (!this.cookieService.check('access_token')){
            this._router.navigate(['/login']);
            return false;
        } else {
            return true;
        }
    }

    logout() {
        this.cookieService.delete('access_token');
        this._router.navigate(['/login']);
    }
}
