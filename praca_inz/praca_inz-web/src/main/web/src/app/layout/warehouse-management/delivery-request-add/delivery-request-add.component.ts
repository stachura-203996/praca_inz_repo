import {Component, Input, OnInit} from '@angular/core';
import {DeliveryRequestAddElement, DeviceRequestAddElement} from "../../../models/request-elements";
import {DeviceModelListElement} from "../../../models/device-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceService} from "../../device-management/device.service";
import {RequestService} from "../../employee-management/request.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-delivery-request-add',
  templateUrl: './delivery-request-add.component.html',
  styleUrls: ['./delivery-request-add.component.scss']
})
export class DeliveryRequestAddComponent implements OnInit {

    @Input() deliveryRequestAddElement: DeliveryRequestAddElement= new DeliveryRequestAddElement;

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
        this.deliveryRequestAddElement.deviceModel=this.deviceModels.find(x=>x.name==this.selectedOption).id;
        this.requestService.createDeliveryRequest(this.deliveryRequestAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.deliveryRequestAddElement=new DeliveryRequestAddElement();
    }

}
