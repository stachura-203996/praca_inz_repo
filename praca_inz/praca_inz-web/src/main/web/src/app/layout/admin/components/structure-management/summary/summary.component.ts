import { Component, OnInit } from '@angular/core';
import {UserRoles} from "../../../../../models/user-roles";
import {LoggedUser} from "../../../../../models/logged-user";
import {UserService} from "../../administration/user-management/user.service";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.scss']
})
export class SummaryComponent implements OnInit {


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
