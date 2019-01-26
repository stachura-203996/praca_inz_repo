import {Injectable} from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {StructureAddElement, StructureEditElement, StructureViewElement} from "../../models/structure-elements";
import {
    ConfirmationAddElement,
    ConfirmationListElement,
    ConfirmationViewElement
} from "../../models/confirmation-elements";


@Injectable({
    providedIn: 'root'
})
export class ConfirmationService {

    private reportPath = this.configuration.ServerWithApiUrl + '/confirmation';

    constructor(private httpService: HttpService, private configuration: Configuration) {
    }


    getAll(): Observable<ConfirmationListElement[]> {
        return this.httpService.get<ConfirmationListElement[]>(this.reportPath);
    }

    getAllForCompany(id:number): Observable<ConfirmationListElement[]> {
        return this.httpService.get<ConfirmationListElement[]>(this.reportPath+'/company/'+id);
    }

    getAllForUser(): Observable<ConfirmationListElement[]> {
        return this.httpService.get<ConfirmationListElement[]>(this.reportPath + '/user');
    }

    getAllFromOthers(): Observable<ConfirmationListElement[]> {
        return this.httpService.get<ConfirmationListElement[]>(this.reportPath + '/others');
    }

    getReportView(id: string): Observable<ConfirmationViewElement> {
        return this.httpService.get<ConfirmationViewElement>(this.reportPath + '/' + id);
    }

    createReport(data: ConfirmationAddElement): Observable<any> {
        return this.httpService.post<ConfirmationAddElement>(this.reportPath, data);
    }

    deleteReport(id: string): Observable<any> {
        return this.httpService.delete<any>(this.reportPath + '/' + id);
    }

    disableBySender(id: string): Observable<any> {
        return this.httpService.delete<any>(this.reportPath + '/sender/' + id);
    }

    disableByReciever(id: string): Observable<any> {
        return this.httpService.delete<any>(this.reportPath + '/reciever/' + id);
    }
}
