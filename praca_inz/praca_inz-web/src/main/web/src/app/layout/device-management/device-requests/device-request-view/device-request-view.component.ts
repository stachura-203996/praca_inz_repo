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
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../../shared/services/message.service";

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
        private userService:UserService,
        private deviceService:DeviceService,
        private translate: TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private  router:Router
    ) {}

    ngOnInit() {
        this.getRequest();
        this.getLoggedUser();
        this.getLoggeduserRoles();

    }

    getUserInfo():string{
        return this.request.name+" "+this.request.surname+" | "+this.request.username;
    }

    getLoggeduserRoles(){
        this.userService.getLoggedUserRoles().subscribe(x=>{this.roles=x}, error => {
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

    getLoggedUser(){
        this.userService.getLoggedUser().subscribe(x=>{this.currentUser=x}, error => {
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

    getRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x =>{ this.request = x});
    }

    cancel(){
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.request.cancel').subscribe(x => entity = x);
        this.translate.get('confirm.cancel').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.changeRequestStatusElement.id=this.request.id;
                    this.changeRequestStatusElement.version=this.request.version;
                    this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_CANCELED;
                    this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
                        this.router.navigateByUrl('')
                        this.translate.get('success.device.request.cancel').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }

    reject() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.request.reject').subscribe(x => entity = x);
        this.translate.get('confirm.cancel').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.changeRequestStatusElement.id=this.request.id;
                    this.changeRequestStatusElement.version=this.request.version;
                    this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_REJECTED;
                    this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
                        this.router.navigateByUrl('/employees/reports/request/add/'+this.request.id)
                        this.translate.get('success.device.request.rejected').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });

    }

    accept() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.request.accept').subscribe(x => entity = x);
        this.translate.get('confirm.cancel').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.changeRequestStatusElement.id=this.request.id;
                    this.changeRequestStatusElement.version=this.request.version;
                    this.changeRequestStatusElement.status=this.configuration.REQUEST_STATUS_ACCEPTED;
                    this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep=>{
                        this.router.navigateByUrl('/employees/reports/request/add/'+this.request.id)
                        this.translate.get('success.device.request.accept').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });

    }
}
