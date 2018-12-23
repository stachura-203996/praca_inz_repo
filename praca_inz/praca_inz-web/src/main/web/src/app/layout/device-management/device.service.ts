import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {StructureListElement} from "../admin/components/entity-management/models/structure-list-element";
import {DeviceListElement} from "./models/device-list-element";
import {TransferListElement} from "./models/transfer-list-element";

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

    getAllDevicesForUser(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath+"/user");
    }

    getAllTransfers(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath);
    }

    getAllTransfersForUser(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath+"/user");
    }
}


