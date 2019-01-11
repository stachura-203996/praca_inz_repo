import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {DeviceService} from "../device.service";
import {DeviceListElement} from "../../../models/device-elements";

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
        this.deviceService.getAllDevicesForLoggedUser().subscribe(deviceListElement=> {this.devices=deviceListElement});
    }

    filterDevices(searchText: string) {
        this.deviceService.getAllDevicesForLoggedUser().subscribe(devices => {
            if (!devices) {
                this.devices = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.devices = devices;
            }

            searchText = searchText.toLowerCase();
            this.devices = devices.filter(it => {
                const range = it.serialNumber+" "+it.name+" "+ it.deviceModel+" "+it.lastUpdate+ " " +it.deviceTypeName+ " "+ it.location+ " "+it.manufacture+ " "+it.username+ " "+ it.status;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

}
