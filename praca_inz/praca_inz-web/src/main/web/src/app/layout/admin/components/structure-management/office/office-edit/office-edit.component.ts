import {Component, OnInit} from '@angular/core';
import {StructureEditElement, StructureListElement} from "../../../../../../models/structure-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {CompanyService} from "../../../administration/company/company.service";
import {TranslateService} from "@ngx-translate/core";
import {DepartmentService} from "../../department/department.service";
import {OfficeService} from "../office.service";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";

@Component({
    selector: 'app-office-edit',
    templateUrl: './office-edit.component.html',
    styleUrls: ['./office-edit.component.scss']
})
export class OfficeEditComponent implements OnInit {

    structureEditElement: StructureEditElement;

    departments = new Map<string, number>();
    roles: UserRoles;

    constructor(
        private route: ActivatedRoute,
        private departmentService: DepartmentService,
        private userService:UserService,
        private translate: TranslateService,
        private officeService: OfficeService,
        private messageService:MessageService,
        private configuration:Configuration,
        private router: Router
    ) {}

    ngOnInit() {
        this.getLoggedUserRoles();
        this.getOffice();
        this.getDepartments();
    }

    getOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.officeService.getOfficeEdit(id).subscribe(x =>{ this.structureEditElement = x}, error => {
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

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getDepartments() {
        this.departmentService.getAll().subscribe((response: StructureListElement[]) => {
            this.departments = response.reduce(function (departmentMap, department) {
                if (department.id) {
                    departmentMap.set(department.name + " | " + department.companyName, department.id)
                }
                return departmentMap;
            }, this.departments);
        });
    }

    officeUpdate() {
        var entity:string;
        var message:string;
        var yes:string;
        var no:string;

        this.translate.get('office.edit').subscribe(x=>entity=x);
        this.translate.get('confirm.edit').subscribe(x=>message=x);
        this.translate.get('yes').subscribe(x=>yes=x);
        this.translate.get('no').subscribe(x=>no=x);


        this.messageService
            .confirm(entity,message,yes,no)
            .subscribe(confirmed => {
                if (confirmed) {
                        this.structureEditElement.parentId = this.departments.get(this.structureEditElement.parentName);
                        this.officeService.updateOffice(this.structureEditElement).subscribe(resp => {
                            this.router.navigateByUrl('/admin/offices');
                        });
                }
            });
    }

    clear() {
        this.structureEditElement = new StructureEditElement;
    }

}
