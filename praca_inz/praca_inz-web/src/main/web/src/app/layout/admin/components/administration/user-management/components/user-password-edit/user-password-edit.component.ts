import {Component, Input, OnInit} from '@angular/core';
import {MessageService} from "../../../../../../../shared/services/message.service";
import {I18nService} from "../../../../../../../shared/services/i18n/i18n.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../user.service";
import {PasswordDataForAdmin} from "../../../../../../../models/change-password-by-admin-model";

@Component({
  selector: 'app-user-password-edit',
  templateUrl: './user-password-edit.component.html',
  styleUrls: ['./user-password-edit.component.scss']
})
export class UserPasswordEditComponent implements OnInit {

    passwordDataByAdmin: PasswordDataForAdmin = new PasswordDataForAdmin();

    constructor(private userService: UserService,
                // private messageService: MessageService,
                // private i18nService: I18nService,
                private router: Router,
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
        // this.messageService
        //     .confirm(this.i18nService.getMessage('change.password'), this.i18nService.getMessage('changePassword.confirm.msg'),
        //         this.i18nService.getMessage('yes'), this.i18nService.getMessage('no'))
        //     .subscribe(confirmed => {
        //         if (confirmed) {

                    this.passwordDataByAdmin.id= this.route.snapshot.paramMap.get('id');
                    this.userService.changePasswordByAdmin(this.passwordDataByAdmin).subscribe(rep=>{
                       this.router.navigateByUrl('/admin/users')
                    });

                // }
            // });
    }

    // message(response: string) {
    //     if (response === 'success') {
    //         this.router.navigateByUrl('/admin/users');
    //         this.messageService.success(this.i18nService.getMessage('success.change.password.msg'));
    //     } else if (response === 'same_password') {
    //         this.messageService.error(this.i18nService.getMessage('same.password.error'));
    //     } else {
    //         this.messageService.error(this.i18nService.getMessage('unknown.error'));
    //     }
    // }
}
