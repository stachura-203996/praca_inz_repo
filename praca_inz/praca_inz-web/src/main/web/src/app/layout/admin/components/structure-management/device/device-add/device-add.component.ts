import {Component, OnInit} from '@angular/core';
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
    selector: 'app-device-add',
    templateUrl: './device-add.component.html',
    styleUrls: ['./device-add.component.scss']
})
export class DeviceAddComponent implements OnInit {

    roles: UserRoles;
    currentUser: LoggedUser;

    constructor(private userService: UserService) {
    }


    ngOnInit() {
    }

    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }
}
