import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../user.service";
import {PasswordDataForAdmin} from "../../../../../../../models/change-password-by-admin-model";
import {MessageService} from "../../../../../../../shared/services/message.service";
import {TranslateService} from "@ngx-translate/core";
import {Configuration} from "../../../../../../../app.constants";

@Component({
  selector: 'app-user-password-edit',
  templateUrl: './user-password-edit.component.html',
  styleUrls: ['./user-password-edit.component.scss']
})
export class UserPasswordEditComponent implements OnInit {

    passwordDataByAdmin: PasswordDataForAdmin = new PasswordDataForAdmin();

    constructor(private userService: UserService,
                private router: Router,
                private translate:TranslateService,
                private messageService:MessageService,
                private configuration:Configuration,
                private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.getVersion();
    }

    getVersion(): void {
        const id = this.route.snapshot.paramMap.get('id');
        this.userService.getUserPasswordAdminData(id).subscribe(ver => this.passwordDataByAdmin = ver);
    }

    changePasswordByAdminSubmit() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('change.password').subscribe(x => entity = x);
        this.translate.get('changePassword.confirm.msg').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);

        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.passwordDataByAdmin.id= this.route.snapshot.paramMap.get('id');
                    this.userService.changePasswordByAdmin(this.passwordDataByAdmin).subscribe(rep=>{
                       this.router.navigateByUrl('/admin/users')
                        this.translate.get('success.change.password.msg').subscribe(x=>{
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }

}
