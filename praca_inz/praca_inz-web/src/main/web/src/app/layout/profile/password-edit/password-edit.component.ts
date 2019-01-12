import {Component, OnInit} from '@angular/core';
import {PasswordData} from "../../../models/change-password";
import {Router} from "@angular/router";
import {ProfileService} from "../profile.service";

@Component({
    selector: 'app-password-edit',
    templateUrl: './password-edit.component.html',
    styleUrls: ['./password-edit.component.scss']
})
export class PasswordEditComponent implements OnInit {
    passwordData: PasswordData = new PasswordData();
    // recaptcha = false;

    constructor(private profileService: ProfileService,
                // private messageService: MessageService,
                // private i18nService: I18nService,
                private router: Router) {
    }

    ngOnInit() {
        this.getVersion();
    }

    getVersion(): void {
        this.profileService.getUserPasswordData()
            .subscribe(ver => this.passwordData = ver);
    }

    changePasswordSubmit() {
        // this.messageService
        //     .confirm(this.i18nService.getMessage('change.password'), this.i18nService.getMessage('changePassword.confirm.msg'),
        //         this.i18nService.getMessage('yes'), this.i18nService.getMessage('no'))
        //     .subscribe(confirmed => {
        //         if (confirmed) {
        this.profileService.changePassword(this.passwordData).subscribe(response => {
                // this.message(response.message);
            this.router.navigateByUrl('/profile')
            }
        );
    // }

    // });
}

// message(response: string) {
//     if (response === 'success') {
//         this.router.navigateByUrl('/');
//         this.messageService.success(this.i18nService.getMessage('success.change.password.msg'));
//     } else if (response === 'same_password') {
//         this.messageService.error(this.i18nService.getMessage('same.password.error'));
//     } else if (response === 'incorrect_password') {
//         this.messageService.error(this.i18nService.getMessage('not.exists.password'));
//     } else {
//         this.messageService.error(this.i18nService.getMessage('unknown.error'));
//     }
// }
}
