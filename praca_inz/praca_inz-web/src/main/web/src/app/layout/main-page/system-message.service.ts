import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {StructureAddElement, StructureEditElement, StructureViewElement} from "../../models/structure-elements";
import {Observable} from "rxjs";
import {DeviceListElement, DeviceModelListElement} from "../../models/device-elements";
import {TransferListElement} from "../../models/transfer-list-element";
import {SystemMessageListElement} from "../../models/system-message-list-element";
import {SystemMessageAddElement} from "../../models/system-message-add-element";

@Injectable({
  providedIn: 'root'
})
export class SystemMessageService {

    private messagePath = this.configuration.ServerWithApiUrl + '/message/system';


    constructor(private httpService: HttpService, private configuration: Configuration) { }


    getAllMessages(): Observable<SystemMessageListElement[]> {
        return this.httpService.get<SystemMessageListElement[]>(this.messagePath);
    }


    createSystemMessage(data: SystemMessageAddElement): Observable<any>  {
        return this.httpService.post<SystemMessageAddElement>(this.messagePath, data);
    }

    deleteSystemMessage(id :string): Observable<any>{
        return this.httpService.delete<any>(this.messagePath+'/'+id);
    }
}
