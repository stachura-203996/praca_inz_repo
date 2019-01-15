import {Component, OnInit} from '@angular/core';
import {NotificationListElement} from "../../../models/notification-list-element";
import {NotificationService} from "../notification.service";
import {Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../shared/services/message.service";
import {Configuration} from "../../../app.constants";

@Component({
    selector: 'app-notification-user',
    templateUrl: './notification-user.component.html',
    styleUrls: ['./notification-user.component.scss']
})
export class NotificationUserComponent implements OnInit {

    unreadedNotifications: NotificationListElement[];

    readedNotifications: NotificationListElement[];

    constructor(
        private notificationService:NotificationService,
        private translate: TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router:Router
    ) {
    }

    ngOnInit() {
        this.getUnreadedNotificationsForUser();
        this.getReadedNotificationsForUser();
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
            this.router.navigateByUrl(notification.url);
        });
    }


    delete(notification: NotificationListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('notification.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.notificationService.deleteNotification(String(notification.id)).subscribe(resp => {
                        this.getReadedNotificationsForUser()
                        this.getUnreadedNotificationsForUser()
                        this.translate.get('success.notification.delete').subscribe(x => {
                            this.messageService.success(x)
                        })
                    }, error => {
                        if (error === this.configuration.ERROR_DEPARTMENT_NAME_TAKEN) {
                            this.translate.get('department.name.taken.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        } else if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
                            this.translate.get('no.object.in.database.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        } else {
                            this.translate.get('unknown.error').subscribe(x => {
                                this.messageService.error(x);
                            })
                        }
                    });
                }
            });
    }

}
