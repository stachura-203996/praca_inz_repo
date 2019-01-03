import {Component, Input, OnInit} from '@angular/core';

import {DeviceModelListElement} from "../../../../models/device-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceService} from "../../../device-management/device.service";
import {RequestService} from "../../request.service";
import {TranslateService} from "@ngx-translate/core";
import {ReportAddElement} from "../../../../models/report-elements";
import {ReportService} from "../../report.service";
import {UserListElement} from "../../../../models/user-list-element";
import {UserService} from "../../../admin/components/administration/user-management/user.service";

@Component({
  selector: 'app-report-add',
  templateUrl: './report-add.component.html',
  styleUrls: ['./report-add.component.scss']
})
export class ReportAddComponent implements OnInit {

    @Input() reportAddElement: ReportAddElement= new ReportAddElement();

     recivers: UserListElement[];
     selectedOption: string;

    constructor(private route: ActivatedRoute,private userService:UserService,private reportService:ReportService,private translate:TranslateService,private router:Router) {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getRecievers();
    }

    getRecievers(){
        this.userService.getAll().subscribe(deviceModels=> {this.recivers=deviceModels});
    }

    reportAdd(){
        this.reportService.createReport(this.reportAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.reportAddElement=new ReportAddElement();
    }
}
