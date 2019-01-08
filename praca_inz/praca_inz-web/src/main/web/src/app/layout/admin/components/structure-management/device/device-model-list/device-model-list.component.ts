import {Component, OnInit} from '@angular/core';
import {DeviceService} from "../../../../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {DeviceListElement, DeviceModelListElement} from "../../../../../../models/device-elements";
import {StructureListElement} from "../../../../../../models/structure-elements";

@Component({
    selector: 'app-device-model-list',
    templateUrl: './device-model-list.component.html',
    styleUrls: ['./device-model-list.component.scss']
})
export class DeviceModelListComponent implements OnInit {

    deviceModels: DeviceModelListElement[];

    constructor(private deviceService: DeviceService, private translate: TranslateService) {
        this.translate.addLangs(['en', 'pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getDevicesModels()
    }


    getDevicesModels() {
        this.deviceService.getAllDevicesModels().subscribe(deviceListElement => {
            this.deviceModels = deviceListElement
        });
    }

    filterDeviceModels(searchText: string) {
        this.deviceService.getAllDevicesModels().subscribe(deviceModels => {
            if (!deviceModels) {
                this.deviceModels = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.deviceModels = deviceModels;
            }

            searchText = searchText.toLowerCase();
            this.deviceModels = deviceModels.filter(it => {
                const range = it.name+ ' ' + it.manufacture+ ' ' + it.type;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(deviceModel: DeviceModelListElement) {
        this.deviceService.deleteDeviceModel(String(deviceModel.id)).subscribe(resp => {
            this.getDevicesModels()
        });
    }
}
