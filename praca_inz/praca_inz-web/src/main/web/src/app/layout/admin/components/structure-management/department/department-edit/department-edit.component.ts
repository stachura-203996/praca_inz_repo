import {Component, Input, OnInit} from '@angular/core';

import {CompanyService} from "../../../administration/company/company.service";
import {DepartmentService} from "../department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureEditElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {UserService} from "../../../administration/user-management/user.service";


@Component({
    selector: 'app-department-edit',
    templateUrl: './department-edit.component.html',
    styleUrls: ['./department-edit.component.scss']
})
export class DepartmentEditComponent implements OnInit {

    structureEditElement: StructureEditElement;

    companies: StructureListElement[];

    roles:UserRoles;

    constructor(private route: ActivatedRoute,private userService:UserService,private companyService: CompanyService,private translate:TranslateService, private departmentService: DepartmentService, private router: Router) {


    }

    ngOnInit() {
        this.getLoggedUserRoles();
        this.getDepartment();
        this.getCompanies();

    }

    getDepartment() {
        const id = this.route.snapshot.paramMap.get('id');
        this.departmentService.getDepartmentEdit(id).subscribe(x=>this.structureEditElement=x);
    }

    getCompanies() {
        this.companyService.getAll().subscribe(companyListElement => {
            this.companies = companyListElement
        });
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    departmentUpdate() {
        this.departmentService.updateDepartment(this.structureEditElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/departments');
        });
    }

    clear() {
        this.structureEditElement=new StructureEditElement;
    }

}
