import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {AccountLevel} from "../../../../../models/account-level";
import {UserListElement} from "../../../../../models/user-list-element";
import {UserEdit} from "../../../../../models/user-edit";
import {HttpService} from "../../../../../shared/services/http.service";
import {Configuration} from "../../../../../app.constants";
import {LoggedUser} from "../../../../../models/logged-user";
import {UserInfo} from "../../../../../models/user-info";
import {RegisterUser} from "../../../../../models/register-user";
import {UserRoles} from "../../../../../models/user-roles";
import {PasswordDataForAdmin} from "../../../../../models/change-password-by-admin-model";
import {ProfileEdit} from "../../../../../models/profile-edit";
import {PasswordData} from "../../../../../models/change-password";


@Injectable({
  providedIn: 'root'
})
export class UserService {

    private userPath = this.configuration.ServerWithApiUrl + '/users';
    private registerPath =this.configuration.ServerWithApiUrl+'/register';
    private updateAccountPath=this.configuration.ServerWithApiUrl+'/account/edit';
    private userPathDetail;
    private activePath = '/active';
    private inactivePath = '/inactive';
    private verifiedPath = '/verified';

  constructor( private httpService: HttpService, private configuration: Configuration) { }


    getUserInfoToView(username: string): Observable<UserInfo> {
        this.userPathDetail = this.userPath + '/view/' + username;
        return this.httpService.get<UserInfo>(this.userPathDetail);
    }

    getUser(username: string): Observable<UserEdit> {
        this.userPathDetail = this.userPath + '/' + username + '/account';
        return this.httpService.get<UserEdit>(this.userPathDetail);
    }

    getAllSubordinates(): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.userPath + '/subordinates');
    }

    getAllForReport(): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.userPath + '/confirmation');
    }

    getAll(): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.userPath);
    }

    getAllWarehousemen(id: number): Observable<UserListElement[]> {
        return this.httpService.get<UserListElement[]>(this.userPath + '/warehousemen/' + id);
    }


    getUserEdit(id:number): Observable<UserEdit> {
        return this.httpService.get<UserEdit>(this.userPath+'/edit/'+id);
    }

    getLoggedUser(): Observable<LoggedUser> {
        return this.httpService.get<LoggedUser>(this.userPath + '/logged');
    }

    getLoggedUserRoles(): Observable<UserRoles> {
        return this.httpService.get<UserRoles>(this.userPath + '/logged/roles');
    }

    getUserRoles(id: string): Observable<UserRoles> {
        return this.httpService.get<UserRoles>(this.userPath + '/user/roles/' + id);
    }

    updateAccountByAdmin(user: UserEdit): Observable<any> {
        return this.httpService.put<any>(this.updateAccountPath + '/admin', user);
    }

    createUser(data: RegisterUser): Observable<any> {
        return this.httpService.post<RegisterUser>(this.registerPath + '/admin', data);
    }


    getUserPasswordAdminData(id: string): Observable<PasswordDataForAdmin> {
        return this.httpService.get<PasswordDataForAdmin>(this.userPath + '/password/admin/' + id);
    }

    changePasswordByAdmin(data: PasswordDataForAdmin):Observable<any>  {
        return this.httpService.put<any>(this.userPath + '/password/admin', data);
    }

    deleteUser(id: string) {
        return this.httpService.delete<any>(this.userPath + '/' + id);
    }
}
