import { Component, OnInit } from '@angular/core';


import {TranslateService} from "@ngx-translate/core";
import {ReportListElement} from "../../../../../../models/report-elements";
import {ReportService} from "../../../../../employee-management/report.service";


@Component({
  selector: 'app-report-list',
  templateUrl: './report-list.component.html',
  styleUrls: ['./report-list.component.scss']
})
export class ReportListComponent implements OnInit {
    public deletedFilter = false;
    reports: ReportListElement[];

    constructor(private reportService : ReportService, private translate:TranslateService)
    {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');

    }

    ngOnInit() {
        // this.filterUsers(null);
        this.getReports();
    }



    getReports(){
        this.reportService.getAll().subscribe(reportListElement=> {this.reports=reportListElement});
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
                const range = it.title+ ' ' + it.sender+' '+it.reciever+ ' ' + it.description+ ' ' + it.reportDate;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });

    }

    delete(structure: ReportListElement) {
        this.reportService.deleteDepartament(String(structure.id)).subscribe(resp => {
            this.getReports()
        });
    }
}
