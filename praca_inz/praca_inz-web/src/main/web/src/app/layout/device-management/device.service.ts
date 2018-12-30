import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {TransferListElement} from "../../models/transfer-list-element";
import {StructureViewElement} from "../../models/structure-elements";
import {DeviceListElement, DeviceModelListElement} from "../../models/device-elements";


@Injectable({
  providedIn: 'root'
})
export class DeviceService {

    private devicePath = this.configuration.ServerWithApiUrl + '/device';
    private transferPath = this.configuration.ServerWithApiUrl + '/transfer';

    constructor(private httpService: HttpService, private configuration: Configuration) { }


    getDevice(id:string){
        return this.httpService.get<StructureViewElement>(this.devicePath+'/'+id);
    }

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

    getAllDevicesForCompany(id:String): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath+"/company/"+id);
    }

    getAllDevicesForDepartment(id:String): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath+"/department/"+id);
    }

    getAllTransfersForLoggedUser(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath+"/user");
    }

    getAllTransfersForUser(username:String): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath+"/user/"+username);
    }

    getAllDevicesModels(): Observable<DeviceModelListElement[]> {
        return this.httpService.get<DeviceModelListElement[]>(this.devicePath+"/model");
    }
}


