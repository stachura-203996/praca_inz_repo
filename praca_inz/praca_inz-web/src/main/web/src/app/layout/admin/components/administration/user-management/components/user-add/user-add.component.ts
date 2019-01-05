import {Component, Input, OnInit} from '@angular/core';
import {RegisterUser} from "../../../../../../../models/register-user";
import {StructureAddElement, StructureListElement} from "../../../../../../../models/structure-elements";
import {CompanyService} from "../../../company/company.service";
import {Router} from "@angular/router";
import {UserService} from "../../user.service";

@Component({
    selector: 'app-user-add',
    templateUrl: './user-add.component.html',
    styleUrls: ['./user-add.component.scss']
})
export class UserAddComponent implements OnInit {

    @Input() registerUserData: RegisterUser = new RegisterUser;

    offices: StructureListElement[];

    selectedOption: string;

    constructor(private userService:UserService,private router:Router) {
    }

    ngOnInit() {

    }


    userAdd(){
        this.registerUserData.officeId=this.offices.find(x=>x.name==this.selectedOption).id;
        this.userService.createUser(this.registerUserData).subscribe(resp => {
            this.router.navigateByUrl('/admin/users');
        });
    }

    clear() {
        this.registerUserData=new RegisterUser();
    }
}
