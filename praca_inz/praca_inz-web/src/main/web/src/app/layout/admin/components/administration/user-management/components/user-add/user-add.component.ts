import {Component, Input, OnInit} from '@angular/core';
import {RegisterUser} from "../../../../../../../models/register-user";

@Component({
    selector: 'app-user-add',
    templateUrl: './user-add.component.html',
    styleUrls: ['./user-add.component.scss']
})
export class UserAddComponent implements OnInit {

    @Input() registerUserData: RegisterUser = new RegisterUser;

    constructor() {
    }

    ngOnInit() {
    }

}
