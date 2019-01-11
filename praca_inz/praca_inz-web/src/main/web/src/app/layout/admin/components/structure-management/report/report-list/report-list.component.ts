import {Component, OnInit} from '@angular/core';


import {TranslateService} from "@ngx-translate/core";
import {ReportListElement} from "../../../../../../models/report-elements";
import {ReportService} from "../../../../../employee-management/report.service";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";


@Component({
    selector: 'app-report-list',
    templateUrl: './report-list.component.html',
    styleUrls: ['./report-list.component.scss']
})
export class ReportListComponent implements OnInit {
    public deletedFilter = false;
    reports: ReportListElement[];


    constructor(
        private reportService: ReportService,
        private userService: UserService,
        private translate: TranslateService
    ) {
    }

    ngOnInit() {
        this.getReports();
    }

    getReports() {
            this.reportService.getAll().subscribe(reportListElement => {
                this.reports = reportListElement
            });
    }

    filterReports(searchText: string) {
            this.reportService.getAll().subscribe(reports => {
                if (!reports) {
                    this.reports = [];
                    return;
                }
                if (!searchText || searchText.length < 2) {
                    this.reports = reports;
                }

                searchText = searchText.toLowerCase();
                this.reports = reports.filter(it => {
                    const range = it.title + ' ' + it.sender + ' ' + it.receiver + ' ' + it.description + ' ' + it.reportDate;
                    const ok = range.toLowerCase().includes(searchText);
                    return ok;
                });
            });
    }

    delete(structure: ReportListElement) {
        this.reportService.deleteReport(String(structure.id)).subscribe(resp => {
            this.getReports()
        });
    }
}
