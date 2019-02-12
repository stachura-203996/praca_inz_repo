import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RequestService} from "../../../employee-management/request.service";
import {ChangeRequestStatusElement, RequestViewElement} from "../../../../models/request-elements";
import {Configuration} from "../../../../app.constants";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {UserRoles} from "../../../../models/user-roles";
import {LoggedUser} from "../../../../models/logged-user";
import {DeviceListElement} from "../../../../models/device-elements";
import {DeviceService} from "../../device.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../../shared/services/message.service";

@Component({
  selector: 'app-transfer-request-view',
  templateUrl: './transfer-request-view.component.html',
  styleUrls: ['./transfer-request-view.component.scss']
})
export class TransferRequestViewComponent implements OnInit {

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
        private translate: TranslateService,
        private messageService: MessageService,
        private  router:Router
    ) {}

    ngOnInit() {
        this.getRequest();
        this.getLoggedUser();
        this.getLoggeduserRoles();
        this.getDevices()
    }

    getUserInfo():string{
        return this.request.name+" "+this.request.surname+" | "+this.request.username;
    }

    getLoggeduserRoles(){
        this.userService.getLoggedUserRoles().subscribe(x=>this.roles=x);
    }

    getLoggedUser(){
        this.userService.getLoggedUser().subscribe(x=>this.currentUser=x);
    }

    getDevices(){
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getAllDevicesForRequest(Number(id)).subscribe(x=>this.devices=x);
    }

    getRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x => this.request = x);
    }

    cancel(){
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('transfer.request.cancel').subscribe(x => entity = x);
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
                        this.translate.get('success.transfer.request.cancel').subscribe(x => {
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

        this.translate.get('transfer.request.reject').subscribe(x => entity = x);
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
                        this.router.navigateByUrl('/employees/confirmations/request/add/'+this.request.id)
                        this.translate.get('success.transfer.request.reject').subscribe(x => {
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

        this.translate.get('transfer.request.accept').subscribe(x => entity = x);
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
                        this.router.navigateByUrl('/employees/confirmations/request/add/'+this.request.id)
                        this.translate.get('success.transfer.request.accept').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });

    }

    translateAll(word: string): string {
        var tmp: string;
        this.translate.get(word).subscribe(x => tmp = x);
        return tmp;
    }

}
