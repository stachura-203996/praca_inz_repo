import {Injectable} from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {StructureEditElement} from "../../models/structure-elements";
import {Observable} from "rxjs";
import {
    ChangeRequestStatusElement,
    DeliveryRequestAddElement,DeviceRequestAddElement,
    RequestListElement, RequestViewElement, ShipmentRequestAddElement,
    TransferRequestAddElement
} from "../../models/request-elements";
import {DeviceListElement} from "../../models/device-elements";


@Injectable({
    providedIn: 'root'
})
export class RequestService {

    private requestPath = this.configuration.ServerWithApiUrl + '/request';


    constructor(private httpService: HttpService, private configuration: Configuration) {
    }

    //VIEW
    getRequestView(id: string): Observable<RequestViewElement> {
        return this.httpService.get<RequestViewElement>(this.requestPath + '/' + id);
    }

    //GET-ALL

    getAllRequests(type: string): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath + '/type/' + type);
    }

    getAllRequestsForLoggedUser(): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath + "/user/");
    }

    getAllRequestsForManager(): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath + "/manager/");
    }

    getAllRequestsFromOtherUsers() {
        return this.httpService.get<RequestListElement[]>(this.requestPath + "/other/users");
    }

    getAllRequestsForWarehouseman() {
        return this.httpService.get<RequestListElement[]>(this.requestPath + "/warehouseman/");
    }

    getAllRequestsFromOtherWarehouses() {
        return this.httpService.get<RequestListElement[]>(this.requestPath + "/other/");
    }

    //CREATE
    createTransferRequest(data: TransferRequestAddElement): Observable<any> {
        return this.httpService.post<TransferRequestAddElement>(this.requestPath + '/transfer', data);
    }

    createDeviceRequest(data: DeviceRequestAddElement): Observable<any> {
        return this.httpService.post<DeviceRequestAddElement>(this.requestPath + '/device', data);
    }

    createDeliveryRequest(data: DeliveryRequestAddElement): Observable<any> {
        return this.httpService.post<DeliveryRequestAddElement>(this.requestPath + '/delivery', data);
    }

    createShipmentRequest(data: ShipmentRequestAddElement): Observable<any> {
        return this.httpService.post<ShipmentRequestAddElement>(this.requestPath + '/shipment', data);
    }

    //CHANGE STATUS

    changeRequestStatus(data:ChangeRequestStatusElement): Observable<any> {
        return this.httpService.put<ChangeRequestStatusElement>(this.requestPath+'/status', data);
    }

    //DEVICES
    getAllDevicesForRequest(id:number): Observable<DeviceListElement[]>{
        return this.httpService.get<DeviceListElement[]>(this.requestPath + "/devices/"+id);
    }

    addDevicesToRequest(data:number[],id:number):Observable<any>{
        return this.httpService.put<DeviceListElement[]>(this.requestPath + '/devices'+id, data);
    }

    //CANCEL

    cancelRequest(data: RequestListElement): Observable<any> {
        return this.httpService.put<StructureEditElement>(this.requestPath + '/cancel/', data);
    }

    //DELETE
    deleteRequest(id: string): Observable<any> {
        return this.httpService.delete<any>(this.requestPath + '/' + id);
    }


}
