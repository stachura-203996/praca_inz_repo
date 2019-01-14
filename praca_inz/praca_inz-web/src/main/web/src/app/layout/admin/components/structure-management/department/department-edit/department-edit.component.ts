import {Component, Input, OnInit} from '@angular/core';

import {CompanyService} from "../../../administration/company/company.service";
import {DepartmentService} from "../department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureEditElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {UserService} from "../../../administration/user-management/user.service";
import {LoggedUser} from "../../../../../../models/logged-user";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";


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
        private messageService:MessageService,
        private configuration:Configuration,
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
        this.departmentService.getDepartmentEdit(id).subscribe(x=>{this.structureEditElement=x}, error => {
            if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
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
        var entity:string;
        var message:string;
        var yes:string;
        var no:string;

        this.translate.get('department.edit').subscribe(x=>entity=x);
        this.translate.get('confirm.edit').subscribe(x=>message=x);
        this.translate.get('yes').subscribe(x=>yes=x);
        this.translate.get('no').subscribe(x=>no=x);


        this.messageService
            .confirm(entity,message,yes,no)
            .subscribe(confirmed => {
                if (confirmed) {
                    if(this.roles.admin) {
                        this.structureEditElement.parentId = this.companies.get(this.structureEditElement.parentName);
                    } else {
                        this.structureEditElement.parentId=this.currentUser.companyId;
                    }
                    this.departmentService.updateDepartment(this.structureEditElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/departments');
                    }, error => {
                        if (error === this.configuration.OPTIMISTIC_LOCK) {
                            this.translate.get('optimistic.lock').subscribe(x => {
                                this.messageService.error(x);
                            })

                        } else  if (error === this.configuration.ERROR_DEPARTMENT_NAME_TAKEN) {
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
        this.structureEditElement=new StructureEditElement;
    }

}
