import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {OfficeService} from "../office.service";
import {DepartmentService} from "../../department/department.service";
import {StructureAddElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {CompanyService} from "../../../administration/company/company.service";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
    selector: 'app-office-add',
    templateUrl: './office-add.component.html',
    styleUrls: ['./office-add.component.scss']
})
export class OfficeAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    departments: StructureListElement[];
    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private officeService: OfficeService,
        private userService: UserService,
        private departmentService: DepartmentService,
        private translate: TranslateService,
        private router: Router) {
    }


    ngOnInit() {
        this.getLoggedUser();
        this.getLoggedUserRoles();
        this.getDepartments();
    }

    getDepartments() {
        if (this.roles.admin) {
            this.departmentService.getAll().subscribe(companyListElement => {
                this.departments = companyListElement
            });
        } else{
            this.departmentService.getAllForCompany(this.currentUser.companyId).subscribe(companyListElement => {
                this.departments = companyListElement
            });
        }

    }


    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    officeAdd() {
        this.officeService.createOffice(this.structureAddElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/offices');
        });
    }

    clear() {
        this.structureAddElement = new StructureAddElement();
    }

}
