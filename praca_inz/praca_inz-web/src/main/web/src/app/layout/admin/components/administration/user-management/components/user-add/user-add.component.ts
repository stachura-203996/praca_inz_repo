import {Component, Input, OnInit} from '@angular/core';
import {RegisterUser} from "../../../../../../../models/register-user";
import {Router} from "@angular/router";
import {UserService} from "../../user.service";
import {MessageService} from "../../../../../../../shared/services/message.service";
import {OfficeService} from "../../../../structure-management/office/office.service";
import {StructureListElement} from "../../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {Configuration} from "../../../../../../../app.constants";

@Component({
    selector: 'app-user-add',
    templateUrl: './user-add.component.html',
    styleUrls: ['./user-add.component.scss']
})
export class UserAddComponent implements OnInit {

    @Input() registerUserData: RegisterUser = new RegisterUser;

    languages: string[] = ['PL', 'ENG'];
    offices = new Map<string, number>();
    admin = false;
    company_admin = false;
    warehouseman = false;
    manager = false;
    selectedRoles: string[] = [];
    selectedOption: string;
    selectedlanguage: string;

    constructor(
        private userService: UserService,
        private officeService: OfficeService,
        private translate: TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router: Router) {
    }

    ngOnInit() {
        this.getOffices();
    }


    setRoles() {
        console.log("admin: " + this.admin);
        console.log("company_admin: " + this.company_admin);
        console.log("manager: " + this.manager);
        console.log("warehouseman: " + this.warehouseman);

        if (this.admin) {
            this.selectedRoles.push("ADMIN");
        }
        if (this.company_admin) {
            this.selectedRoles.push("COMPANY_ADMIN");
        }
        if (this.warehouseman) {
            this.selectedRoles.push("WAREHOUSEMAN");
        }
        if (this.manager) {
            this.selectedRoles.push("MANAGER");
        }
        this.selectedRoles.push("USER");
        this.registerUserData.roles = this.selectedRoles;
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
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('user.add').subscribe(x => entity = x);
        this.translate.get('register.confirm.msg').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);

        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.setRoles();
                    this.registerUserData.officeId = this.offices.get(this.selectedOption);
                    this.userService.createUser(this.registerUserData)
                        .subscribe(resp => {
                            this.router.navigateByUrl('/admin/users');
                            this.translate.get('success.user.add').subscribe(x=>{
                                this.messageService.success(x)
                            })
                        }, error => {
                            if (error === this.configuration.ERROR_USERNAME_TAKEN) {
                                this.translate.get('username.taken.error').subscribe(x => {
                                    this.messageService.error(x);
                                })
                            } else if (error === this.configuration.ERROR_EMAIL_TAKEN) {
                                this.translate.get('email.taken.error').subscribe(x => {
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
        this.registerUserData = new RegisterUser();
    }
}
