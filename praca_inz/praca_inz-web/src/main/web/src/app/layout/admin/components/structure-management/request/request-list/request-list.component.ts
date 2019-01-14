import {Component, OnInit} from '@angular/core';
import {StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {RequestListElement} from "../../../../../../models/request-elements";
import {Router} from "@angular/router";
import {RequestService} from "../../../../../employee-management/request.service";
import {UserService} from "../../../administration/user-management/user.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";

@Component({
    selector: 'app-request-list',
    templateUrl: './request-list.component.html',
    styleUrls: ['./request-list.component.scss']
})
export class RequestListComponent implements OnInit {

    deviceRequests: RequestListElement[];
    transferRequests: RequestListElement[];

    DEVICE_REQUEST: string = "DEVICE_REQUEST";
    TRANSFER_REQUEST: string = "TRANSFER_REQUEST";

    constructor(
        private requestService: RequestService,
        private userService: UserService,
        private translate: TranslateService,
        private configuration: Configuration,
        private messageService: MessageService,
        private router: Router) {

    }

    ngOnInit() {
        this.getRequests();
    }

    getRequests() {

        this.requestService.getAllRequests(this.DEVICE_REQUEST).subscribe(requests => {
            this.deviceRequests = requests
        });
        this.requestService.getAllRequests(this.TRANSFER_REQUEST).subscribe(requests => {
            this.transferRequests = requests
        });
    }

    getAddress(office: StructureListElement): string {
        if (office.flatNumber == null || office.flatNumber === "0") {
            return (office.street + ' ' + office.buildingNumber);
        } else {
            return (office.street + ' ' + office.buildingNumber + ' / ' + office.flatNumber);
        }
    }

    viewPage(request: RequestListElement) {
        switch (request.type) {
            case this.DEVICE_REQUEST: {
                this.router.navigateByUrl('/ui/page/devices/request/view/' + request.id);
                break;
            }
            case this.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/ui/page/devices/request/transfer/view/' + request.id);
                break;
            }
        }
    }

    editPage(request: RequestListElement) {
        switch (request.type) {
            case this.DEVICE_REQUEST: {
                this.router.navigateByUrl('/ui/page/devices/request/edit/' + request.id);
                break;
            }
            case this.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/ui/page/devices/transfer/request/edit/' + request.id);
                break;
            }
        }
    }

    cancel(structure: RequestListElement) {

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
                    this.requestService.cancelRequest(structure).subscribe(resp => {
                        this.getRequests();
                        this.translate.get('success.request.cancel').subscribe(x=>{
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

    delete(structure: RequestListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('request.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.requestService.deleteRequest(String(structure.id)).subscribe(resp => {
                        this.getRequests()
                        this.translate.get('success.request.delete').subscribe(x=>{
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

    getStatus(status:string):string{
        var tmp:string;
        this.translate.get(status).subscribe(x=>tmp=x);
        return tmp;
    }


}
