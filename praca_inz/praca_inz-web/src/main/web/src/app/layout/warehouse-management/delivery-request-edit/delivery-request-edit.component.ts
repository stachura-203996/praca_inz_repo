import {Component, Input, OnInit} from '@angular/core';
import {DeliveryRequestAddElement, DeliveryRequestEditElement} from "../../../models/request-elements";
import {DeviceModelListElement} from "../../../models/device-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceService} from "../../device-management/device.service";
import {RequestService} from "../../employee-management/request.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
    selector: 'app-delivery-request-edit',
    templateUrl: './delivery-request-edit.component.html',
    styleUrls: ['./delivery-request-edit.component.scss']
})
export class DeliveryRequestEditComponent implements OnInit {

    deliveryRequestEditElement: DeliveryRequestEditElement;

    deviceModels: DeviceModelListElement[];

    selectedOption: string;

    constructor(private route: ActivatedRoute, private deviceService: DeviceService, private requestService: RequestService, private translate: TranslateService, private router: Router) {
        this.translate.addLangs(['en', 'pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getWarehouses();
    }

    getWarehouses() {
        this.deviceService.getAllDevicesModels().subscribe(deviceModels => {
            this.deviceModels = deviceModels
        });
    }

    deviceRequestUpdate() {
        this.deliveryRequestEditElement.deviceModel = this.deviceModels.find(x => x.name == this.selectedOption).id;
        this.requestService.updateDeliveryRequest(this.deliveryRequestEditElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.deliveryRequestEditElement = new DeliveryRequestAddElement();
    }
}
