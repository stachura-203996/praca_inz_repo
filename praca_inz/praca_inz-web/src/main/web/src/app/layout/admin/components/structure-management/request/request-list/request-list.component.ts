import {Component, OnInit} from '@angular/core';
import {StructureListElement} from "../../../../../../models/structure-elements";

import {TranslateService} from "@ngx-translate/core";
import {RequestListElement} from "../../../../../../models/request-elements";

import {Router} from "@angular/router";
import {RequestService} from "../../../../../employee-management/request.service";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
    selector: 'app-request-list',
    templateUrl: './request-list.component.html',
    styleUrls: ['./request-list.component.scss']
})
export class RequestListComponent implements OnInit {

    deviceRequests: RequestListElement[];
    transferRequests: RequestListElement[];
    deliveryRequests: RequestListElement[];
    shipmentRequests: RequestListElement[];


    DEVICE_REQUEST: string = "DEVICE_REQUEST";
    TRANSFER_REQUEST: string = "TRANSFER_REQUEST";
    DELIVERY_REQUEST: string = "DELIVERY_REQUEST";
    SHIPMENT_REQUEST: string = "SHIPMENT_REQUEST";

    constructor(
        private requestService: RequestService,
        private userService:UserService,
        private translate: TranslateService,
        private router:Router) {

    }

    ngOnInit() {
        this.getRequests();
    }

    getRequests() {

            this.requestService.getAllRequests(this.DEVICE_REQUEST).subscribe(requests => {
                this.deviceRequests = requests
            });
            this.requestService.getAllRequests(this.TRANSFER_REQUEST).subscribe(requests => {
                this.transferRequests = requests
            });
            this.requestService.getAllRequests(this.DELIVERY_REQUEST).subscribe(requests => {
                this.deliveryRequests = requests
            });
            this.requestService.getAllRequests(this.SHIPMENT_REQUEST).subscribe(requests => {
                this.shipmentRequests = requests
            });
    }


    getAddress(office: StructureListElement): string {
        if (office.flatNumber == null || office.flatNumber === "0") {
            return (office.street + ' ' + office.buildingNumber);
        } else {
            return (office.street + ' ' + office.buildingNumber + ' / ' + office.flatNumber);
        }
    }


    viewPage(request:RequestListElement){
        switch(request.type) {
            case this.DEVICE_REQUEST: {
                this.router.navigateByUrl('/page/devices/request/view/'+request.id);
                break;
            }
            case this.DELIVERY_REQUEST: {
                this.router.navigateByUrl('/page/warehouses/delivery/request/view/'+request.id);
                break;
            }
            case this.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/page/devices/request/transfer/view/'+request.id);
                break;
            }
            case this.SHIPMENT_REQUEST: {
                this.router.navigateByUrl('/page/warehouses/shipment/request/view/'+request.id);
                break;
            }
        }
    }

    editPage(request:RequestListElement){
        switch(request.type) {
            case this.DEVICE_REQUEST: {
                this.router.navigateByUrl('/page/devices/request/edit/'+request.id);
                break;
            }
            case this.DELIVERY_REQUEST: {
                this.router.navigateByUrl('/page/warehouses/delivery/request/edit/'+request.id);
                break;
            }
            case this.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/page/devices/transfer/request/edit/'+request.id);
                break;
            }
            case this.SHIPMENT_REQUEST: {
                this.router.navigateByUrl('/page/warehouses/shipment/request/edit/'+request.id);
                break;
            }
        }
    }

    cancel(structure: RequestListElement) {
        this.requestService.cancelRequest(structure).subscribe(resp => {
            this.getRequests();
        });
    }


    delete(structure: RequestListElement) {
        this.requestService.deleteRequest(String(structure.id)).subscribe(resp => {
            this.getRequests()
        });
    }

}
