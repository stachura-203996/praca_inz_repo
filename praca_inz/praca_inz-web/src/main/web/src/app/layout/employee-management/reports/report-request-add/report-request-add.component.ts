import {Component, Input, OnInit} from '@angular/core';
import {ReportAddElement} from "../../../../models/report-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {ReportService} from "../../report.service";
import {TranslateService} from "@ngx-translate/core";
import {UserListElement} from "../../../../models/user-list-element";
import {RequestViewElement} from "../../../../models/request-elements";
import {RequestService} from "../../request.service";
import {MessageService} from "../../../../shared/services/message.service";
import {Configuration} from "../../../../app.constants";

@Component({
  selector: 'app-report-request-add',
  templateUrl: './report-request-add.component.html',
  styleUrls: ['./report-request-add.component.scss']
})
export class ReportRequestAddComponent implements OnInit {

    @Input() reportAddElement: ReportAddElement= new ReportAddElement();

    request:RequestViewElement;
    recivers=new Map<string, number>();
    selectedOption: string;

    constructor(
        private route: ActivatedRoute,
        private userService:UserService,
        private reportService:ReportService,
        private requestService:RequestService,
        private translate:TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router:Router
    ) {}

    ngOnInit() {
        this.getRecievers();
        this.getRequest();
    }

    getRequest(){
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x=>{this.request=x}, error => {
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

    getRecievers() {
        this.userService.getAll().subscribe((response: UserListElement[]) => {
            this.recivers = response.reduce(function(companyMap, company){
                if(company.id){
                    companyMap.set(company.username,company.id)
                }
                return companyMap;
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
                    this.reportAddElement.reciever = this.recivers.get(this.request.username);
                    if(this.request.status=="IN_WAREHOUSE"){
                        this.reportAddElement.title=this.request.title+" - Manager report"
                    } else{
                        this.reportAddElement.title=this.request.title+" - Warehouseman report"
                    }
                    this.reportService.createReport(this.reportAddElement).subscribe(resp => {
                        this.router.navigateByUrl('/employees/reports');
                        this.translate.get('success.report.add').subscribe(x => {
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

    clear() {
        this.reportAddElement=new ReportAddElement();
    }
}
