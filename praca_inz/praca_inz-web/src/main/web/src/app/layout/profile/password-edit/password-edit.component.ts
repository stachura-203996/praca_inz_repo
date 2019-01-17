import {Component, OnInit} from '@angular/core';
import {PasswordData} from "../../../models/change-password";
import {Router} from "@angular/router";
import {ProfileService} from "../profile.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../shared/services/message.service";
import {Configuration} from "../../../app.constants";

@Component({
    selector: 'app-password-edit',
    templateUrl: './password-edit.component.html',
    styleUrls: ['./password-edit.component.scss']
})
export class PasswordEditComponent implements OnInit {
    passwordData: PasswordData = new PasswordData();

    constructor(private profileService: ProfileService,
                private translate: TranslateService,
                private messageService: MessageService,
                private configuration: Configuration,
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
                    this.profileService.changePassword(this.passwordData).subscribe(response => {
                        this.router.navigateByUrl('/profile')
                        this.translate.get('success.change.password.msg').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }

}
