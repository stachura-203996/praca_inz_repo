import { Component, OnInit } from '@angular/core';
import {RequestListElement} from "../../../models/request-elements";

import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../models/structure-elements";
import {RequestService} from "../../employee-management/request.service";

@Component({
  selector: 'app-warehouse-request-list',
  templateUrl: './warehouse-request-list.component.html',
  styleUrls: ['./warehouse-request-list.component.scss']
})
export class WarehouseRequestListComponent implements OnInit {

    employeesRequests: RequestListElement[];
    warehousesRequests:RequestListElement[];
    otherWarehousesRequests:RequestListElement[];

    TRANSFER_REQUEST:string="TRANSFER_REQUEST";

    constructor(private requestService : RequestService,
                private translate:TranslateService,

    ) {}

    ngOnInit() {
        // this.filterUsers(null);
        this.getRequests();
    }

    getRequests(){
        this.requestService.getAllRequestsForLoggedUser(this.TRANSFER_REQUEST).subscribe(requests=> {this.warehousesRequests=requests});
        this.requestService.getAllRequestsForLoggedUser(this.TRANSFER_REQUEST).subscribe(requests=> {this.otherWarehousesRequests=requests});
        this.requestService.getAllRequestsForLoggedUser(this.TRANSFER_REQUEST).subscribe(requests=> {this.employeesRequests=requests});
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
