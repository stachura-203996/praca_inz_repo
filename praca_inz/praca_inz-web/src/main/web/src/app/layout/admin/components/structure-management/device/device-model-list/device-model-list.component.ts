import {Component, OnInit} from '@angular/core';
import {DeviceService} from "../../../../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {DeviceListElement, DeviceModelListElement} from "../../../../../../models/device-elements";

@Component({
    selector: 'app-device-model-list',
    templateUrl: './device-model-list.component.html',
    styleUrls: ['./device-model-list.component.scss']
})
export class DeviceModelListComponent implements OnInit {

    devicesTypes: DeviceModelListElement[];

    constructor(private deviceService: DeviceService, private translate: TranslateService) {
        this.translate.addLangs(['en', 'pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getDevicesTypes()
    }


    getDevicesTypes() {
        this.deviceService.getAllDevicesModels().subscribe(deviceListElement => {
            this.devicesTypes = deviceListElement
        });
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
