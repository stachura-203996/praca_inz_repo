import {Injectable} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoggedUser} from "../models/logged-user";
import {UserService} from "../layout/admin/components/administration/user-management/user.service";
import {HttpService} from "../shared/services/http.service";
import {Configuration} from "../app.constants";
import {MessageService} from "../shared/services/message.service";
import {TranslateService} from "@ngx-translate/core";

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    isActive: boolean;
    user: LoggedUser;
    private userPath = this.configuration.ServerWithApiUrl + '/users';

    constructor(
        private router: Router,
        private configuration: Configuration,
        private http: HttpClient,
        private cookieService: CookieService,
        private messageService: MessageService,
        private translate: TranslateService,
        private httpService: HttpService,
        private userService: UserService) {
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
                err => this.translate.get('BadLogin').subscribe(x => {
                    this.messageService.error(x);
                    console.log(x);
                })
            );

    }

    saveToken(token) {
        var expire = new Date();
        var time = Date.now() + (1000 * token.expires_in);
        expire.setTime(time);
        this.cookieService.set('access_token', token.access_token, expire,'/ui/page');
        console.log('Obtained Access token');
        this.saveLoggedUser(token);
        window.location.href = 'http://localhost:8081/ui/page/main-page';
    }

    checkCredentials(): boolean {
        return this.cookieService.check('access_token');
    }


    resetPassword(username: string) {
        this.http.get<any>('http://localhost:8081/reset/' + username).subscribe(rep => {
            this.translate.get('PssswordResetSuccess').subscribe(x => {
                this.messageService.success(x);
            }), error => {
                this.translate.get('PssswordResetError').subscribe(x => {
                    this.messageService.error(x);
                })
            }
        });
    }

    saveLoggedUser(token) {
        let headers = new HttpHeaders({'authorization': 'Bearer ' + token.access_token});
        this.http.get<LoggedUser>('http://localhost:8081/secured/users/logged', {headers: headers}).subscribe(user => {
            localStorage.setItem('loggedUser', JSON.stringify(user));
        });
    }

    getUser(): LoggedUser {
        const user: LoggedUser = JSON.parse(localStorage.getItem('loggedUser'));
        if (user == null) {
            this.logout();
        }
        return user;
    }

    revokeToken() {
        if (this.checkCredentials()) {
            let headers = new HttpHeaders({'authorization': 'Bearer ' + this.cookieService.get('access_token')});
            this.http.get<LoggedUser>('http://localhost:8081/secured/revoke/token', {headers: headers}).subscribe(user => {
                localStorage.setItem('loggedUser', JSON.stringify(user));
            })
        }
    }

    logout() {

        localStorage.removeItem('loggedUser');
        localStorage.clear();
        this.revokeToken();
        this.cookieService.deleteAll('/ui/page')
        this.cookieService.delete('JSESSIONID');
        this.cookieService.delete('access_token');
        this.router.navigate(['/login']);
    }
}
