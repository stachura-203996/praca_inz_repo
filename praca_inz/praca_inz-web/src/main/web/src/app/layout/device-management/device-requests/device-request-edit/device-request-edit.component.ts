import {Component, Input, OnInit} from '@angular/core';
import {
    DeviceRequestAddElement,
    DeviceRequestEditElement,
    TransferRequestEditElement
} from "../../../../models/request-elements";
import {DeviceModelListElement} from "../../../../models/device-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceService} from "../../device.service";
import {RequestService} from "../../../employee-management/request.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-device-request-edit',
  templateUrl: './device-request-edit.component.html',
  styleUrls: ['./device-request-edit.component.scss']
})
export class DeviceRequestEditComponent implements OnInit {

   deviceRequestEditElement: DeviceRequestEditElement;

    deviceModels: DeviceModelListElement[];
    selectedOption: string;

    constructor(private route: ActivatedRoute,private deviceService:DeviceService,private requestService:RequestService,private translate:TranslateService,private router:Router) {

    }

    ngOnInit() {
        this.getWarehouses();
        this.getDeviceRequest();
    }

    getDeviceRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getDeviceRequestEdit(id).subscribe(x=>this.deviceRequestEditElement=x);
    }

    getWarehouses(){
        this.deviceService.getAllDevicesModels().subscribe(deviceModels=> {this.deviceModels=deviceModels});
    }

    deviceRequestUpdate(){
        this.deviceRequestEditElement.deviceModel=this.deviceModels.find(x=>x.name==this.selectedOption).id;
        this.requestService.updateDeviceRequest(this.deviceRequestEditElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.deviceRequestEditElement=new DeviceRequestEditElement();
    }
}
