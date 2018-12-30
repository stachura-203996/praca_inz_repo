import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {WarehouseViewElement} from "../../models/warehouse-view-element";
import {WarehouseAddElement, WarehouseListElement} from "../../models/warehouse-elements";


@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

    private warehousePath = this.configuration.ServerWithApiUrl + '/warehouse';

    constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAll(): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath);
    }

    getAllForWarehouseman(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/company/'+id);
    }

    getAllForCompany(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/company/'+id);
    }

    getAllForDepartment(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/department/'+id);
    }

    getAllForOffice(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/warehouse/'+id);
    }

    createWarehouse(data:WarehouseAddElement): Observable<any>{
        return this.httpService.post(this.warehousePath,data);
    }

    getWarehouseView(id:string): Observable<WarehouseViewElement>{
        return this.httpService.get<WarehouseViewElement>(this.warehousePath+'/'+id);
    }
}
