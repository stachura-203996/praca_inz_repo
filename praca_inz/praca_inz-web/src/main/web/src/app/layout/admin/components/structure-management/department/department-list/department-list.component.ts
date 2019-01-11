import {Component, OnInit} from '@angular/core';
import {DepartmentService} from "../department.service";
import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {UserRoles} from "../../../../../../models/user-roles";
import {UserService} from "../../../administration/user-management/user.service";
import {LoggedUser} from "../../../../../../models/logged-user";


@Component({
    selector: 'app-department-list',
    templateUrl: './department-list.component.html',
    styleUrls: ['./department-list.component.scss']
})
export class DepartmentListComponent implements OnInit {
    
    departments: StructureListElement[];

    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(private departmentService: DepartmentService, private userService: UserService, private translate: TranslateService) {
    }

    ngOnInit() {
        this.getLoggedUserRoles();
        this.getLoggedUser();
        this.filterDepartments(null);

    }


    getAddress(department: StructureListElement): string {
        if (department.flatNumber == null || department.flatNumber === "0") {
            return (department.street + ' ' + department.buildingNumber);
        } else {
            return (department.street + ' ' + department.buildingNumber + ' / ' + department.flatNumber);
        }
    }

    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    filterDepartments(searchText: string) {
        this.departmentService.getAll().subscribe(departments => {
            if (!departments) {
                this.departments = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.departments = departments;
            }

            searchText = searchText.toLowerCase();
            this.departments = departments.filter(it => {
                const range = it.name + ' '+ it.street+' '+it.city+' '+it.description+' '+it.flatNumber+' '+it.buildingNumber+' '+it.companyName;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(structure: StructureListElement) {
        this.departmentService.deleteDepartament(String(structure.id)).subscribe(resp => {
            this.filterDepartments(null);
        });
    }

}
