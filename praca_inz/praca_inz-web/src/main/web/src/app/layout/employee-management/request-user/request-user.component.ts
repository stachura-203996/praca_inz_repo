import { Component, OnInit } from '@angular/core';
import {RequestListElement} from "../../../models/request-elements";

import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../models/structure-elements";
import {RequestService} from "../request.service";

@Component({
  selector: 'app-request-user',
  templateUrl: './request-user.component.html',
  styleUrls: ['./request-user.component.scss']
})
export class RequestUserComponent implements OnInit {


    yourRequest: RequestListElement[];
    employeesRequest:RequestListElement[];

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
        this.requestService.getAllRequestsForLoggedUser(this.DEVICE_REQUEST).subscribe(officeListElement=> {this.yourRequest=officeListElement});
        this.requestService.getAllRequestsForLoggedUser(this.TRANSFER_REQUEST).subscribe(officeListElement=> {this.employeesRequest=officeListElement});
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
