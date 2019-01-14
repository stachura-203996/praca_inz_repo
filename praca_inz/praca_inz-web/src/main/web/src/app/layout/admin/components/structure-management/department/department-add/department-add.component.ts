import {Component, Input, OnInit} from '@angular/core';

import {CompanyService} from "../../../administration/company/company.service";
import {Router} from "@angular/router";

import {DepartmentService} from "../department.service";
import {StructureAddElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";

@Component({
    selector: 'app-department-add',
    templateUrl: './department-add.component.html',
    styleUrls: ['./department-add.component.scss']
})
export class DepartmentAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    companies = new Map<string, number>();

    selectedOption: string;
    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(
        private companyService: CompanyService,
        private userService: UserService,
        private departmentService: DepartmentService,
        private translate: TranslateService,
        private messageService: MessageService,
        private router: Router,
        private configuration: Configuration
    ) {

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
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('department.add').subscribe(x => entity = x);
        this.translate.get('confirm.add').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    if (this.roles.admin) {
                        this.structureAddElement.companyId = this.companies.get(this.selectedOption);
                    } else {
                        this.structureAddElement.companyId = this.currentUser.companyId;
                    }
                    this.departmentService.createDepartment(this.structureAddElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/departments');
                        this.translate.get('success.department.add').subscribe(x => {
                            this.messageService.success(x)
                        })
                    }, error => {
                        if (error === this.configuration.ERROR_DEPARTMENT_NAME_TAKEN) {
                            this.translate.get('department.name.taken.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        } else if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
                            this.translate.get('no.object.in.database.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        } else {
                            this.translate.get('unknown.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        }
                    });
                }
            });
    }

    clear() {
        this.structureAddElement = new StructureAddElement();
    }

}
