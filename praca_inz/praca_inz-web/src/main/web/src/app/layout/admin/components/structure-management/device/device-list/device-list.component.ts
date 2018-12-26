import { Component, OnInit } from '@angular/core';

import {TranslateService} from "@ngx-translate/core";
import {DeviceListElement} from "../../../../../../models/device-list-element";
import {DeviceService} from "../../../../../device-management/device.service";


@Component({
  selector: 'app-device-list',
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.scss']
})
export class DeviceListComponent implements OnInit {


    devices: DeviceListElement[];

    constructor(private deviceService : DeviceService,
                private translate:TranslateService) {
    }

    ngOnInit() {
        this.getDevices()
    }


    getDevices(){
        this.deviceService.getAllDevices().subscribe(deviceListElement=> {this.devices=deviceListElement});
    }

    filterDevices(searchText: string) {
        this.deviceService.getAllDevices().subscribe(devices => {
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
