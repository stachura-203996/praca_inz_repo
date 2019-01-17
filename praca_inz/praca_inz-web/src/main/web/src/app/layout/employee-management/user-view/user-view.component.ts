import {Component, OnInit} from '@angular/core';

import {ActivatedRoute} from "@angular/router";
import {UserInfo} from "../../../models/user-info";
import {DeviceListElement} from "../../../models/device-elements";
import {TransferListElement} from "../../../models/transfer-list-element";
import {UserRoles} from "../../../models/user-roles";
import {UserService} from "../../admin/components/administration/user-management/user.service";
import {DeviceService} from "../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";



@Component({
    selector: 'app-profile-public',
    templateUrl: './user-view.component.html',
    styleUrls: ['./user-view.component.scss']
})
export class UserViewComponent implements OnInit {

    user: UserInfo;
    devices: DeviceListElement[];
    transfers: TransferListElement[];

    roles: UserRoles;


    constructor(
        private route: ActivatedRoute,
        private userService: UserService,
        private deviceService: DeviceService,
        private translate:TranslateService
    ) {
    }

    ngOnInit() {
        this.getLoggedUserRoles()
        this.getUserInfo();
        this.getDevicesForUser()
        this.getTransfersForUser()
    }

    getUserInfo() {
        const username = this.route.snapshot.paramMap.get('username');
        this.userService.getUserInfoToView(username).subscribe(user => {
            this.user = user
        });
    }


    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getAddress(): string {
        if (this.user.flatNumber == null || this.user.flatNumber === "0") {
            return (this.user.street + ' ' + this.user.houseNumber);
        } else {
            return (this.user.street + ' ' + this.user.houseNumber + ' / ' + this.user.flatNumber);
        }
    }

    getTransfersForUser() {
        const username = this.route.snapshot.paramMap.get('username');
        this.deviceService.getAllTransfersForUser(username).subscribe(transferListElement => {
            this.transfers = transferListElement
        });
    }

    getDevicesForUser() {
        const username = this.route.snapshot.paramMap.get('username');
        this.deviceService.getAllDevicesForUser(username).subscribe(deviceListElement => {
            this.devices = deviceListElement
        });
    }

    getAuthorityTranslation(authority:string):string{
        var tmp:string;
        this.translate.get(authority).subscribe(x=>tmp=x);
        return tmp;
    }
}
