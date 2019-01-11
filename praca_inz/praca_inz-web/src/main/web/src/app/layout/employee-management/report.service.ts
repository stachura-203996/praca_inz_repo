import {Injectable} from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {ReportAddElement, ReportListElement, ReportViewElement} from "../../models/report-elements";
import {StructureAddElement, StructureEditElement, StructureViewElement} from "../../models/structure-elements";


@Injectable({
    providedIn: 'root'
})
export class ReportService {

    private reportPath = this.configuration.ServerWithApiUrl + '/report';

    constructor(private httpService: HttpService, private configuration: Configuration) {
    }


    getAll(): Observable<ReportListElement[]> {
        return this.httpService.get<ReportListElement[]>(this.reportPath);
    }

    getAllForCompany(id:number): Observable<ReportListElement[]> {
        return this.httpService.get<ReportListElement[]>(this.reportPath+'/company/'+id);
    }

    getAllForUser(): Observable<ReportListElement[]> {
        return this.httpService.get<ReportListElement[]>(this.reportPath + '/user');
    }

    getAllFromOthers(): Observable<ReportListElement[]> {
        return this.httpService.get<ReportListElement[]>(this.reportPath + '/others');
    }

    getReportView(id: string): Observable<ReportViewElement> {
        return this.httpService.get<ReportViewElement>(this.reportPath + '/' + id);
    }

    createReport(data: ReportAddElement): Observable<any> {
        return this.httpService.post<ReportAddElement>(this.reportPath, data);
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
