import {Injectable} from '@angular/core';
import {HttpService} from "../../shared/services/http.service";
import {Configuration} from "../../app.constants";
import {Observable} from "rxjs";
import {ReportListElement} from "../../models/report-elements";
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

    getAllForUser(): Observable<ReportListElement[]> {
        return this.httpService.get<ReportListElement[]>(this.reportPath + '/user');
    }

    getAllFromOthers(): Observable<ReportListElement[]> {
        return this.httpService.get<ReportListElement[]>(this.reportPath + '/others');
    }

    getReportEdit(id: string): Observable<StructureEditElement> {
        return this.httpService.get<StructureEditElement>(this.reportPath + '/' + id);
    }

    getReportView(id: string): Observable<StructureViewElement> {
        return this.httpService.get<StructureViewElement>(this.reportPath + '/' + id);
    }

    createReport(data: StructureAddElement): Observable<any> {
        return this.httpService.post<StructureAddElement>(this.reportPath, data);
    }

    updateReport(data: StructureEditElement): Observable<any> {
        return this.httpService.put<StructureEditElement>(this.reportPath, data);
    }

    deleteDepartament(id: string): Observable<any> {
        return this.httpService.delete<any>(this.reportPath + '/' + id);
    }
}
