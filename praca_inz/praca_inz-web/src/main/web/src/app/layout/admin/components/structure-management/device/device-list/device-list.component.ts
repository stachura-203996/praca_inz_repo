import { Component, OnInit } from '@angular/core';

import {TranslateService} from "@ngx-translate/core";
import {DeviceService} from "../../../../../device-management/device.service";
import {DeviceListElement} from "../../../../../../models/device-elements";
import {Router} from "@angular/router";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {I18nService} from "../../../../../../shared/services/i18n/i18n.service";


@Component({
  selector: 'app-device-list',
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.scss']
})
export class DeviceListComponent implements OnInit {


    devices: DeviceListElement[];

    constructor(
         private i18nService:I18nService,
        private deviceService : DeviceService,
        private userService:UserService,
        private translate:TranslateService,
        private router:Router
    ) {
    }

    ngOnInit() {
        this.getDevices()
    }


    getDevices(){
            this.deviceService.getAllDevices().subscribe(deviceListElement => {
                this.devices = deviceListElement
            });
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
                const range = it.deviceModel+ ' ' + it.manufacture+ ' ' + it.location+ ' ' + it.serialNumber+ ' '+ it.deviceTypeName+ ' '+it.lastUpdate;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    transfer(device: DeviceListElement) {
      this.router.navigateByUrl('/ui/page/admin/devices/transfer'+device.id)
    }

    delete(device: DeviceListElement) {
        this.deviceService.deleteDevice(String(device.id)).subscribe(resp => {
            this.getDevices()
        });
    }

    getUserInfo(device:DeviceListElement){
        return device.userName+' '+device.userSurname+' | '+device.username
    }
}
