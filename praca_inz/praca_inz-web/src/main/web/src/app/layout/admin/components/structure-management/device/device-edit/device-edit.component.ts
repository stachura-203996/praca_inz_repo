import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../administration/user-management/user.service";
import {UserRoles} from "../../../../../../models/user-roles";

@Component({
    selector: 'app-device-edit',
    templateUrl: './device-edit.component.html',
    styleUrls: ['./device-edit.component.scss']
})
export class DeviceEditComponent implements OnInit {

    roles: UserRoles;

    constructor(private userService: UserService) {
    }

    ngOnInit() {
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }
}
