import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {LoggedUser} from "../../models/logged-user";
import {Observable} from 'rxjs';
import {Subject} from "rxjs";
import {UserService} from "../../layout/admin/components/administration/user-management/user.service";

@Injectable()
export class SessionContextService {

    private userLoggedIn = new Subject();
    loggedUser: LoggedUser;

    constructor(private cookieService: CookieService, private userService: UserService) {

    }

    watchSession(): Observable<any> {
        return this.userLoggedIn;
    }

    getUser(): LoggedUser {
        return this.loggedUser;
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
