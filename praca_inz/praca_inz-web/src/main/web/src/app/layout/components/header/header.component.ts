import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import {LoginService} from "../../../login/login.service";
import {ProfileInfo} from "../../profile/models/profile-info";
import {ProfileService} from "../../profile/profile.service";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    pushRightClass: string = 'push-right';
    currentUser: ProfileInfo;

    constructor(private translate: TranslateService, public router: Router, private loginService : LoginService, private profileService:ProfileService) {

        this.translate.addLangs(['en','pl','de']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl|de/) ? browserLang : 'pl');

        this.router.events.subscribe(val => {
            if (
                val instanceof NavigationEnd &&
                window.innerWidth <= 992 &&
                this.isToggled()
            ) {
                this.toggleSidebar();
            }
        });
    }

    ngOnInit() {}

    isToggled(): boolean {
        const dom: Element = document.querySelector('body');
        return dom.classList.contains(this.pushRightClass);
    }

    toggleSidebar() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle(this.pushRightClass);
    }

    rltAndLtr() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle('rtl');
    }

    onLoggedout() {
        this.loginService.logout();
    }

    changeLang(language: string) {
        this.translate.use(language);
    }

    getProfile() {
        this.profileService.getUserProfile().subscribe(profile => {
            this.currentUser = profile
        });
    }

    getUserInfo(){
        return (this.currentUser.name+ ' '+ this.currentUser.surname);
    }

    getAddress(): string {
        if (this.currentUser.flatNumber == null || this.currentUser.flatNumber === "0") {
            return (this.currentUser.street + ' ' + this.currentUser.houseNumber);
        } else {
            return (this.currentUser.street + ' ' + this.currentUser.houseNumber + ' / ' + this.currentUser.flatNumber);
        }
    }
}
