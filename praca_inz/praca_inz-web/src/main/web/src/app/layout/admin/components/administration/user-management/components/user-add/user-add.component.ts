import {Component, Input, OnInit} from '@angular/core';
import {RegisterUser} from "../../../../../../../models/register-user";
import {Router} from "@angular/router";
import {UserService} from "../../user.service";
import {I18nService} from "../../../../../../../shared/services/i18n/i18n.service";
import {MessageService} from "../../../../../../../shared/services/message.service";
import {OfficeService} from "../../../../structure-management/office/office.service";
import {StructureListElement} from "../../../../../../../models/structure-elements";
import {UserRoles} from "../../../../../../../models/user-roles";
import {TranslateService} from "@ngx-translate/core";

@Component({
    selector: 'app-user-add',
    templateUrl: './user-add.component.html',
    styleUrls: ['./user-add.component.scss']
})
export class UserAddComponent implements OnInit {

    @Input() registerUserData: RegisterUser = new RegisterUser;

    offices = new Map<string, number>();
    admin=false;
    company_admin=false;
    warehouseman=false;
    manager=false;
    selectedRoles:string[]=[];
    selectedOption: string;

    constructor(
        private userService: UserService,
        private officeService: OfficeService,
        private translate: TranslateService,
        private messageService:MessageService,
        private i18nService:I18nService,
        private router: Router) {}

    ngOnInit() {
        this.getOffices();
    }


    setRoles(){
        console.log("admin: "+this.admin);
        console.log("company_admin: "+this.company_admin);
        console.log("manager: "+this.manager);
        console.log("warehouseman: "+this.warehouseman);

        if(this.admin){
            this.selectedRoles.push("ADMIN");
        }
        if(this.company_admin){
            this.selectedRoles.push("COMPANY_ADMIN");
        }
        if(this.warehouseman){
            this.selectedRoles.push("WAREHOUSEMAN");
        }
        if(this.manager){
            this.selectedRoles.push("MANAGER");
        }
        this.selectedRoles.push("USER");
        this.registerUserData.roles=this.selectedRoles;
    }

    getOffices() {
        this.officeService.getAll().subscribe((response: StructureListElement[]) => {
            this.offices = response.reduce(function (officeMap, office) {
                if (office.id) {
                    officeMap.set(office.name, office.id)
                }
                return officeMap;
            }, this.offices);
        });

    }

    userAdd() {
        this.setRoles();
        // this.messageService
        // .confirm(this.i18nService.getMessage('change.password'), this.i18nService.getMessage('changePassword.confirm.msg'),
        // this.i18nService.getMessage('yes'), this.i18nService.getMessage('no'))
        // .subscribe(confirmed => {
        this.registerUserData.officeId = this.offices.get(this.selectedOption);
        this.userService.createUser(this.registerUserData)
            .subscribe(resp => {
                this.router.navigateByUrl('/admin/users');
            });

        // });
    }

    clear() {
        this.registerUserData = new RegisterUser();
    }
}
