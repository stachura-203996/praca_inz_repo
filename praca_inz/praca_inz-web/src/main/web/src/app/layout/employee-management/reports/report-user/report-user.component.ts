import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ReportListElement} from "../../../../models/report-elements";
import {ReportService} from "../../report.service";
import {MessageService} from "../../../../shared/services/message.service";
import {Configuration} from "../../../../app.constants";

@Component({
    selector: 'app-report',
    templateUrl: './report-user.component.html',
    styleUrls: ['./report-user.component.scss']
})
export class ReportUserComponent implements OnInit {

    public deletedFilter = false;
    reports: ReportListElement[];
    otherReports: ReportListElement[];


    constructor(
        private reportService: ReportService,
        private messageService: MessageService,
        private configuration: Configuration,
        private translate: TranslateService) {}

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
                    this.reportService.disableBySender(String(structure.id)).subscribe(resp => {
                        this.getSendedReportsForUser();
                        this.getReciveddReportsForUser();
                        this.translate.get('success.report.delete').subscribe(x => {
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

    disableByReciever(structure: ReportListElement) {

                    var entity: string;
                    var message: string;
                    var yes: string;
                    var no: string;

                    this.translate.get('department.add').subscribe(x => entity = x);
                    this.translate.get('confirm.add').subscribe(x => message = x);
                    this.translate.get('yes').subscribe(x => yes = x);
                    this.translate.get('no').subscribe(x => no = x);


                    this.messageService
                        .confirm(entity, message, yes, no)
                        .subscribe(confirmed => {
                            if (confirmed) {
                                    this.reportService.disableByReciever(String(structure.id)).subscribe(resp => {
                                        this.getSendedReportsForUser();
                                        this.getReciveddReportsForUser();
                                        this.translate.get('success.report.delete').subscribe(x => {
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
