import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {
    DeliveryListElement,
    ShipmentListElement,
    WarehouseAddElement,
    WarehouseListElement, WarehouseViewElement
} from "../../models/warehouse-elements";


@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

    private warehousePath = this.configuration.ServerWithApiUrl + '/warehouse';
    private deliveryPath = this.configuration.ServerWithApiUrl + '/delivery';
    private shipmentPath = this.configuration.ServerWithApiUrl + '/shipment';

    constructor(private httpService: HttpService, private configuration: Configuration) { }

    //Warehouses

    getAll(): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath);
    }

    getAllForTransferRequest(){
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/transfer-request');
    }

    getAllForShipmentRequest(){
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/shipment-request');
    }

    getAllForWarehouseman(): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/warehouseman');
    }

    getAllForCompany(id:number): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/company/'+id);
    }

    getAllForDepartment(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/department/'+id);
    }

    getAllForOffice(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/office/'+id);
    }

    createWarehouse(data:WarehouseAddElement): Observable<any>{
        return this.httpService.post(this.warehousePath,data);
    }

    getWarehouseView(id:string): Observable<WarehouseViewElement>{
        return this.httpService.get<WarehouseViewElement>(this.warehousePath+'/'+id);
    }

    deleteWarehouse(id :string): Observable<any>{
        return this.httpService.delete<any>(this.warehousePath+'/'+id);
    }

    //Deliveries

    getAllDeliveries(): Observable<DeliveryListElement[]> {
        return this.httpService.get<DeliveryListElement[]>(this.deliveryPath);
    }

    getAllDeliveriesForCompany(id:number): Observable<DeliveryListElement[]> {
        return this.httpService.get<DeliveryListElement[]>(this.deliveryPath+'/company/'+id);
    }

    getAllDeliveriesForWarehouse(): Observable<DeliveryListElement[]> {
        return this.httpService.get<DeliveryListElement[]>(this.deliveryPath+'/warehouseman');
    }

}
