import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpService} from "../../../../../shared/services/http.service";
import {Configuration} from "../../../../../app.constants";
import {StructureListElement} from "../../../../../models/structure-list-element";
import {StructureAddElement} from "../../../../../models/structure-add-element";
import {StructureEditElement} from "../../../../../models/structure-edit-element";
import {StructureViewElement} from "../../../../../models/structure-view-element";

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

    createCompany(data: StructureAddElement): Observable<any>  {
       return this.httpService.post<StructureAddElement>(this.companyPath, data);
    }

}
