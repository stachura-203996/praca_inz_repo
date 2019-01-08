import {Component, OnInit} from '@angular/core';
import {DeviceTypeListElement} from "../../../../../../models/device-elements";
import {DeviceService} from "../../../../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";


@Component({
    selector: 'app-device-type-list',
    templateUrl: './device-type-list.component.html',
    styleUrls: ['./device-type-list.component.scss']
})
export class DeviceTypeListComponent implements OnInit {

    deviceTypes: DeviceTypeListElement[];

    constructor(private deviceService: DeviceService, private translate: TranslateService) {
    }

    ngOnInit() {
        this.getDevicesTypes()
    }


    getDevicesTypes() {
        this.deviceService.getAllDevicesModels().subscribe(deviceListElement => {
            this.deviceTypes = deviceListElement
        });
    }


    filterDeviceTypes(searchText: string) {
        this.deviceService.getAllDevicesModels().subscribe(deviceTypes => {
            if (!deviceTypes) {
                this.deviceTypes = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.deviceTypes = deviceTypes;
            }

            searchText = searchText.toLowerCase();
            this.deviceTypes = deviceTypes.filter(it => {
                const range = it.name + ' ' + it.manufacture + ' ' + it.type;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(deviceType: DeviceTypeListElement) {
        this.deviceService.deleteDeviceTypes(String(deviceType.id)).subscribe(resp => {
            this.getDevicesTypes()
        });
    }

}
