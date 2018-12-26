import { Injectable } from '@angular/core';
import {HttpService} from "../../../../../shared/services/http.service";
import {Configuration} from "../../../../../app.constants";
import {Observable} from "rxjs";
import {StructureListElement} from "../../../../../models/structure-list-element";
import {StructureAddElement} from "../../../../../models/structure-add-element";
import {StructureViewElement} from "../../../../../models/structure-view-element";

@Injectable({
  providedIn: 'root'
})
export class OfficeService {

    private officePath = this.configuration.ServerWithApiUrl + '/structure/office';

    constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAll(): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.officePath);
    }

    getAllForCompany(id:string): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.officePath+'/company/'+id);
    }

    getAllForDepartment(id:string): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.officePath+'/department/'+id);
    }

    createOffice(data:StructureAddElement): Observable<any>{
        return this.httpService.post(this.officePath,data);
    }

    getOfficeView(id:string): Observable<StructureViewElement>{
        return this.httpService.get<StructureViewElement>(this.officePath+'/'+id);
    }

}
