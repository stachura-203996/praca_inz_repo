import { Component, OnInit } from '@angular/core';
import {RequestListElement} from "../../../models/request-elements";
import {RequestService} from "../../device-management/request.service";
import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../models/structure-elements";

@Component({
  selector: 'app-warehouse-request-list',
  templateUrl: './warehouse-request-list.component.html',
  styleUrls: ['./warehouse-request-list.component.scss']
})
export class WarehouseRequestListComponent implements OnInit {

    deviceRequest: RequestListElement[];
    transferRequest:RequestListElement[];
    deliveryRequest:RequestListElement[];
    shipmentRequest:RequestListElement[];


    DEVICE_REQUEST:string="DEVICE_REQUEST";
    TRANSFER_REQUEST:string="TRANSFER_REQUEST";
    DELIVERY_REQUEST:string="DELIVERY_REQUEST";
    SHIPMENT_REQUEST:string="SHIPMENT_REQUEST";

    constructor(private requestService : RequestService,
                private translate:TranslateService,

    ) {}

    ngOnInit() {
        // this.filterUsers(null);
        this.getRequests();
    }

    getRequests(){
        this.requestService.getAllRequestsForLoggedUser(this.DEVICE_REQUEST).subscribe(officeListElement=> {this.deviceRequest=officeListElement});
        this.requestService.getAllRequestsForLoggedUser(this.TRANSFER_REQUEST).subscribe(officeListElement=> {this.transferRequest=officeListElement});
    }

    getAddress(office:StructureListElement): string {
        if (office.flatNumber== null || office.flatNumber === "0") {
            return (office.street + ' ' + office.buildingNumber);
        } else {
            return (office.street + ' ' + office.buildingNumber + ' / ' + office.flatNumber);
        }
    }

    filterUsers(searchText: string) {
        // this.userService.getAllNotificationsForUser().subscribe(users => {
        //     if (!users) {
        //         this.users = [];
        //         return;
        //     }
        //     if (!searchText || searchText.length < 2) {
        //         if (this.notVerifiedFilter) {
        //             this.users = users.filter(it => {
        //                 return it.verified === !this.notVerifiedFilter;
        //             });
        //         } else {
        //             this.users = users;
        //         }
        //         return;
        //     }
        //
        //     searchText = searchText.toLowerCase();
        //     this.users = users.filter(it => {
        //         const fullname = it.name + ' ' + it.surname;
        //         const ok = fullname.toLowerCase().includes(searchText);
        //         if (!this.notVerifiedFilter) {
        //             return ok;
        //         } else {
        //             return ok && it.verified === !this.notVerifiedFilter;
        //         }
        //     });
        // });
    }

    delete(structure: StructureListElement) {
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
