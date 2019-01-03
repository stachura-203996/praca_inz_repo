import {Component, Input, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceRequestAddElement} from "../../../../models/request-elements";
import {RequestService} from "../../../employee-management/request.service";
import {DeviceModelListElement} from "../../../../models/device-elements";
import {DeviceService} from "../../device.service";

@Component({
  selector: 'app-device-request-add',
  templateUrl: './device-request-add.component.html',
  styleUrls: ['./device-request-add.component.scss']
})
export class DeviceRequestAddComponent implements OnInit {

    @Input() deviceRequestAddElement: DeviceRequestAddElement= new DeviceRequestAddElement;

    deviceModels: DeviceModelListElement[];
    selectedOption: string;

    constructor(private route: ActivatedRoute,private deviceService:DeviceService,private requestService:RequestService,private translate:TranslateService,private router:Router) {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getWarehouses();
    }

    getWarehouses(){
        this.deviceService.getAllDevicesModels().subscribe(deviceModels=> {this.deviceModels=deviceModels});
    }

    deviceRequestAdd(){
        this.deviceRequestAddElement.deviceModel=this.deviceModels.find(x=>x.name==this.selectedOption).id;
        this.requestService.createDeviceRequest(this.deviceRequestAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.deviceRequestAddElement=new DeviceRequestAddElement();
    }
}
