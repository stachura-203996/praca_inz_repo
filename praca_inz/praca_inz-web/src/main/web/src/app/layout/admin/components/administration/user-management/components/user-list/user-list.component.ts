import {Component, OnInit} from '@angular/core';
import {UserListElement} from "../../../../../../../models/user-list-element";
import {UserService} from "../../user.service";
import {TranslateService} from "@ngx-translate/core";
import {routerTransition} from "../../../../../../../router.animations";


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


    delete(user: UserListElement) {
        this.userService.deleteUser(String(user.id)).subscribe(resp => {
            this.filterUsers(null)
        });
    }
}
