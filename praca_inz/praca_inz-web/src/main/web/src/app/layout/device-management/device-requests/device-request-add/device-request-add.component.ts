import {Component, Input, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceRequestAddElement} from "../../../../models/request-elements";
import {RequestService} from "../../../employee-management/request.service";
import {DeviceListElement, DeviceModelListElement} from "../../../../models/device-elements";
import {DeviceService} from "../../device.service";
import {MessageService} from "../../../../shared/services/message.service";
import {Configuration} from "../../../../app.constants";

@Component({
  selector: 'app-device-request-add',
  templateUrl: './device-request-add.component.html',
  styleUrls: ['./device-request-add.component.scss']
})
export class DeviceRequestAddComponent implements OnInit {

    @Input() deviceRequestAddElement: DeviceRequestAddElement= new DeviceRequestAddElement;

    deviceModels= new Map<string, number>();
    selectedOption: string;

    constructor(
        private route: ActivatedRoute,
        private deviceService:DeviceService,
        private requestService:RequestService,
        private translate:TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router:Router) {

    }

    ngOnInit() {
        this.getDevicesModels();
    }


    getDevicesModels() {
        this.deviceService.getAllDevicesModels().subscribe((response: DeviceModelListElement[]) => {
            this.deviceModels = response.reduce(function (deviceMap, device) {
                if (device.id) {
                    deviceMap.set(device.name+" "+device.manufacture, device.id)
                }
                return deviceMap;
            }, this.deviceModels);
        });
    }

    deviceRequestAdd(){
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('device.request.add').subscribe(x => entity = x);
        this.translate.get('confirm.add').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.deviceRequestAddElement.deviceModelId=this.deviceModels.get(this.selectedOption);
                    this.requestService.createDeviceRequest(this.deviceRequestAddElement).subscribe(resp => {
                        this.router.navigateByUrl('/employees/requests');
                        this.translate.get('success.device.request.add').subscribe(x => {
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

    clear() {
        this.deviceRequestAddElement=new DeviceRequestAddElement();
    }
}
