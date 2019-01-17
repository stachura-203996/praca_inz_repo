import {Component, OnInit} from '@angular/core';
import {TransferListElement} from "../../../../../../models/transfer-list-element";
import {DeviceService} from "../../../../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
    selector: 'app-transfer-list',
    templateUrl: './transfer-list.component.html',
    styleUrls: ['./transfer-list.component.scss']
})
export class TransferListComponent implements OnInit {

    transfers: TransferListElement[];

    constructor(private deviceService: DeviceService,
                private userService: UserService,
                private translate: TranslateService) {
    }

    ngOnInit() {
        this.getTransfersForLoggedUser()

    }

    getTransfersForLoggedUser() {
            this.deviceService.getAllTransfers().subscribe(transferListElement => {
                this.transfers = transferListElement
            });
    }

    filterTransfers(searchText: string) {
        this.deviceService.getAllTransfers().subscribe(transfers => {
            if (!transfers) {
                this.transfers = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.transfers = transfers;
            }

            searchText = searchText.toLowerCase();
            this.transfers = transfers.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }
}
