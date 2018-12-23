import {Component, OnInit} from '@angular/core';
import {StructureListElement} from "../../admin/components/entity-management/models/structure-list-element";
import {DeviceListElement} from "../models/device-list-element";
import {CompanyService} from "../../admin/components/entity-management/company/company.service";
import {TranslateService} from "@ngx-translate/core";
import {DeviceService} from "../device.service";

@Component({
    selector: 'app-device-user',
    templateUrl: './device-user.component.html',
    styleUrls: ['./device-user.component.scss']
})
export class DeviceUserComponent implements OnInit {


    devices: DeviceListElement[];

    constructor(private deviceService : DeviceService,
                private translate:TranslateService) {
    }

    ngOnInit() {
        this.getDevicesForLoggedUser()
    }


    getDevicesForLoggedUser(){
        this.deviceService.getAllDevicesForUser().subscribe(deviceListElement=> {this.devices=deviceListElement});
    }

    filterDevices(searchText: string) {
        this.deviceService.getAllDevicesForUser().subscribe(devices => {
            if (!devices) {
                this.devices = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.devices = devices;
            }

            searchText = searchText.toLowerCase();
            this.devices = devices.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
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
