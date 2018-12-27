import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import {LoginService} from "../../../login/login.service";
import {LoggedUser} from "../../../models/logged-user";
import {UserService} from "../../admin/components/administration/user-management/user.service";
import {NotificationService} from "../../notification/notification.service";
import {NotificationListElement} from "../../../models/notification-list-element";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    pushRightClass: string = 'push-right';
    currentUser: LoggedUser;
    notifications:NotificationListElement[];

    constructor(private translate: TranslateService, public router: Router, private loginService : LoginService, private userService:UserService, private notificationService:NotificationService) {

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



    ngOnInit() {
        this.getLoggedUser();
    }

    getLastNotifications(){
        this.notificationService.getAllUnreadedNotificationsForUser().subscribe(x=>this.notifications=x);
    }

    getLoggedUser(){
        this.userService.getLoggedUser().subscribe(x=>this.currentUser=x);
    }

    isToggled(): boolean {
        const dom: Element = document.querySelector('body');
        return dom.classList.contains(this.pushRightClass);
    }

    toggleSidebar() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle(this.pushRightClass);
    }



    onLoggedout() {
        this.loginService.logout();
    }

    changeLang(language: string) {
        this.translate.use(language);
    }


    getUserInfo(){
        return (this.currentUser.name+ ' '+ this.currentUser.surname);
    }

    viewNotification(notification:NotificationListElement){
        notification.readed=true;
        this.notificationService.updateNotification(notification).subscribe(resp => {
            this.router.navigateByUrl(notification.url);
        });
    }
}
