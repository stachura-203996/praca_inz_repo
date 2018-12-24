import {Component, OnInit} from '@angular/core';
import {StructureListElement} from "../../../models/structure-list-element";
import {NotificationListElement} from "../../../models/notification-list-element";
import {NotificationService} from "../notification.service";

@Component({
    selector: 'app-notification-user',
    templateUrl: './notification-user.component.html',
    styleUrls: ['./notification-user.component.scss']
})
export class NotificationUserComponent implements OnInit {

    notifications: NotificationListElement[];

    constructor(private notificationService:NotificationService) {
    }

    ngOnInit() {
        this.getNotificationsForUser();
    }

    getNotificationsForUser(){
        this.notificationService.getAllNotificationsForUser().subscribe(notificationListElement=> {this.notifications=notificationListElement});
    }

    filterNotifications(searchText: string) {
        this.notificationService.getAllNotificationsForUser().subscribe(notifications => {
            if (!notifications) {
                this.notifications = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.notifications = notifications;
            }

            searchText = searchText.toLowerCase();
            this.notifications = notifications.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(notification: NotificationListElement) {
        // const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        // modalRef.componentInstance.user = user;
        // modalRef.result.then(
        //     result => {
        //         // Left blank intentionally, nothing to do here
        //     },
        //     reason => {
        //         // Left blank intentionally, nothing to do here
        //     }
        // );
    }

}
