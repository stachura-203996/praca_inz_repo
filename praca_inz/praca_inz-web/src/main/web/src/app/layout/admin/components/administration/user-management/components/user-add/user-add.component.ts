import {Component, Input, OnInit} from '@angular/core';
import {RegisterUser} from "../../../../../../../models/register-user";
import {CreateUser} from "../../../../../../../models/create-user";

@Component({
    selector: 'app-user-add',
    templateUrl: './user-add.component.html',
    styleUrls: ['./user-add.component.scss']
})
export class UserAddComponent implements OnInit {

    @Input() createUserData: CreateUser = new CreateUser;

    constructor() {
    }

    ngOnInit() {
    }

}
