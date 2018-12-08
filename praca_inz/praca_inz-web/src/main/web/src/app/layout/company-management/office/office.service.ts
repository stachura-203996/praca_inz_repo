import { Injectable } from '@angular/core';
import {HttpService} from "../../../shared/services/http.service";
import {Configuration} from "../../../app.constants";
import {Observable} from "rxjs";
import {CompanyListElement} from "../company/models/company-list-element";

@Injectable({
  providedIn: 'root'
})
export class OfficeService {

    private companyPath = this.configuration.ServerWithApiUrl + '/structure/office';
    private userPathDetail;
    private activePath = '/active';
    private inactivePath = '/inactive';
    private verifiedPath = '/verified';


    constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAll(): Observable<CompanyListElement[]> {
        return this.httpService.get<CompanyListElement[]>(this.companyPath);
    }
}
