import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {StructureViewElement} from "../../models/structure-elements";
import {Observable} from "rxjs";
import {DeviceListElement, DeviceModelListElement} from "../../models/device-elements";
import {TransferListElement} from "../../models/transfer-list-element";
import {SystemMessageListElement} from "../../models/system-message-list-element";

@Injectable({
  providedIn: 'root'
})
export class SystemMessageService {

    private messagePath = this.configuration.ServerWithApiUrl + '/message/system';


    constructor(private httpService: HttpService, private configuration: Configuration) { }


    getAllMessages(): Observable<SystemMessageListElement[]> {
        return this.httpService.get<SystemMessageListElement[]>(this.messagePath);
    }

    getLastMessages(): Observable<SystemMessageListElement[]> {
        return this.httpService.get<SystemMessageListElement[]>(this.messagePath+'/last');
    }
}
