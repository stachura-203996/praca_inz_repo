import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {StructureAddElement} from "../../models/structure-add-element";
import {WarehouseListElement} from "../../login/warehouse-list-element";
import {WarehouseViewElement} from "../../models/warehouse-view-element";


@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

    private warehousePath = this.configuration.ServerWithApiUrl + '/warehouse';

    constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAll(): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath);
    }

    getAllForCompany(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/company/'+id);
    }

    getAllForDepartment(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/department/'+id);
    }

    getAllForOffice(id:string): Observable<WarehouseListElement[]> {
        return this.httpService.get<WarehouseListElement[]>(this.warehousePath+'/office/'+id);
    }

    createWarehouse(data:StructureAddElement): Observable<any>{
        return this.httpService.post(this.warehousePath,data);
    }

    getWarehouseView(id:string): Observable<WarehouseViewElement>{
        return this.httpService.get<WarehouseViewElement>(this.warehousePath+'/'+id);
    }
}
