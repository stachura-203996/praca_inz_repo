import { Component, OnInit } from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {UserListElement} from "../../../models/user-list-element";
import {UserService} from "../../admin/components/administration/user-management/user.service";

@Component({
  selector: 'app-subordinates-list',
  templateUrl: './subordinates-list.component.html',
  styleUrls: ['./subordinates-list.component.scss']
})
export class SubordinatesListComponent implements OnInit {

    public notVerifiedFilter = false;
    users: UserListElement[];

  constructor(private translate:TranslateService,private userService:UserService) { }

  ngOnInit() {
  }

    getUsers(){
        this.userService.getAllSubordinates().subscribe(userListElements=> {this.users=userListElements});
    }

    filterSubordinates(searchText: string) {
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

}
