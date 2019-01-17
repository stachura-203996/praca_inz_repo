import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {OfficeService} from "../office.service";
import {DepartmentService} from "../../department/department.service";
import {StructureAddElement, StructureListElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";

@Component({
    selector: 'app-office-add',
    templateUrl: './office-add.component.html',
    styleUrls: ['./office-add.component.scss']
})
export class OfficeAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    departments = new Map<string, number>();
    selectedOption: string;
    roles: UserRoles;


    constructor(
        private officeService: OfficeService,
        private userService: UserService,
        private departmentService: DepartmentService,
        private translate: TranslateService,
        private configuration:Configuration,
        private messageService:MessageService,
        private router: Router) {
    }


    ngOnInit() {
        this.getLoggedUserRoles();
        this.getDepartments();
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

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    officeAdd() {
        var entity:string;
        var message:string;
        var yes:string;
        var no:string;

        this.translate.get('office.add').subscribe(x=>entity=x);
        this.translate.get('confirm.add').subscribe(x=>message=x);
        this.translate.get('yes').subscribe(x=>yes=x);
        this.translate.get('no').subscribe(x=>no=x);


        this.messageService
            .confirm(entity,message,yes,no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.structureAddElement.departmentId = this.departments.get(this.selectedOption);
                    this.officeService.createOffice(this.structureAddElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/offices');
                        this.translate.get('success.office.add').subscribe(x=>{
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }

    clear() {
        this.structureAddElement = new StructureAddElement();
    }

}
