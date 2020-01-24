import {Component, OnInit} from '@angular/core';

import {TranslateService} from "@ngx-translate/core";
import {DeviceService} from "../../../../../device-management/device.service";
import {DeviceListElement} from "../../../../../../models/device-elements";
import {Router} from "@angular/router";
import {UserService} from "../../../administration/user-management/user.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";


@Component({
    selector: 'app-device-list',
    templateUrl: './device-list.component.html',
    styleUrls: ['./device-list.component.scss']
})
export class DeviceListComponent implements OnInit {


    devices: DeviceListElement[];

    constructor(
        private deviceService: DeviceService,
        private userService: UserService,
        private translate: TranslateService,
        private configuration: Configuration,
        private messageService: MessageService,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.getDevices()
    }


    getDevices() {
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
                const range = it.deviceModel + ' ' + it.manufacture + ' ' + it.location + ' ' + it.serialNumber + ' ' + it.deviceTypeName + ' ' + it.lastUpdate;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    transfer(device: DeviceListElement) {
        this.router.navigateByUrl('/admin/devices/transfer/' + device.id)
    }

    delete(device: DeviceListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.deviceService.deleteDevice(String(device.id)).subscribe(resp => {
                        this.getDevices();
                        this.translate.get('success.device.delete').subscribe(x => {
                            this.messageService.success(x)
                        })

                    }, error => {
                        if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
                            this.translate.get('no.object.in.database.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        } else {
                            this.translate.get('unknown.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        }

                    });
                }
            });
    }
}
