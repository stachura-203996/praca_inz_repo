import {Component, OnInit} from '@angular/core';
import {AccountLevel} from "../../models/account-level";
import {UserListElement} from "../../models/user-list-element";
import {UserService} from "../../user.service";
import {Observable} from "rxjs";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../../shared/services/message.service";
import {I18nService} from "../../../../shared/services/i18n/i18n.service";

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

    public notVerifiedFilter = false;
    users: UserListElement[];
    selectedUserAccessLevels: AccountLevel[];

    constructor(private userService : UserService,
                private translate:TranslateService,

    ) {

        this.translate.addLangs(['en','pl','de']);
        this.translate.setDefaultLang('en');
        const browserLang = this.translate.getBrowserLang();// private messageService: MessageService,
        // private i18nService: I18nServiceerLang();
        this.translate.use(browserLang.match(/en|pl|de/) ? browserLang : 'pl');
    }

    ngOnInit() {
        // this.filterUsers(null);
        this.getUsers();
    }

    getUsers(){
        this.userService.getAll().subscribe(userListElements=> {this.users=userListElements});
    }


    filterUsers(searchText: string) {
        this.userService.getAll().subscribe(users => {
            if (!users) {
                this.users = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                if (this.notVerifiedFilter) {
                    this.users = users.filter(it => {
                        return it.verified === !this.notVerifiedFilter;
                    });
                } else {
                    this.users = users;
                }
                return;
            }

            searchText = searchText.toLowerCase();
            this.users = users.filter(it => {
                const fullname = it.name + ' ' + it.surname;
                const ok = fullname.toLowerCase().includes(searchText);
                if (!this.notVerifiedFilter) {
                    return ok;
                } else {
                    return ok && it.verified === !this.notVerifiedFilter;
                }
            });
        });
    }

    getRoles(userId: number): void {
        this.userService.getAccessLevels(userId)
            .subscribe(levels => this.selectedUserAccessLevels = levels);
    }

    activateAccount(id: number) {
        // this.userService.activateAccount(id).subscribe(() => this.messageService
        //     .success(this.i18nService.getMessage('user.account.activate.success')));
    }

    deactivateAccount(id: number) {
        // this.userService.deactivateAccount(id).subscribe(() => this.messageService
        //     .success(this.i18nService.getMessage('user.account.deactivate.success')));
    }

    activateAccessLevel(userId: number, accessLevelId: number) {
        // this.userService.activateAccessLevel(userId, accessLevelId).subscribe(() => this.messageService
        //     .success(this.i18nService.getMessage('user.access_level.activate.success')));
    }

    deactivateAccessLevel(userId: number, accessLevelId: number) {
        // this.userService.deactivateAccessLevel(userId, accessLevelId).subscribe(() => this.messageService
        //     .success(this.i18nService.getMessage('user.access_level.deactivate.success')));
    }

    changeAccessLevelState(event, userId: number, accessLevelId: number) {
        // if (event.target.checked) {
        //     this.activateAccessLevel(userId, accessLevelId);
        // } else {
        //     this.deactivateAccessLevel(userId, accessLevelId);
        // }
    }

    verifyUser(userId: number) {
        // this.messageService
        //     .confirm(this.i18nService.getMessage('user.verify'), this.i18nService.getMessage('user.verify.confirm.msg'),
        //         this.i18nService.getMessage('yes'), this.i18nService.getMessage('no'))
        //     .subscribe(confirmed => {
        //         if (confirmed) {
        //             this.userService.verifyUser(userId).subscribe(() => {
        //                 this.users.find(user => user.id === userId).verified = true;
        //
        //                 if (this.notVerifiedFilter) {
        //                     this.filterUsers(null);
        //                 }
        //
        //                 this.messageService
        //                     .success(this.i18nService.getMessage('user.verify.success.msg'));
        //             });
        //         }
        //     });
    }
}
