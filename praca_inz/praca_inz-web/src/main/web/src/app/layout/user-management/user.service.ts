import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {AccountLevel} from "./models/account-level";
import {UserListElement} from "./models/user-list-element";
import {UserEdit} from "./models/user-edit";
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";

@Injectable({
  providedIn: 'root'
})
export class UserService {

    private userPath = this.configuration.ServerWithApiUrl + '/users';
    private userPathDetail;
    private activePath = '/active';
    private inactivePath = '/inactive';
    private verifiedPath = '/verified';

  constructor( private httpService: HttpService, private configuration: Configuration) { }

    getAll(): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.userPath);
    }

    getUser(username: string): Observable<UserEdit> {
        this.userPathDetail = this.userPath + '/' + username + '/account';
        return this.httpService.get<UserEdit>(this.userPathDetail);
    }

    updateUser(user: UserEdit): Observable<any> {
        return this.httpService.put<any>(this.userPathDetail, user);
    }

    activateAccount(id: number): Observable<any> {
        return this.httpService.put(this.userPath + '/' + id + this.activePath, '');
    }

    deactivateAccount(id: number): Observable<any> {
        return this.httpService.put(this.userPath + '/' + id + this.inactivePath, '');
    }

    getAccessLevels(userId: number): Observable<AccountLevel[]> {
        return this.httpService.get<AccountLevel[]>(this.userPath + '/' + userId + '/accessLevels');
    }

    activateAccessLevel(userId: number, accountId: number): Observable<any> {
        return this.httpService.put(this.userPath + '/' + userId + '/accessLevels/' + accountId + this.activePath, '');
    }

    deactivateAccessLevel(userId: number, accountId: number): Observable<any> {
        return this.httpService.put(this.userPath + '/' + userId + '/accessLevels/' + accountId + this.inactivePath, '');
    }



    verifyUser(id: number): Observable<any> {
        return this.httpService.put(this.userPath + '/' + id + this.verifiedPath, '');
    }
}
