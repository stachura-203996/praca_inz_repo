import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ProfileInfo} from "../../models/profile-info";
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

    private profilePath = this.configuration.ServerWithApiUrl + '/users';

  constructor(private httpService: HttpService, private configuration: Configuration) { }

    getUserProfile(): Observable<ProfileInfo> {
        return this.httpService.get<ProfileInfo>(this.profilePath + '/profile');
    }
}
