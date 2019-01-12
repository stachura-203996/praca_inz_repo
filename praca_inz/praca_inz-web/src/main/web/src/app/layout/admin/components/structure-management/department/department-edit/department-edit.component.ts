import {Component, Input, OnInit} from '@angular/core';

import {CompanyService} from "../../../administration/company/company.service";
import {DepartmentService} from "../department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureEditElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {UserService} from "../../../administration/user-management/user.service";
import {LoggedUser} from "../../../../../../models/logged-user";


@Component({
    selector: 'app-department-edit',
    templateUrl: './department-edit.component.html',
    styleUrls: ['./department-edit.component.scss']
})
export class DepartmentEditComponent implements OnInit {

    structureEditElement: StructureEditElement;

    companies = new Map<string, number>();

    revertCompanies=new Map<number,string>();

    selectedOption: string;
    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private route: ActivatedRoute,
        private userService:UserService,
        private companyService: CompanyService,
        private translate:TranslateService,
        private departmentService: DepartmentService,
        private router: Router) {
    }

    ngOnInit() {
        this.getLoggedUserRoles();
        this.getDepartment();
        this.getCompanies();
        this.getLoggedUser();
        this.getRevertCompanies();

    }

    getDepartment() {
        const id = this.route.snapshot.paramMap.get('id');
        this.departmentService.getDepartmentEdit(id).subscribe(x=>this.structureEditElement=x);
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

    getRevertCompanies() {
        this.companyService.getAll().subscribe((response: StructureListElement[]) => {
            this.revertCompanies = response.reduce(function (companyMap, company) {
                if (company.id) {
                    companyMap.set(company.id, company.name)
                }
                return companyMap;
            }, this.revertCompanies);
        });
        this.selectedOption=this.revertCompanies.get(this.structureEditElement.parentId)
    }

    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    departmentUpdate() {
        if(this.roles.admin) {
            this.structureEditElement.parentId = this.companies.get(this.selectedOption);
        } else {
            this.structureEditElement.parentId=this.currentUser.companyId;
        }
        this.departmentService.updateDepartment(this.structureEditElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/departments');
        });
    }

    clear() {
        this.structureEditElement=new StructureEditElement;
    }

}
