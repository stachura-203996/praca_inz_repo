import {Component, OnInit} from '@angular/core';
import {ChangeRequestStatusElement, RequestListElement} from "../../../models/request-elements";

import {TranslateService} from "@ngx-translate/core";
import {RequestService} from "../request.service";
import {Router} from "@angular/router";
import {UserRoles} from "../../../models/user-roles";
import {UserService} from "../../admin/components/administration/user-management/user.service";
import {Configuration} from "../../../app.constants";
import {MessageService} from "../../../shared/services/message.service";


@Component({
    selector: 'app-request-user',
    templateUrl: './request-user.component.html',
    styleUrls: ['./request-user.component.scss']
})
export class RequestUserComponent implements OnInit {


    yourRequest: RequestListElement[];
    employeesRequest: RequestListElement[];
    roles: UserRoles;
    changeRequestStatusElement: ChangeRequestStatusElement = new ChangeRequestStatusElement();

    constructor(
        private router: Router,
        private requestService: RequestService,
        private userService: UserService,
        private translate: TranslateService,
        private messageService: MessageService,
        private configuration: Configuration
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
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('request.cancel').subscribe(x => entity = x);
        this.translate.get('confirm.cancel').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.changeRequestStatusElement.id = request.id;
                    this.changeRequestStatusElement.version = request.version;
                    this.changeRequestStatusElement.status = this.configuration.REQUEST_STATUS_CANCELED;
                    this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep => {
                        this.getRequests();
                        this.translate.get('success.request.cancel').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }

    reject(request: RequestListElement) {

        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('request.reject').subscribe(x => entity = x);
        this.translate.get('confirm.reject').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.changeRequestStatusElement.id = request.id;
                    this.changeRequestStatusElement.version = request.version;
                    this.changeRequestStatusElement.status = this.configuration.REQUEST_STATUS_REJECTED;
                    this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep => {
                        this.router.navigateByUrl('/employees/confirmations/request/add/' + request.id)
                        this.translate.get('success.request.reject').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });

    }

    accept(request: RequestListElement) {

        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('request.accept').subscribe(x => entity = x);
        this.translate.get('confirm.accept').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.changeRequestStatusElement.id = request.id;
                    this.changeRequestStatusElement.version = request.version;
                    this.changeRequestStatusElement.status = this.configuration.REQUEST_STATUS_ACCEPTED;
                    this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep => {
                        this.router.navigateByUrl('/employees/confirmations/request/add/' + request.id)
                        this.translate.get('success.request.accept').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
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

    getStatus(status: string): string {
        var tmp: string;
        this.translate.get(status).subscribe(x => tmp = x);
        return tmp;
    }

    getType(type: string): string {
        var tmp: string;
        this.translate.get(type).subscribe(x => tmp = x);
        return tmp;
    }
}
