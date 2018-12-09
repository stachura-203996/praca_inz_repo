import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpService} from "../../../shared/services/http.service";
import {Configuration} from "../../../app.constants";
import {StructureListElement} from "../models/structure-list-element";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

    private companyPath = this.configuration.ServerWithApiUrl + '/structure/company';

  constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAll(): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.companyPath);
    }

}
