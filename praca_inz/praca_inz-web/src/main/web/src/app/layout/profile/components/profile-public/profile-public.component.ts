import {Component, OnInit} from '@angular/core';
import {ProfileInfo} from "../../models/profile-info";
import {ProfileService} from "../../profile.service";

@Component({
    selector: 'app-profile-public',
    templateUrl: './profile-public.component.html',
    styleUrls: ['./profile-public.component.scss']
})
export class ProfilePublicComponent implements OnInit {

    currentUser: ProfileInfo;

    constructor(private profileService: ProfileService) {
    }

    ngOnInit() {
        this.getProfile();
    }

    getProfile() {
        this.profileService.getUserProfile().subscribe(profile => {
            this.currentUser = profile
        });
    }

    getAddress(): string {
        if (this.currentUser.flatNumber == null || this.currentUser.flatNumber === "0") {
            return (this.currentUser.street + ' ' + this.currentUser.houseNumber);
        } else {
            return (this.currentUser.street + ' ' + this.currentUser.houseNumber + ' / ' + this.currentUser.flatNumber);
        }
    }
}
