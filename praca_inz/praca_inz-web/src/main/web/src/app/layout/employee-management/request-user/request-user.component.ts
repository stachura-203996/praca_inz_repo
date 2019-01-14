import {Component, OnInit} from '@angular/core';
import {ChangeRequestStatusElement, RequestListElement} from "../../../models/request-elements";

import {TranslateService} from "@ngx-translate/core";
import {RequestService} from "../request.service";
import {Router} from "@angular/router";
import {UserRoles} from "../../../models/user-roles";
import {UserService} from "../../admin/components/administration/user-management/user.service";
import {Configuration} from "../../../app.constants";


@Component({
    selector: 'app-request-user',
    templateUrl: './request-user.component.html',
    styleUrls: ['./request-user.component.scss']
})
export class RequestUserComponent implements OnInit {


    yourRequest: RequestListElement[];
    employeesRequest: RequestListElement[];
    roles: UserRoles;
    changeRequestStatusElement:ChangeRequestStatusElement=new ChangeRequestStatusElement();

    constructor(
        private router: Router,
        private requestService: RequestService,
        private userService: UserService,
        private translate: TranslateService,
        private configuration:Configuration
    ) {
    }

    ngOnInit() {
        this.getRequests();
        this.getLoggedUserRoles();
    }

    getRequests() {
        this.requestService.getAllRequestsForLoggedUser().subscribe(requests => {
            this.yourRequest = requests
        });
        this.requestService.getAllRequestsForManager().subscribe(requests => {
            this.employeesRequest = requests
        });
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    cancel(request: RequestListElement) {
        this.changeRequestStatusElement.id=request.id;
        this.changeRequestStatusElement.version=request.version;
        this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_CANCELED;
        this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
            this.getRequests();
        });
    }

    reject(request: RequestListElement) {
                this.changeRequestStatusElement.id=request.id;
                this.changeRequestStatusElement.version=request.version;
                this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_REJECTED;
                this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
                    this.router.navigateByUrl('/employees/reports/request/add/'+request.id)
                });

    }

    accept(request: RequestListElement) {
                this.changeRequestStatusElement.id=request.id;
                this.changeRequestStatusElement.version=request.version;
                this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_ACCEPTED;
                this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
                    this.router.navigateByUrl('/employees/reports/request/add/'+request.id)

                });

    }

    viewPage(request: RequestListElement) {
        switch (request.type) {
            case this.configuration.DEVICE_REQUEST: {
                this.router.navigateByUrl('/page/devices/request/view/' + request.id);
                break;
            }
            case this.configuration.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/devices/transfer/request/view/' + request.id);
                break;
            }
        }
    }

    editPage(request: RequestListElement) {
        switch (request.type) {
            case this.configuration.DEVICE_REQUEST: {
                this.router.navigateByUrl('/page/devices/request/edit/' + request.id);
                break;
            }
            case this.configuration.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/page/devices/transfer/request/edit/' + request.id);
                break;
            }
        }
    }
}
