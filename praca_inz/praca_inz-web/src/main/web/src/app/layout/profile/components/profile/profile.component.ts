import { Component, OnInit } from '@angular/core';
import {ProfileInfo} from "../../models/profile-info";
import {ProfileService} from "../../profile.service";

import {SessionContextService} from "../../../../shared/services/session-context.service";
import {UserListElement} from "../../../admin/components/administration/user-management/models/user-list-element";

@Component({
  selector: 'app-profile-private',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

    user: ProfileInfo;
    devices: UserListElement[];
    isUserLoggedIn = this.sessionContextService.getUser() !== null;


  constructor(private profileService:ProfileService, private sessionContextService:SessionContextService ) { }

    ngOnInit() {
        this.getProfile();
    }

    getProfile() {
        this.profileService.getUserProfile().subscribe(profile => {
            this.user = profile
        });
    }

    getAddress(): string {
        if (this.user.flatNumber == null || this.user.flatNumber === "0") {
            return (this.user.street + ' ' + this.user.houseNumber);
        } else {
            return (this.user.street + ' ' + this.user.houseNumber + ' / ' + this.user.flatNumber);
        }
    }
}
