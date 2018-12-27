import { Component, OnInit } from '@angular/core';
import {DeviceService} from "../../../../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {DeviceListElement, DeviceTypeListElement} from "../../../../../../models/device-elements";

@Component({
  selector: 'app-device-type-list',
  templateUrl: './device-type-list.component.html',
  styleUrls: ['./device-type-list.component.scss']
})
export class DeviceTypeListComponent implements OnInit {

    devicesTypes: DeviceTypeListElement[];

    constructor(private deviceService : DeviceService,
                private translate:TranslateService) {
    }

    ngOnInit() {
        this.getDevicesTypes()
    }


    getDevicesTypes(){
        this.deviceService.getAllDevicesTypes().subscribe(deviceListElement=> {this.devicesTypes=deviceListElement});
    }

    filterDevicesTypes(searchText: string) {
        this.deviceService.getAllDevices().subscribe(devicesTypes => {
            if (!devicesTypes) {
                this.devicesTypes = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.devicesTypes = devicesTypes;
            }

            searchText = searchText.toLowerCase();
            this.devicesTypes = devicesTypes.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(device: DeviceListElement) {
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
