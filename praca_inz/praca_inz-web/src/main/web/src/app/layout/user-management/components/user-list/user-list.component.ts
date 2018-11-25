import {Component, OnInit} from '@angular/core';
import {AccountLevel} from "../../models/account-level";
import {UserListElement} from "../../models/user-list-element";

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

    notVerifiedFilter = false;
    users: UserListElement[];
    selectedUserAccessLevels: AccountLevel[];

    constructor() {
    }

    ngOnInit() {
    }

}
