import {Component, OnInit} from '@angular/core';

import {OfficeService} from "../office.service";
import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
    selector: 'app-office-list',
    templateUrl: './office-list.component.html',
    styleUrls: ['./office-list.component.scss']
})
export class OfficeListComponent implements OnInit {

    public deletedFilter = false;
    offices: StructureListElement[];
    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private officeService: OfficeService,
        private translate: TranslateService,
    ) {
    }

    ngOnInit() {
        this.getOffices();
    }

    getOffices() {
            this.officeService.getAll().subscribe(officeListElement => {
                this.offices = officeListElement
            });
    }

    getAddress(office: StructureListElement): string {
        if (office.flatNumber == null || office.flatNumber === "0") {
            return (office.street + ' ' + office.buildingNumber);
        } else {
            return (office.street + ' ' + office.buildingNumber + ' / ' + office.flatNumber);
        }
    }

    filterOffices(searchText: string) {

            this.officeService.getAll().subscribe(offices => {
                if (!offices) {
                    this.offices = [];
                    return;
                }
                if (!searchText || searchText.length < 2) {
                    if (this.deletedFilter) {
                        this.offices = offices.filter(it => {
                            return it.deleted === !this.deletedFilter;
                        });
                    } else {
                        this.offices = offices;
                    }
                    return;
                }

                searchText = searchText.toLowerCase();
                this.offices = offices.filter(it => {
                    const range = it.name + ' ' + it.companyName + ' ' + it.departmentName + ' ' + it.description + ' ' + it.city + ' ' + it.street + ' ' + it.zipCode;
                    const ok = range.toLowerCase().includes(searchText);
                    if (!this.deletedFilter) {
                        return ok;
                    } else {
                        return ok && it.deleted === !this.deletedFilter;
                    }
                });
            });
    }

    delete(id: string) {
        this.officeService.deleteDepartament(id).subscribe(resp => {
            this.getOffices();
        });
    }
}
