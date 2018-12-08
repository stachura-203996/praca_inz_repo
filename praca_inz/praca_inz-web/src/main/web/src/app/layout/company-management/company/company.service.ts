import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {UserListElement} from "../../user-management/models/user-list-element";
import {UserEdit} from "../../user-management/models/user-edit";
import {AccountLevel} from "../../user-management/models/account-level";
import {HttpService} from "../../../shared/services/http.service";
import {Configuration} from "../../../app.constants";
import {CompanyListElement} from "./models/company-list-element";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

    private companyPath = this.configuration.ServerWithApiUrl + '/structure/company';
    private userPathDetail;
    private activePath = '/active';
    private inactivePath = '/inactive';
    private verifiedPath = '/verified';


  constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAll(): Observable<CompanyListElement[]> {
        return this.httpService.get<CompanyListElement[]>(this.companyPath);
    }

}
