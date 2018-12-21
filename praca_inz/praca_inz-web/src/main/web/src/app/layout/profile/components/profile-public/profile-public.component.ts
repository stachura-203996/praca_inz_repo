import {Component, OnInit} from '@angular/core';
import {ProfileInfo} from "../../models/profile-info";
import {ProfileService} from "../../profile.service";
import {UserListElement} from "../../../user-management/models/user-list-element";
import {SessionContextService} from "../../../../shared/services/session-context.service";

@Component({
    selector: 'app-profile-public',
    templateUrl: './profile-public.component.html',
    styleUrls: ['./profile-public.component.scss']
})
export class ProfilePublicComponent implements OnInit {

    user: ProfileInfo;
    devices: UserListElement[];
    isUserLoggedIn = this.sessionContextService.getUser() !== null;

    roles = {admin: false, employee: false, supervisor: false, benefit_manager: false};

    constructor(private profileService: ProfileService, private sessionContextService: SessionContextService,) {
    }

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
