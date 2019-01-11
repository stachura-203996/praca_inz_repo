import {Component, Input, OnInit} from '@angular/core';

import {CompanyService} from "../../../administration/company/company.service";
import {Router} from "@angular/router";

import {DepartmentService} from "../department.service";
import {StructureAddElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
    selector: 'app-department-add',
    templateUrl: './department-add.component.html',
    styleUrls: ['./department-add.component.scss']
})
export class DepartmentAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    companies = new Map<string, number>();

    selectedOption: string;
    id: number;
    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private companyService: CompanyService,
        private userService: UserService,
        private departmentService: DepartmentService,
        private translate: TranslateService,
        private router: Router) {

    }

    ngOnInit() {
        this.getLoggedUser();
        this.getLoggedUserRoles();
        this.getCompanies();

    }


    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getCompanies() {
            this.companyService.getAll().subscribe((response: StructureListElement[]) => {
                this.companies = response.reduce(function (companyMap, company) {
                    if (company.id) {
                        companyMap.set(company.name, company.id)
                    }
                    return companyMap;
                }, this.companies);
            });
    }

    departmentAdd() {
        if(this.roles.admin) {
            this.structureAddElement.companyId = this.companies.get(this.selectedOption);
        } else {
            this.structureAddElement.companyId=this.currentUser.companyId;
        }
        this.departmentService.createDepartment(this.structureAddElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/departments');
        });

    }

    clear() {
        this.structureAddElement = new StructureAddElement();
    }

}
