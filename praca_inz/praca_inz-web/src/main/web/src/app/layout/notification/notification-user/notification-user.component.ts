import {Component, OnInit} from '@angular/core';
import {NotificationListElement} from "../../../models/notification-list-element";
import {NotificationService} from "../notification.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-notification-user',
    templateUrl: './notification-user.component.html',
    styleUrls: ['./notification-user.component.scss']
})
export class NotificationUserComponent implements OnInit {

    unreadedNotifications: NotificationListElement[];

    readedNotifications: NotificationListElement[];

    constructor(private notificationService:NotificationService, private router:Router) {
    }

    ngOnInit() {
        this.getUnreadedNotificationsForUser();
    }

    getUnreadedNotificationsForUser(){
        this.notificationService.getAllUnreadedNotificationsForUser().subscribe(notificationListElement=> {this.unreadedNotifications=notificationListElement});
    }

    getReadedNotificationsForUser(){
        this.notificationService.getAllReadedNotificationsForUser().subscribe(notificationListElement=> {this.readedNotifications=notificationListElement});
    }

    filterNotifications(searchText: string) {
        this.notificationService.getAllUnreadedNotificationsForUser().subscribe(notifications => {
            if (!notifications) {
                this.unreadedNotifications = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.unreadedNotifications = notifications;
            }

            searchText = searchText.toLowerCase();
            this.unreadedNotifications = notifications.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    view(notification:NotificationListElement){
        notification.readed=true;
        this.notificationService.updateNotification(notification).subscribe(resp => {
            this.router.navigateByUrl('/notifications');
        });
    }


    delete(notification: NotificationListElement) {
        this.notificationService.deleteNotification(String(notification.id)).subscribe(resp => {
            this.getReadedNotificationsForUser()
            this.getUnreadedNotificationsForUser()
        });
    }

}
