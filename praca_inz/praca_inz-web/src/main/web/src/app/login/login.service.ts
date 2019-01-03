import {Injectable} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class LoginService {


    constructor(
        private router: Router, private http: HttpClient, private cookieService: CookieService) {
    }

    obtainAccessToken(loginData) {
        let params = new URLSearchParams();
        params.append('username', loginData.username);
        params.append('password', loginData.password);
        params.append('grant_type', 'password');
        params.append('client_id', 'spring-security-oauth2-read-write-client');

        let headers = new HttpHeaders({
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA=='
        });
        console.log(params.toString());
        this.http.post('http://localhost:8081/oauth/token', params.toString(), {headers: headers})
            .subscribe(
                data => this.saveToken(data),
                err => alert('Invalid Credentials')
            );

    }

    saveToken(token) {
        var expireDate = new Date().getTime() + (1000 * token.expires_in);
        this.cookieService.set('access_token', token.access_token, expireDate);
        console.log('Obtained Access token');
        this.router.navigateByUrl('/page')
    }

    checkCredentials():boolean {
        return this.cookieService.check('access_token');
    }

    revokeToken(){

    }

    logout() {
        this.cookieService.delete('access_token');
        this.router.navigate(['/login']);
    }
}
