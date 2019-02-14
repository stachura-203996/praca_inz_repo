import {Component, OnInit} from '@angular/core';
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {ActivatedRoute, Router} from "@angular/router";
import {Configuration} from "../../../../../../app.constants";
import {UserService} from "../../../administration/user-management/user.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../../../../shared/services/message.service";
import {UserListElement} from "../../../../../../models/user-list-element";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {log} from "util";

@Component({
    selector: 'app-warehouse-users-edit',
    templateUrl: './warehouse-users-edit.component.html',
    styleUrls: ['./warehouse-users-edit.component.scss']
})
export class WarehouseUsersEditComponent implements OnInit {


    roles: UserRoles;
    currentUser: LoggedUser;
    warehousemen: UserListElement[];


    selectedOption;
    users = new Map<string, number>();


    constructor(
        private route: ActivatedRoute,
        private configuration: Configuration,
        private userService: UserService,
        private warehouseService: WarehouseService,
        private translate: TranslateService,
        private messageService: MessageService,
        private  router: Router
    ) {
    }

    ngOnInit() {
        this.getUsers();
        this.getDevices();
        this.getLoggedUser();
        this.getLoggeduserRoles();
    }

    getLoggeduserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => this.roles = x);
    }

    getLoggedUser() {
        this.userService.getLoggedUser().subscribe(x => this.currentUser = x);
    }

    getUsers() {
        const id = this.route.snapshot.paramMap.get('id');
        this.userService.getAllWarehouseUsers(Number(id)).subscribe((response: UserListElement[]) => {
            this.users = response.reduce(function (userMap, user) {
                if (user.id) {
                    userMap.set(user.name + " " + user.surname + " | " + user.username, user.id)
                }
                return userMap;
            }, this.users);
        });

    }

    getDevices() {
        const id = this.route.snapshot.paramMap.get('id');
        this.warehouseService.getAllWarehouseUsers(id).subscribe(deviceListElement => {
            this.warehousemen = deviceListElement
        });
    }

    attach() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('user.warehouse.added').subscribe(x => entity = x);
        this.translate.get('confirm.added').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    const id = this.route.snapshot.paramMap.get('id');
                    const userId = this.users.get(this.selectedOption);
                    this.warehouseService.attachWarehouseUser(Number(id), userId).subscribe(x => {
                        this.translate.get('success.user.warehouse.user.add').subscribe(x => {
                            this.messageService.success(x)
                            this.getDevices();
                        });
                    });
                }
            });
    }

    detach(user: UserListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('user.warehouse.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    const id = this.route.snapshot.paramMap.get('id');
                    this.warehouseService.detachWarehouseUser(Number(id), user.id).subscribe(x => {
                        this.translate.get('success.user.warehouse.user.detach').subscribe(x => {
                            this.messageService.success(x)
                            this.getDevices();
                        });
                    });
                }
            });

    }

    translateAll(word: string): string {
        var tmp: string;
        this.translate.get(word).subscribe(x => tmp = x);
        return tmp;
    }
}
