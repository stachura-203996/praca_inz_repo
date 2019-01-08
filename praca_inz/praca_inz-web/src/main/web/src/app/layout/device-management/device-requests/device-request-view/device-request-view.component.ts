import { Component, OnInit } from '@angular/core';
import {ChangeRequestStatusElement, RequestViewElement} from "../../../../models/request-elements";
import {UserRoles} from "../../../../models/user-roles";
import {LoggedUser} from "../../../../models/logged-user";
import {DeviceListElement} from "../../../../models/device-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {RequestService} from "../../../employee-management/request.service";
import {Configuration} from "../../../../app.constants";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {DeviceService} from "../../device.service";

@Component({
  selector: 'app-device-request-view',
  templateUrl: './device-request-view.component.html',
  styleUrls: ['./device-request-view.component.scss']
})
export class DeviceRequestViewComponent implements OnInit {

    request: RequestViewElement;
    changeRequestStatusElement:ChangeRequestStatusElement=new ChangeRequestStatusElement();
    roles:UserRoles;
    currentUser:LoggedUser;
    devices:DeviceListElement[];

    constructor(
        private route: ActivatedRoute,
        private requestService: RequestService,
        private configuration:Configuration,
        private userService:UserService,
        private deviceService:DeviceService,
        private  router:Router
    ) {}

    ngOnInit() {
        this.getRequest();
        this.getLoggedUser();
        this.getLoggeduserRoles();

    }

    getLoggeduserRoles(){
        this.userService.getLoggedUserRoles().subscribe(x=>this.roles=x);
    }

    getLoggedUser(){
        this.userService.getLoggedUser().subscribe(x=>this.currentUser=x);
    }

    getRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x => this.request = x);
    }

    cancel(){
        this.changeRequestStatusElement.id=this.request.id;
        this.changeRequestStatusElement.version=this.request.version;
        this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_CANCELED;
        this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
            this.router.navigateByUrl('')
        });
    }

    reject() {
        this.changeRequestStatusElement.id=this.request.id;
        this.changeRequestStatusElement.version=this.request.version;
        this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_REJECTED;
        this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
            this.router.navigateByUrl('/employees/reports/request/add/'+this.request.id)
        });

    }

    accept() {
        this.changeRequestStatusElement.id=this.request.id;
        this.changeRequestStatusElement.version=this.request.version;
        this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_ACCEPTED;
        this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
            this.router.navigateByUrl('/employees/reports/request/add/'+this.request.id)
        });

    }
}
