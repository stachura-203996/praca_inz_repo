import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpService} from "../../../../../shared/services/http.service";
import {Configuration} from "../../../../../app.constants";
import {
    StructureAddElement, StructureEditElement,
    StructureListElement,
    StructureViewElement
} from "../../../../../models/structure-elements";


@Injectable({
    providedIn: 'root'
})
export class CompanyService {

    private companyPath = this.configuration.ServerWithApiUrl + '/structure/company';

    constructor(private httpService: HttpService, private configuration: Configuration) {
    }

    getAll(): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.companyPath);
    }

    getCompany(id:string){
        return this.httpService.get<StructureViewElement>(this.companyPath+'/'+id);
    }

    getCompanyEdit(id:string): Observable<StructureEditElement>{
        return this.httpService.get<StructureEditElement>(this.companyPath+'/'+id);
    }

    createCompany(data: StructureAddElement): Observable<any>  {
       return this.httpService.post<StructureAddElement>(this.companyPath, data);
    }

    updateCompany(data: StructureEditElement): Observable<any> {
        return this.httpService.put<StructureEditElement>(this.companyPath, data);
    }

    deleteCompany(id :string): Observable<any>{
        return this.httpService.delete<any>(this.companyPath+'/'+id);
    }
}
