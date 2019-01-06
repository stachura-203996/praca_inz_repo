import { Injectable } from '@angular/core';
import {HttpService} from "../../../../../shared/services/http.service";
import {Configuration} from "../../../../../app.constants";
import {Observable} from "rxjs";
import {
    StructureAddElement,
    StructureEditElement,
    StructureListElement, StructureViewElement
} from "../../../../../models/structure-elements";
import {DepartmentEditComponent} from "./department-edit/department-edit.component";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

    private departmentPath = this.configuration.ServerWithApiUrl + '/structure/department';

    constructor(private httpService: HttpService, private configuration: Configuration) { }


    getAll(): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.departmentPath);
    }

    getAllForAdmin(role:boolean,id:number): Observable<StructureListElement[]> {
        if(role) {
            return this.httpService.get<StructureListElement[]>(this.departmentPath);
        } else{
            return this.httpService.get<StructureListElement[]>(this.departmentPath+'/company/'+id);
        }
    }

    getAllForCompany(id:number): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.departmentPath+'/company/'+id);
    }



    getDepartmentEdit(id:string): Observable<StructureEditElement>{
        return this.httpService.get<StructureEditElement>(this.departmentPath+'/'+id);
    }

    getDepartmentView(id:string): Observable<StructureViewElement>{
        return this.httpService.get<StructureViewElement>(this.departmentPath+'/'+id);
    }

    createDepartment(data:StructureAddElement): Observable<any>{
        return this.httpService.post<StructureAddElement>(this.departmentPath,data);
    }

    updateDepartment(data: StructureEditElement): Observable<any> {
        return this.httpService.put<StructureEditElement>(this.departmentPath, data);
    }

    deleteDepartament(id :string): Observable<any>{
        return this.httpService.delete<any>(this.departmentPath+'/'+id);
    }
}
