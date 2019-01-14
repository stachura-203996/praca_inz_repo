import { Component, OnInit } from '@angular/core';
import {DeviceListElement} from "../../../models/device-elements";
import {DeviceService} from "../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-warehouse-devices',
  templateUrl: './warehouse-devices.component.html',
  styleUrls: ['./warehouse-devices.component.scss']
})
export class WarehouseDevicesComponent implements OnInit {


    devices: DeviceListElement[];

    constructor(private deviceService : DeviceService,
                private translate:TranslateService) {
    }

    ngOnInit() {
        this.getDevices()
    }


    getDevices(){
        this.deviceService.getAllDevicesForWarehouseman().subscribe(deviceListElement=> {this.devices=deviceListElement});
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
                const range =it.manufacture+ ' ' + it.location+ ' ' + it.serialNumber+ ' '+ it.deviceModel+ ' '+it.lastUpdate+' '+it.deviceTypeName;
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

    getUserInfo(device:DeviceListElement){
        return device.name+' '+device.userSurname+' | '+device.username
    }

    delete(device: DeviceListElement) {
        this.deviceService.deleteDevice(String(device.id)).subscribe(resp => {
            this.getDevices()
        });
    }
}
