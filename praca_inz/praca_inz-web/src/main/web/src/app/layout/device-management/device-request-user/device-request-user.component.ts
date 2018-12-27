import { Component, OnInit } from '@angular/core';
import {StructureListElement} from "../../../models/structure-elements";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-device-request-user',
  templateUrl: './device-request-user.component.html',
  styleUrls: ['./device-request-user.component.scss']
})
export class DeviceRequestUserComponent implements OnInit {

    offices: StructureListElement[];

    constructor(private officeService : OfficeService,
                private translate:TranslateService,

    ) {}

    ngOnInit() {
        // this.filterUsers(null);
        this.getUsers();
    }

    getUsers(){
        this.officeService.getAll().subscribe(officeListElement=> {this.offices=officeListElement});
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
