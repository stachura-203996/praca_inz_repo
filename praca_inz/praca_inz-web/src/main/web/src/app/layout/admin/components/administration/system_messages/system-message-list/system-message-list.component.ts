import { Component, OnInit } from '@angular/core';
import {StructureListElement} from "../../../../../../models/structure-elements";
import {CompanyService} from "../../company/company.service";
import {TranslateService} from "@ngx-translate/core";
import {SystemMessageService} from "../../../../../main-page/system-message.service";
import {SystemMessageListElement} from "../../../../../../models/system-message-list-element";

@Component({
  selector: 'app-system-message-list',
  templateUrl: './system-message-list.component.html',
  styleUrls: ['./system-message-list.component.scss']
})
export class SystemMessageListComponent implements OnInit {

    systemMessages: SystemMessageListElement[];

    constructor(
        private systemMessageService: SystemMessageService,
        private translate: TranslateService,
    ) {}

    ngOnInit() {
        this.filterMessages(null);
    }


    filterMessages(searchText: string) {
        this.systemMessageService.getAllMessages().subscribe(systemMessages => {
            if (!systemMessages) {
                this.systemMessages = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.systemMessages = systemMessages;
            }

            searchText = searchText.toLowerCase();
            this.systemMessages = systemMessages.filter(it => {
                const range = it.title + ' ' + it.message + ' ' + it.messageDate;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(structure: SystemMessageListElement) {
        this.systemMessageService.deleteSystemMessage(String(structure.id)).subscribe(resp => {
            this.filterMessages(null);
        });
    }
}
