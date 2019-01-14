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
import {StructureListElement} from "../../../../models/structure-elements";

@Component({
  selector: 'app-report-add',
  templateUrl: './report-add.component.html',
  styleUrls: ['./report-add.component.scss']
})
export class ReportAddComponent implements OnInit {

    @Input() reportAddElement: ReportAddElement= new ReportAddElement();

     recivers=new Map<string, number>();
     selectedOption: string;

    constructor(private route: ActivatedRoute,private userService:UserService,private reportService:ReportService,private translate:TranslateService,private router:Router) {}

    ngOnInit() {
        this.getRecievers();
    }


    getRecievers() {
        this.userService.getAll().subscribe((response: UserListElement[]) => {
            this.recivers = response.reduce(function(userMap, user){
                if(user.id){
                    userMap.set(user.name+" "+user.surname+" | "+user.username,user.id)
                }
                return userMap;
            },this.recivers);
        });

    }


    reportAdd(){
        this.reportAddElement.reciever = this.recivers.get(this.selectedOption);
        this.reportService.createReport(this.reportAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/reports');
        });
    }

    clear() {
        this.reportAddElement=new ReportAddElement();
    }
}
