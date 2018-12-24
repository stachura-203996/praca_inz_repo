import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {NotificationListElement} from "../../models/notification-list-element";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

    private notificationPath = this.configuration.ServerWithApiUrl + '/notification';

    constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAllNotificationsForUser(): Observable<NotificationListElement[]> {
        return this.httpService.get<NotificationListElement[]>(this.notificationPath+'/user');
    }
}
