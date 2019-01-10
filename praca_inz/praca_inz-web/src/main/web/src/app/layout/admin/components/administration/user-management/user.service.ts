import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {AccountLevel} from "../../../../../models/account-level";
import {UserListElement} from "../../../../../models/user-list-element";
import {UserEdit} from "../../../../../models/user-edit";
import {HttpService} from "../../../../../shared/services/http.service";
import {Configuration} from "../../../../../app.constants";
import {LoggedUser} from "../../../../../models/logged-user";
import {UserInfo} from "../../../../../models/user-info";
import {StructureAddElement, StructureEditElement} from "../../../../../models/structure-elements";
import {RegisterUser} from "../../../../../models/register-user";
import {UserRoles} from "../../../../../models/user-roles";
import {PasswordDataForAdmin} from "../../../../../models/change-password-by-admin-model";


@Injectable({
  providedIn: 'root'
})
export class UserService {

    private userPath = this.configuration.ServerWithApiUrl + '/users';
    private registerPath =this.configuration.ServerWithApiUrl+'/register';
    private userPathDetail;
    private activePath = '/active';
    private inactivePath = '/inactive';
    private verifiedPath = '/verified';

  constructor( private httpService: HttpService, private configuration: Configuration) { }

    getAllSubordinates(): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.userPath+'/subordinates');
    }

    getAll(): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.userPath);
    }

    getAllWarehousemen(id:number): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.userPath+'/warehousemen/'+id);
    }

    getUserInfoToView(username: string): Observable<UserInfo> {
        this.userPathDetail = this.userPath + '/view/' + username;
        return this.httpService.get<UserInfo>(this.userPathDetail);
    }

    getUser(username: string): Observable<UserEdit> {
        this.userPathDetail = this.userPath + '/' + username + '/account';
        return this.httpService.get<UserEdit>(this.userPathDetail);
    }

    getLoggedUser():Observable<LoggedUser>{
        return this.httpService.get<LoggedUser>(this.userPath+'/logged');
    }

    getLoggedUserRoles():Observable<UserRoles>{
        return this.httpService.get<UserRoles>(this.userPath+'/logged/roles');
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


    createUser(data: RegisterUser): Observable<any>  {
        return this.httpService.post<RegisterUser>(this.registerPath+'/admin', data);
    }


    verifyUser(id: number): Observable<any> {
        return this.httpService.put(this.userPath + '/' + id + this.verifiedPath, '');
    }

    getUserPasswordData(id: string):Observable<PasswordDataForAdmin> {
        return this.httpService.get<PasswordDataForAdmin>(this.userPath+'/password'+id);
    }

    changePasswordByAdmin(passwordDataByAdmin: PasswordDataForAdmin) {

    }
}
