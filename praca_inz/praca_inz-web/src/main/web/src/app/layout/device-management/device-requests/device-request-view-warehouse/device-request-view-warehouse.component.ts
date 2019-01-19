import {Component, OnInit} from '@angular/core';
import {ChangeRequestStatusElement, RequestViewElement} from "../../../../models/request-elements";
import {UserRoles} from "../../../../models/user-roles";
import {LoggedUser} from "../../../../models/logged-user";
import {DeviceListElement} from "../../../../models/device-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {RequestService} from "../../../employee-management/request.service";
import {Configuration} from "../../../../app.constants";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {DeviceService} from "../../device.service";
import {FormControl} from "@angular/forms";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../../shared/services/message.service";

@Component({
    selector: 'app-device-request-view-warehouse',
    templateUrl: './device-request-view-warehouse.component.html',
    styleUrls: ['./device-request-view-warehouse.component.scss']
})
export class DeviceRequestViewWarehouseComponent implements OnInit {

    request: RequestViewElement;
    changeRequestStatusElement: ChangeRequestStatusElement = new ChangeRequestStatusElement();
    roles: UserRoles;
    currentUser: LoggedUser;

    selectedOption;
    selectedOptions = [];
    devices = new Map<string, number>();
    devicesIds=[];

    constructor(
        private route: ActivatedRoute,
        private requestService: RequestService,
        private configuration: Configuration,
        private userService: UserService,
        private deviceService: DeviceService,
        private translate: TranslateService,
        private messageService: MessageService,
        private  router: Router
    ) {
    }

    ngOnInit() {
        this.getRequest();
        this.getLoggedUser();
        this.getLoggeduserRoles();
        this.getDevices(this.request.deviceModelName);
    }

    getUserInfo():string{
        return this.request.name+" "+this.request.surname+" | "+this.request.username;
    }

    add() {
        if (!this.selectedOptions.some(x => x.option == this.selectedOption)) {
            if (this.selectedOptions.length != this.request.amount) {
                this.selectedOptions.push({option: this.selectedOption});
            }
        } else {
            this.translate.get('this.device.is.on.the.list').subscribe(x => {
                this.messageService.error(x)
            })
        }
    }

    delete(name) {
        for (var i = 0; i < this.selectedOptions.length; i++) {
            if (this.selectedOptions[i]["option"] == name) {
                this.selectedOptions.splice(i, 1);
            }
        }

    }

    isOnList(value: string) {
        return this.selectedOptions.some(x => x.option == value);
    }

    getDevices(deviceModel:string) {
        this.deviceService.getAllDevicesForWarehouseman().subscribe((response: DeviceListElement[]) => {
            this.devices = response.reduce(function (deviceMap, device) {
                if (device.id && device.deviceModel==deviceModel) {
                    deviceMap.set(device.deviceModel + " |   " + device.serialNumber, device.id)
                }
                return deviceMap;
            }, this.devices);
        });

    }

    getLoggeduserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x => this.request = x);
    }

    reject() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.request.reject').subscribe(x => entity = x);
        this.translate.get('confirm.reject').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.changeRequestStatusElement.id = this.request.id;
                    this.changeRequestStatusElement.version = this.request.version;
                    this.changeRequestStatusElement.status = this.configuration.REQUEST_STATUS_REJECTED;
                    this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep => {
                        this.router.navigateByUrl('/employees/reports/request/add/' + this.request.id)
                        this.translate.get('success.device.request.rejected').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });

    }

    submit() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.request.accept').subscribe(x => entity = x);
        this.translate.get('confirm.accept').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    for (var i = 0; i < this.selectedOptions.length; i++) {
                        this.devicesIds.push(this.devices.get( this.selectedOptions[i]["option"]));
                    }
                    const id = this.route.snapshot.paramMap.get('id');
                    this.changeRequestStatusElement.id = this.request.id;
                    this.changeRequestStatusElement.version = this.request.version;
                    this.changeRequestStatusElement.status = this.configuration.REQUEST_STATUS_TO_TAKE_AWAY;
                    this.requestService.addDevicesToRequest(this.devicesIds, Number(id)).subscribe(rep => {
                        this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep => {
                            this.router.navigateByUrl('/employees/reports/request/add/' + this.request.id)
                        });
                        this.translate.get('success.device.request.accept').subscribe(x => {
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
