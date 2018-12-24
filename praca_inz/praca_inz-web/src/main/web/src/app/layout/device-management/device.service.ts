import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {StructureListElement} from "../../models/structure-list-element";
import {DeviceListElement} from "../../models/device-list-element";
import {TransferListElement} from "../../models/transfer-list-element";
import {DeviceTypeListElement} from "../../models/device-type-list-element";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

    private devicePath = this.configuration.ServerWithApiUrl + '/device';
    private transferPath = this.configuration.ServerWithApiUrl + '/transfer';

    constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAllDevices(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath);
    }

    getAllDevicesForLoggedUser(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath+"/user");
    }

    getAllTransfers(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath);
    }

    getAllDevicesForUser(username:String): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath+"/user/"+username);
    }

    getAllTransfersForLoggedUser(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath+"/user");
    }

    getAllTransfersForUser(username:String): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath+"/user/"+username);
    }

    getAllDevicesTypes(): Observable<DeviceTypeListElement[]> {
        return this.httpService.get<DeviceTypeListElement[]>(this.devicePath+"/type");
    }
}


