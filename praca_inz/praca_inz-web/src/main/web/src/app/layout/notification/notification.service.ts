import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {ProfileInfo} from "../profile/models/profile-info";
import {NotificationListElement} from "./models/notification-list-element";
import {StructureListElement} from "../admin/components/entity-management/models/structure-list-element";

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
