import {Component, OnInit} from '@angular/core';
import {OfficeService} from "../office.service";
import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";

@Component({
    selector: 'app-office-list',
    templateUrl: './office-list.component.html',
    styleUrls: ['./office-list.component.scss']
})
export class OfficeListComponent implements OnInit {

    offices: StructureListElement[];
    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private officeService: OfficeService,
        private translate: TranslateService,
    ) {
    }

    ngOnInit() {
        this.filterOffices(null);
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
                this.offices = offices;
            }

            searchText = searchText.toLowerCase();
            this.offices = offices.filter(it => {
                const range = it.name + ' ' + it.street+' '+it.city+' '+it.description+' '+it.flatNumber+' '+it.buildingNumber+' '+it.departmentName+' '+it.companyName;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(id: string) {
        this.officeService.deleteDepartament(id).subscribe(resp => {
            this.filterOffices(null);
        });
    }
}
