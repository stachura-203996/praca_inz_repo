import { Injectable } from '@angular/core';
import {HttpService} from "../../../shared/services/http.service";
import {Configuration} from "../../../app.constants";
import {Observable} from "rxjs";
import {StructureListElement} from "../models/structure-list-element";

@Injectable({
  providedIn: 'root'
})
export class OfficeService {

    private officePath = this.configuration.ServerWithApiUrl + '/structure/office';

    constructor(private httpService: HttpService, private configuration: Configuration) { }

    getAll(): Observable<StructureListElement[]> {
        return this.httpService.get<StructureListElement[]>(this.officePath);
    }
}
