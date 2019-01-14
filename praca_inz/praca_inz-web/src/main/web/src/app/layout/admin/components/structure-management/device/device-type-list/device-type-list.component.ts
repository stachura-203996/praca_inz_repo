import {Component, OnInit} from '@angular/core';
import {DeviceTypeListElement} from "../../../../../../models/device-elements";
import {DeviceService} from "../../../../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";


@Component({
    selector: 'app-device-type-list',
    templateUrl: './device-type-list.component.html',
    styleUrls: ['./device-type-list.component.scss']
})
export class DeviceTypeListComponent implements OnInit {

    deviceTypes: DeviceTypeListElement[];

    deviceType: string;

    constructor(
        private deviceService: DeviceService,
        private translate: TranslateService,
        private configuration: Configuration,
        private messageService: MessageService
    ) {
    }

    ngOnInit() {
        this.getDevicesTypes()
    }


    getDevicesTypes() {
        this.deviceService.getAllDevicesTypes().subscribe(deviceListElement => {
            this.deviceTypes = deviceListElement
        });
    }

    addDeviceType() {
        if (!this.deviceTypes.some(x => x.name == this.deviceType)) {
            this.deviceService.createDeviceType(this.deviceType).subscribe(x => {
                this.getDevicesTypes();
            });
        }
    }

    filterDeviceTypes(searchText: string) {
        this.deviceService.getAllDevicesTypes().subscribe(deviceTypes => {
            if (!deviceTypes) {
                this.deviceTypes = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.deviceTypes = deviceTypes;
            }

            searchText = searchText.toLowerCase();
            this.deviceTypes = deviceTypes.filter(it => {
                const range = it.name;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(deviceType: DeviceTypeListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.type.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                        this.deviceService.deleteDeviceTypes(deviceType.id).subscribe(resp => {
                            this.getDevicesTypes()
                            this.translate.get('success.device.type.delete').subscribe(x=>{
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
