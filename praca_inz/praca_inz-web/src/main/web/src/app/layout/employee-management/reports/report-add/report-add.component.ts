import {Component, Input, OnInit} from '@angular/core';

import {ActivatedRoute, Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";
import {ReportAddElement} from "../../../../models/report-elements";
import {ReportService} from "../../report.service";
import {UserListElement} from "../../../../models/user-list-element";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {StructureListElement} from "../../../../models/structure-elements";
import {MessageService} from "../../../../shared/services/message.service";
import {Configuration} from "../../../../app.constants";

@Component({
  selector: 'app-report-add',
  templateUrl: './report-add.component.html',
  styleUrls: ['./report-add.component.scss']
})
export class ReportAddComponent implements OnInit {

    @Input() reportAddElement: ReportAddElement= new ReportAddElement();

     recivers=new Map<string, number>();
     selectedOption: string;

    constructor(
        private route: ActivatedRoute,
        private userService:UserService,
        private reportService:ReportService,
        private translate:TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router:Router) {}

    ngOnInit() {
        this.getRecievers();
    }

    getRecievers() {
        this.userService.getAllForReport().subscribe((response: UserListElement[]) => {
            this.recivers = response.reduce(function(userMap, user){
                if(user.id){
                    userMap.set(user.name+" "+user.surname+" | "+user.username,user.id)
                }
                return userMap;
            },this.recivers);
        });

    }

    reportAdd(){
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('report.add').subscribe(x => entity = x);
        this.translate.get('confirm.add').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                        this.reportAddElement.reciever = this.recivers.get(this.selectedOption);
                        this.reportService.createReport(this.reportAddElement).subscribe(resp => {
                            this.router.navigateByUrl('/employees/reports');
                            this.translate.get('success.report.add').subscribe(x => {
                                this.messageService.success(x)
                            })
                        });
                }
            });
    }

    clear() {
        this.reportAddElement=new ReportAddElement();
    }
}
