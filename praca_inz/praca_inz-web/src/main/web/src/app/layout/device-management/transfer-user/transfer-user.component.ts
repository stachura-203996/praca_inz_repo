import { Component, OnInit } from '@angular/core';
import {DeviceService} from "../device.service";
import {TranslateService} from "@ngx-translate/core";
import {TransferListElement} from "../../../models/transfer-list-element";

@Component({
  selector: 'app-transfer-user',
  templateUrl: './transfer-user.component.html',
  styleUrls: ['./transfer-user.component.scss']
})
export class TransferUserComponent implements OnInit {

    transfers: TransferListElement[];

    constructor(private deviceService : DeviceService,
                private translate:TranslateService) {
    }

  ngOnInit() {
        this.getTransfersForLoggedUser()
  }

    getTransfersForLoggedUser(){
        this.deviceService.getAllTransfersForLoggedUser().subscribe(transferListElement=> {this.transfers=transferListElement});
    }

    filterTransfers(searchText: string) {
        this.deviceService.getAllTransfersForLoggedUser().subscribe(transfers => {
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
