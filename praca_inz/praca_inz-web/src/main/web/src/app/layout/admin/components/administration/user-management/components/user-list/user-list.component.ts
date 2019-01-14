import {Component, OnInit} from '@angular/core';
import {UserListElement} from "../../../../../../../models/user-list-element";
import {UserService} from "../../user.service";
import {TranslateService} from "@ngx-translate/core";
import {routerTransition} from "../../../../../../../router.animations";
import {MessageService} from "../../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../../app.constants";


@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.scss'],
    animations: [routerTransition()]
})
export class UserListComponent implements OnInit {

    public notVerifiedFilter = false;
    users: UserListElement[];


    constructor(private userService: UserService,
                private translate: TranslateService,
                private messageService:MessageService,
                private configuration:Configuration
    ) {}

    ngOnInit() {
        this.filterUsers(null);

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
                const fullname = it.name + ' ' + it.surname+' '+it.email+' '+it.username;
                const ok = fullname.toLowerCase().includes(searchText);
                if (!this.notVerifiedFilter) {
                    return ok;
                } else {
                    return ok && it.verified === !this.notVerifiedFilter;
                }
            });
        });
    }

    getAuthorityTranslation(authority:string):string{
        var tmp:string;
        this.translate.get(authority).subscribe(x=>tmp=x);
        return tmp;
    }


    delete(user: UserListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('user.delete').subscribe(x => entity = x);
        this.translate.get('confirm.user.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService.confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.userService.deleteUser(String(user.id)).subscribe(resp => {
                        this.filterUsers(null)
                    }, error => {
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
            });
    }
}
