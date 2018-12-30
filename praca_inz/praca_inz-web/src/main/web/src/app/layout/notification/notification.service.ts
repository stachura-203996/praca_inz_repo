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

    getAllUnreadedNotificationsForUser(): Observable<NotificationListElement[]> {
        return this.httpService.get<NotificationListElement[]>(this.notificationPath+'/user');
    }

    getAllReadedNotificationsForUser(): Observable<NotificationListElement[]> {
        return this.httpService.get<NotificationListElement[]>(this.notificationPath+'/user/readed');
    }

    updateNotification(data: NotificationListElement): Observable<any> {
        return this.httpService.put<NotificationListElement>(this.notificationPath, data);
    }

    deleteNotification(id :string): Observable<any>{
        return this.httpService.delete<any>(this.notificationPath+'/'+id);
    }
}
