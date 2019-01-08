import {Component, Input, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceRequestAddElement} from "../../../../models/request-elements";
import {RequestService} from "../../../employee-management/request.service";
import {DeviceListElement, DeviceModelListElement} from "../../../../models/device-elements";
import {DeviceService} from "../../device.service";

@Component({
  selector: 'app-device-request-add',
  templateUrl: './device-request-add.component.html',
  styleUrls: ['./device-request-add.component.scss']
})
export class DeviceRequestAddComponent implements OnInit {

    @Input() deviceRequestAddElement: DeviceRequestAddElement= new DeviceRequestAddElement;

    deviceModels= new Map<string, number>();
    selectedOption: string;

    constructor(private route: ActivatedRoute,private deviceService:DeviceService,private requestService:RequestService,private translate:TranslateService,private router:Router) {

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
        this.deviceRequestAddElement.deviceModelId=this.deviceModels.get(this.selectedOption);
        this.requestService.createDeviceRequest(this.deviceRequestAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.deviceRequestAddElement=new DeviceRequestAddElement();
    }
}
