import { Component, OnInit } from '@angular/core';
import {StructureListElement} from "../../../../../../models/structure-elements";
import {CompanyService} from "../../company/company.service";
import {TranslateService} from "@ngx-translate/core";
import {SystemMessageService} from "../../../../../main-page/system-message.service";
import {SystemMessageListElement} from "../../../../../../models/system-message-list-element";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";

@Component({
  selector: 'app-system-message-list',
  templateUrl: './system-message-list.component.html',
  styleUrls: ['./system-message-list.component.scss']
})
export class SystemMessageListComponent implements OnInit {

    systemMessages: SystemMessageListElement[];

    constructor(
        private systemMessageService: SystemMessageService,
        private messageService:MessageService,
        private translate: TranslateService,
        private configuration:Configuration
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
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('system.message.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.systemMessageService.deleteSystemMessage(String(structure.id)).subscribe(resp => {
                        this.filterMessages(null);
                        this.translate.get('success.system.message.delete').subscribe(x=>{
                            this.messageService.success(x)
                        })
                    }, error => {
                        if (error === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
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
