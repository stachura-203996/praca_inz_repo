import { Injectable } from '@angular/core';
import {HttpService} from "../../../../../shared/services/http.service";
import {Configuration} from "../../../../../app.constants";
import {Observable} from "rxjs";
import {StructureListElement} from "../../../../../models/structure-list-element";
import {StructureAddElement} from "../../../../../models/structure-add-element";
import {StructureEditElement} from "../../../../../models/structure-edit-element";
import {StructureViewElement} from "../../../../../models/structure-view-element";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

    private departmentPath = this.configuration.ServerWithApiUrl + '/structure/department';

    constructor(private httpService: HttpService, private configuration: Configuration) { }


    getAll(): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.departmentPath);
    }

    getAllForCompany(id:string): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.departmentPath+'/company/'+id);
    }

    createDepartment(data:StructureAddElement): Observable<any>{
        return this.httpService.post<StructureAddElement>(this.departmentPath,data);
    }

    getDepartmentEdit(id:string): Observable<StructureEditElement>{
        return this.httpService.get<StructureEditElement>(this.departmentPath+'/'+id);
    }

    getDepartmentView(id:string): Observable<StructureViewElement>{
        return this.httpService.get<StructureViewElement>(this.departmentPath+'/'+id);
    }
}
