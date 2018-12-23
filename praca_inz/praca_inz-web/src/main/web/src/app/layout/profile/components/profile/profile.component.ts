import { Component, OnInit } from '@angular/core';
import {ProfileInfo} from "../../models/profile-info";
import {ProfileService} from "../../profile.service";

import {SessionContextService} from "../../../../shared/services/session-context.service";
import {DeviceService} from "../../../device-management/device.service";
import {DeviceListComponent} from "../../../admin/components/entity-management/device/device-list/device-list.component";
import {DeviceListElement} from "../../../device-management/models/device-list-element";
import {TransferListElement} from "../../../device-management/models/transfer-list-element";

@Component({
  selector: 'app-profile-private',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

    user: ProfileInfo;
    devices: DeviceListElement[];
    transfers: TransferListElement[];
    isUserLoggedIn = this.sessionContextService.getUser() !== null;


  constructor(private profileService:ProfileService, private sessionContextService:SessionContextService,private deviceService : DeviceService ) { }

    ngOnInit() {
        this.getProfile();
        this.getDevicesForLoggedUser()
        this.getTransfersForLoggedUser()
    }

    getProfile() {
        this.profileService.getUserProfile().subscribe(profile => {
            this.user = profile
        });
    }

    getTransfersForLoggedUser(){
        this.deviceService.getAllTransfersForUser().subscribe(transferListElement=> {this.transfers=transferListElement});
    }

    getDevicesForLoggedUser(){
        this.deviceService.getAllDevicesForUser().subscribe(deviceListElement=> {this.devices=deviceListElement});
    }

    getAddress(): string {
        if (this.user.flatNumber == null || this.user.flatNumber === "0") {
            return (this.user.street + ' ' + this.user.houseNumber);
        } else {
            return (this.user.street + ' ' + this.user.houseNumber + ' / ' + this.user.flatNumber);
        }
    }

    transfer(device: DeviceListElement) {
        // const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        // modalRef.componentInstance.user = user;
        // modalRef.result.then(
        //     result => {
        //         // Left blank intentionally, nothing to do here
        //     },
        //     reason => {
        //         // Left blank intentionally, nothing to do here
        //     }
        // );
    }
}
