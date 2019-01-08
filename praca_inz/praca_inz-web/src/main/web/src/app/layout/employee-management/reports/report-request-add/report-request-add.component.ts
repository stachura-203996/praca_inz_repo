import {Component, Input, OnInit} from '@angular/core';
import {ReportAddElement} from "../../../../models/report-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {ReportService} from "../../report.service";
import {TranslateService} from "@ngx-translate/core";
import {UserListElement} from "../../../../models/user-list-element";
import {RequestViewElement} from "../../../../models/request-elements";
import {RequestService} from "../../request.service";

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
        private router:Router
    ) {}

    ngOnInit() {
        this.getRecievers();
        this.getRequest();
    }

    getRequest(){
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x=>this.request=x);
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
        this.reportAddElement.reciever = this.recivers.get(this.request.username);
        this.reportAddElement.title=this.request.title+" - Manager report"
        this.reportService.createReport(this.reportAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/reports');
        });
    }

    clear() {
        this.reportAddElement=new ReportAddElement();
    }
}
