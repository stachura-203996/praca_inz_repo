import {Component, OnInit} from '@angular/core';


import {TranslateService} from "@ngx-translate/core";
import {ReportListElement} from "../../../../../../models/report-elements";
import {ReportService} from "../../../../../employee-management/report.service";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";


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
        private translate: TranslateService,
        private configuration: Configuration,
        private messageService: MessageService

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

    getSender(report:ReportListElement){
        return report.senderName+" "+report.senderSurname+" | "+report.sender;
    }

    getReciever(report:ReportListElement){
        return report.recieverName+" "+report.recieverSurname+" | "+report.receiver;
    }


    delete(structure: ReportListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('report.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.reportService.deleteReport(String(structure.id)).subscribe(resp => {
                        this.getReports()
                        this.translate.get('success.report.delete').subscribe(x=>{
                            this.messageService.success(x)
                        })
                    }, error => {
                        if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
                            this.translate.get('no.object.in.database.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        } else {
                            this.translate.get('unknown.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        }

                    });
                }
            });
    }
}
