import { Component, OnInit } from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {UserListElement} from "../../../models/user-list-element";
import {UserService} from "../../admin/components/administration/user-management/user.service";

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrls: ['./employees-list.component.scss']
})
export class EmployeesListComponent implements OnInit {

    public notVerifiedFilter = false;
    subordinates: UserListElement[];

  constructor(private translate:TranslateService,private userService:UserService) { }

  ngOnInit() {
      this.getSubordinates();
  }

    getSubordinates(){
        this.userService.getAllSubordinates().subscribe(userListElements=> {this.subordinates=userListElements});
    }

    filterSubordinates(searchText: string) {
        this.userService.getAll().subscribe(subordinates => {
            if (!subordinates) {
                this.subordinates = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                if (this.notVerifiedFilter) {
                    this.subordinates = subordinates.filter(it => {
                        return it.verified === !this.notVerifiedFilter;
                    });
                } else {
                    this.subordinates = subordinates;
                }
                return;
            }

            searchText = searchText.toLowerCase();
            this.subordinates = subordinates.filter(it => {
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

}
