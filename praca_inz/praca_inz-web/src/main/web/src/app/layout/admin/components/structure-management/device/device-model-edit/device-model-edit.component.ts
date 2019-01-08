import { Component, OnInit } from '@angular/core';
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
  selector: 'app-device-model-edit',
  templateUrl: './device-model-edit.component.html',
  styleUrls: ['./device-model-edit.component.scss']
})
export class DeviceModelEditComponent implements OnInit {

    roles: UserRoles;
    currentUser: LoggedUser;

  constructor(private userService:UserService) { }

  ngOnInit() {
      this.getLoggedUser();
      this.getLoggedUserRoles();
  }

    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }
}
