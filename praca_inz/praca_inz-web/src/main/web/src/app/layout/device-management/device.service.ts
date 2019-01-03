import {Injectable} from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {TransferListElement} from "../../models/transfer-list-element";
import {StructureViewElement} from "../../models/structure-elements";
import {
    DeviceListElement,
    DeviceModelListElement, DeviceModelViewElement,
    DeviceTypeListElement,
    ParameterListElement
} from "../../models/device-elements";


@Injectable({
    providedIn: 'root'
})
export class DeviceService {

    private devicePath = this.configuration.ServerWithApiUrl + '/device';
    private transferPath = this.configuration.ServerWithApiUrl + '/transfer';

    constructor(private httpService: HttpService, private configuration: Configuration) {
    }

    //Devices

    getDevice(id: string) {
        return this.httpService.get<StructureViewElement>(this.devicePath + '/' + id);
    }

    getAllDevices(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath);
    }

    getAllDevicesForLoggedUser(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/user");
    }

    getAllDevicesForShipmentRequest(): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/shipment-request");
    }

    getAllDevicesForUser(username: String): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/user/" + username);
    }

    getAllDevicesForCompany(id: String): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/company/" + id);
    }

    getAllDevicesForDepartment(id: String): Observable<DeviceListElement[]> {
        return this.httpService.get<DeviceListElement[]>(this.devicePath + "/department/" + id);
    }

    getAllTransfersForLoggedUser(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath + "/user");
    }

    deleteDevice(id :string): Observable<any>{
        return this.httpService.delete<any>(this.devicePath+'/'+id);
    }


    //Device Models

    getDeviceModelView(id: string) {
        return this.httpService.get<DeviceModelViewElement>(this.devicePath + '/model/' + id);
    }

    getAllDevicesModels(): Observable<DeviceModelListElement[]> {
        return this.httpService.get<DeviceModelListElement[]>(this.devicePath + "/model");
    }

    deleteDeviceModel(id :string): Observable<any>{
        return this.httpService.delete<any>(this.devicePath+'/model/'+id);
    }

    //Parameters

    getAllParametersForDeviceModel(id: string): Observable<ParameterListElement[]> {
        return this.httpService.get<ParameterListElement[]>(this.devicePath + "/model/parameters/"+id);
    }


    //Device Types

    getAllDevicesTypes(): Observable<DeviceTypeListElement[]> {
        return this.httpService.get<DeviceModelListElement[]>(this.devicePath + "/type");
    }

    deleteDeviceTypes(id :string): Observable<any>{
        return this.httpService.delete<any>(this.devicePath+'/type/'+id);
    }

    //Transfers

    getAllTransfers(): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath);
    }

    getAllTransfersForUser(username: String): Observable<TransferListElement[]> {
        return this.httpService.get<TransferListElement[]>(this.transferPath + "/user/" + username);
    }


}


