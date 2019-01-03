import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {StructureAddElement, StructureEditElement, StructureViewElement} from "../../models/structure-elements";
import {Observable} from "rxjs";
import {
    DeliveryRequestAddElement, DeliveryRequestEditElement,
    DeviceRequestAddElement, DeviceRequestEditElement,
    RequestListElement, ShipmentRequestAddElement, ShipmentRequestEditElement,
    TransferRequestAddElement, TransferRequestEditElement
} from "../../models/request-elements";
import {TransferRequestEditComponent} from "../device-management/transfer-requests/transfer-request-edit/transfer-request-edit.component";


@Injectable({
  providedIn: 'root'
})
export class RequestService {

    private requestPath = this.configuration.ServerWithApiUrl + '/request';
   

    constructor(private httpService: HttpService, private configuration: Configuration) { }


    getTransferRequestEdit(id:string): Observable<TransferRequestEditElement>{
        return this.httpService.get<TransferRequestEditElement>(this.requestPath+'/transfer/'+id);
    }

    getDeviceRequestEdit(id:string): Observable<DeviceRequestEditElement>{
        return this.httpService.get<DeviceRequestEditElement>(this.requestPath+'/device/'+id);
    }

    getDeliveryRequestEdit(id:string): Observable<DeliveryRequestEditElement>{
        return this.httpService.get<DeliveryRequestEditElement>(this.requestPath+'/delivery/'+id);
    }

    getShipmentRequestEdit(id:string): Observable<ShipmentRequestEditElement>{
        return this.httpService.get<ShipmentRequestEditElement>(this.requestPath+'/shipment/'+id);
    }

    getAllRequests(type:string): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath+'/type/'+type);
    }

    getAllRequestsForLoggedUser(type:string): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath+"/user/"+type);
    }
    
    getAllRequestsForOffice(type:string,id:string): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath+"/office/"+type+'/'+id);
    }

    createTransferRequest(data:TransferRequestAddElement): Observable<any>{
        return this.httpService.post<TransferRequestAddElement>(this.requestPath+'/transfer',data);
    }

    createDeviceRequest(data:DeviceRequestAddElement): Observable<any>{
        return this.httpService.post<DeviceRequestAddElement>(this.requestPath+'/device',data);
    }

    createDeliveryRequest(data:DeliveryRequestAddElement): Observable<any>{
        return this.httpService.post<DeliveryRequestAddElement>(this.requestPath+'/delivery',data);
    }

    createShipmentRequest(data:ShipmentRequestAddElement): Observable<any>{
        return this.httpService.post<ShipmentRequestAddElement>(this.requestPath+'/shipment',data);
    }

    updateTransferRequest(data: TransferRequestEditElement): Observable<any> {
        return this.httpService.put<TransferRequestEditElement>(this.requestPath+'/transfer', data);
    }

    updateDeviceRequest(data: DeviceRequestEditElement): Observable<any> {
        return this.httpService.put<DeviceRequestEditElement>(this.requestPath+'/device', data);
    }

    updateDeliveryRequest(data: DeliveryRequestEditElement): Observable<any> {
        return this.httpService.put<DeliveryRequestEditElement>(this.requestPath+'/delivery', data);
    }

    updateShipmentRequest(data: ShipmentRequestEditElement): Observable<any> {
        return this.httpService.put<ShipmentRequestEditElement>(this.requestPath+'/shipment', data);
    }

    cancelRequest(data: RequestListElement): Observable<any> {
        return this.httpService.put<StructureEditElement>(this.requestPath+'/cancel/', data);
    }

    deleteRequest(id :string): Observable<any>{
        return this.httpService.delete<any>(this.requestPath+'/'+id);
    }

   
}
