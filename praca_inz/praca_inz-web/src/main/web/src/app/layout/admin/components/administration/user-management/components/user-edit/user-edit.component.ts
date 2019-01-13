import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user.service";
import {OfficeService} from "../../../../structure-management/office/office.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../../../../../shared/services/message.service";
import {I18nService} from "../../../../../../../shared/services/i18n/i18n.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureListElement} from "../../../../../../../models/structure-elements";
import {UserRoles} from "../../../../../../../models/user-roles";
import {UserEdit} from "../../../../../../../models/user-edit";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {


    userEditElement: UserEdit;

    offices = new Map<string, number>();
    roles:UserRoles;
    selectedRoles:string[]=[];

    constructor(
        private route: ActivatedRoute,
        private userService: UserService,
        private officeService: OfficeService,
        private translate: TranslateService,
        private messageService:MessageService,
        private i18nService:I18nService,
        private router: Router) {}

    ngOnInit() {
        this.getOffices();
        this.getUserEdit();
        this.getUserRoles();
    }


    setRoles(){
        console.log("admin: "+this.roles.admin);
        console.log("company_admin: "+this.roles.company_admin);
        console.log("manager: "+this.roles.manager);
        console.log("warehouseman: "+this.roles.warehouseman);

        if(this.roles.admin){
            this.selectedRoles.push("ADMIN");
        }
        if(this.roles.company_admin){
            this.selectedRoles.push("COMPANY_ADMIN");
        }
        if(this.roles.warehouseman){
            this.selectedRoles.push("WAREHOUSEMAN");
        }
        if(this.roles.manager){
            this.selectedRoles.push("MANAGER");
        }
        this.selectedRoles.push("USER");
        this.userEditElement.roles=this.selectedRoles;
    }

    getUserEdit(){
        const id = this.route.snapshot.paramMap.get('id');
        this.userService.getUserEdit(Number(id)).subscribe(x=>this.userEditElement=x);
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

    getUserRoles() {
        const id = this.route.snapshot.paramMap.get('id');
        this.userService.getUserRoles(id).subscribe(x => this.roles = x);
    }

    userAdd() {
        this.setRoles();
        this.userEditElement.officeId = this.offices.get(this.userEditElement.officeName);
        this.userService.updateAccountByAdmin(this.userEditElement)
            .subscribe(resp => {
                this.router.navigateByUrl('/admin/users');
            });

    }

    clear() {
        this.userEditElement = new UserEdit();
    }
}
