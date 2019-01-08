import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ReportListElement} from "../../../../models/report-elements";
import {ReportService} from "../../report.service";

@Component({
    selector: 'app-report',
    templateUrl: './report-user.component.html',
    styleUrls: ['./report-user.component.scss']
})
export class ReportUserComponent implements OnInit {

    public deletedFilter = false;
    reports: ReportListElement[];
    otherReports: ReportListElement[];


    constructor(private reportService: ReportService, private translate: TranslateService) {}

    ngOnInit() {
        this.getSendedReportsForUser();
        this.getReciveddReportsForUser();
    }

    getSendedReportsForUser() {
        this.reportService.getAllForUser().subscribe(reportListElement => {
            this.reports = reportListElement
        });
    }

    getReciveddReportsForUser() {
        this.reportService.getAllFromOthers().subscribe(reportListElement => {
            this.otherReports = reportListElement
        });
    }

    disableBySender(structure: ReportListElement) {
        this.reportService.disableBySender(String(structure.id)).subscribe(resp => {
            this.getSendedReportsForUser();
            this.getReciveddReportsForUser();
        });
    }

    disableByReciever(structure: ReportListElement) {
        this.reportService.disableByReciever(String(structure.id)).subscribe(resp => {
            this.getSendedReportsForUser();
            this.getReciveddReportsForUser();
        });
    }
}
