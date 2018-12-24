import {Component, OnInit} from '@angular/core';

import {UserListElement} from "../../../../../../../models/user-list-element";
import {ProfileInfo} from "../../../../../../../models/profile-info";
import {ProfileService} from "../../../../../../profile/profile.service";
import {SessionContextService} from "../../../../../../../shared/services/session-context.service";
import {ActivatedRoute} from "@angular/router";
import {UserInfo} from "../../../../../../../models/user-info";
import {UserService} from "../../user.service";
import {DeviceListElement} from "../../../../../../../models/device-list-element";
import {TransferListElement} from "../../../../../../../models/transfer-list-element";
import {DeviceService} from "../../../../../../device-management/device.service";


@Component({
    selector: 'app-profile-public',
    templateUrl: './user-view.component.html',
    styleUrls: ['./user-view.component.scss']
})
export class UserViewComponent implements OnInit {

    user: UserInfo;
    devices: DeviceListElement[];
    transfers: TransferListElement[];

    roles = {admin: false, employee: false, supervisor: false, benefit_manager: false};

    constructor(
        private route: ActivatedRoute,
        private userService: UserService,
        private deviceService:DeviceService,
        private sessionContextService: SessionContextService,
        ) {}

    ngOnInit() {
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

    getAddress(): string {
        if (this.user.flatNumber == null || this.user.flatNumber === "0") {
            return (this.user.street + ' ' + this.user.houseNumber);
        } else {
            return (this.user.street + ' ' + this.user.houseNumber + ' / ' + this.user.flatNumber);
        }
    }



getTransfersForUser()
{
    const username = this.route.snapshot.paramMap.get('username');
    this.deviceService.getAllTransfersForUser(username).subscribe(transferListElement => {
        this.transfers = transferListElement
    });
}

getDevicesForUser()
{
    const username = this.route.snapshot.paramMap.get('username');
    this.deviceService.getAllDevicesForUser(username).subscribe(deviceListElement => {
        this.devices = deviceListElement
    });
}
}
