import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ProfileInfo} from "../../models/profile-info";
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {ProfileEdit} from "../../models/profile-edit";
import {PasswordData} from "../../models/change-password";

@Injectable({
    providedIn: 'root'
})
export class ProfileService {

    private profilePath = this.configuration.ServerWithApiUrl + '/users';
    private updateAccountPath = this.configuration.ServerWithApiUrl + '/account/edit';

    constructor(private httpService: HttpService, private configuration: Configuration) {
    }

    getUserProfile(): Observable<ProfileInfo> {
        return this.httpService.get<ProfileInfo>(this.profilePath + '/profile');
    }

    getUserProfileEdit(): Observable<ProfileEdit> {
        return this.httpService.get<ProfileEdit>(this.profilePath + '/profile/edit');
    }

    getUserPasswordData(): Observable<PasswordData> {
        return this.httpService.get<PasswordData>(this.profilePath + '/password');
    }


    changePassword(data: PasswordData):Observable<any>  {
        return this.httpService.put<any>(this.profilePath + '/password', data);
    }


    updateAccountByUser(user: ProfileEdit) {
        return this.httpService.put<any>(this.updateAccountPath + '/self', user);
    }
}
