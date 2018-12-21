import { Component, OnInit } from '@angular/core';
import {UserListElement} from "../../user-management/models/user-list-element";
import {TranslateService} from "@ngx-translate/core";
import {UserService} from "../../user-management/user.service";

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
