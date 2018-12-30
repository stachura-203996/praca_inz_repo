import { Injectable } from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {StructureAddElement, StructureEditElement, StructureViewElement} from "../../models/structure-elements";
import {Observable} from "rxjs";
import {RequestListElement} from "../../models/request-elements";


@Injectable({
  providedIn: 'root'
})
export class RequestService {

    private requestPath = this.configuration.ServerWithApiUrl + '/request';
   

    constructor(private httpService: HttpService, private configuration: Configuration) { }


    getDevice(id:string){
        return this.httpService.get<StructureViewElement>(this.requestPath+'/'+id);
    }

    getAllRequests(type:string): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath+'/type/'+type);
    }

    getAllRequestsForLoggedUser(type:string): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath+"/user"+type);
    }
    
    getAllRequestsForOffice(type:string,id:string): Observable<RequestListElement[]> {
        return this.httpService.get<RequestListElement[]>(this.requestPath+"/office/"+type+'/'+id);
    }

    createRequest(data:StructureAddElement): Observable<any>{
        return this.httpService.post<StructureAddElement>(this.requestPath,data);
    }

    updateRequest(data: StructureEditElement): Observable<any> {
        return this.httpService.put<StructureEditElement>(this.requestPath, data);
    }

    deleteRequest(id :string): Observable<any>{
        return this.httpService.delete<any>(this.requestPath+'/'+id);
    }

   
}
