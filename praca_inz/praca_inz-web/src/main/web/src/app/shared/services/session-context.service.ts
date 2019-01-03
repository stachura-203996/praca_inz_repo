import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {LoggedUser} from "../../models/logged-user";
import {Observable} from 'rxjs';
import {Subject} from "rxjs";
import {LoginService} from "../../login/login.service";
import {HttpService} from "./http.service";
import {UserService} from "../../layout/admin/components/administration/user-management/user.service";

@Injectable()
export class SessionContextService {

    private userLoggedIn = new Subject();
     loggedUser:LoggedUser;

    constructor(private cookieService: CookieService, private userService:UserService) {
        this.initLoggedUser();
    }

    initLoggedUser():void{
       this.userService.getLoggedUser().subscribe( x=>{this.loggedUser=x});
    }

    watchSession(): Observable<any> {
        return this.userLoggedIn;
    }

    getUser(): LoggedUser {
        return this.loggedUser;
    }

    setUser(user: LoggedUser): void {
        localStorage.setItem('loggedUser', JSON.stringify(user));
        this.userLoggedIn.next(true);
    }

    hasUserRole(role: string) {
        if (this.loggedUser == null) {
            return false;
        }
        return this.loggedUser.roles.includes(role);
    }

    setNameSurName(name: string, surname: string) {
        this.loggedUser.username = this.getUser().username;
        this.loggedUser.roles = this.getUser().roles;

        if ((name === this.loggedUser.name) === (surname === this.loggedUser.surname)) {
            this.loggedUser.name = name;
            this.loggedUser.surname = surname;
        } else if (name === this.loggedUser.name) {
            this.loggedUser.name = this.getUser().name;
            this.loggedUser.surname = surname;
        } else {
            this.loggedUser.name = name;
            this.loggedUser.surname = this.getUser().surname;
        }
        localStorage.setItem('loggedUser', JSON.stringify(this.loggedUser));
    }

    resetSession() {
        this.cookieService.delete('JSESSIONID');
        localStorage.removeItem('loggedUser');
        this.userLoggedIn.next(false);
    }

    getLocale(): string {
        return JSON.parse(localStorage.getItem('locale'));
    }

    setLocale(locale: string): void {
        localStorage.setItem('locale', JSON.stringify(locale));
        this.userLoggedIn.next(true);
    }
}
