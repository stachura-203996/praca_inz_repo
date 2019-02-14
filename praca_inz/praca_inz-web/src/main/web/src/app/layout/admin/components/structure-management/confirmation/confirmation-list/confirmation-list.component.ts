import {Component, OnInit} from '@angular/core';


import {TranslateService} from "@ngx-translate/core";
import {ConfirmationListElement} from "../../../../../../models/confirmation-elements";
import {ConfirmationService} from "../../../../../employee-management/confirmation.service";
import {UserService} from "../../../administration/user-management/user.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";


@Component({
    selector: 'app-confirmation-list',
    templateUrl: './confirmation-list.component.html',
    styleUrls: ['./confirmation-list.component.scss']
})
export class ConfirmationListComponent implements OnInit {
    public deletedFilter = false;
    confirmations: ConfirmationListElement[];


    constructor(
        private confirmationService: ConfirmationService,
        private userService: UserService,
        private translate: TranslateService,
        private configuration: Configuration,
        private messageService: MessageService

    ) {
    }

    ngOnInit() {
        this.getReports();
    }

    getReports() {
            this.confirmationService.getAll().subscribe(confirmationListElement => {
                this.confirmations = confirmationListElement
            });
    }

    filterReports(searchText: string) {
            this.confirmationService.getAll().subscribe(confirmations => {
                if (!confirmations) {
                    this.confirmations = [];
                    return;
                }
                if (!searchText || searchText.length < 2) {
                    this.confirmations = confirmations;
                }

                searchText = searchText.toLowerCase();
                this.confirmations = confirmations.filter(it => {
                    const range = it.title + ' ' + it.sender + ' ' + it.receiver + ' ' + it.description + ' ' + it.confirmationDate;
                    const ok = range.toLowerCase().includes(searchText);
                    return ok;
                });
            });
    }

    getSender(confirmation:ConfirmationListElement){
        return confirmation.senderName+" "+confirmation.senderSurname+" | "+confirmation.sender;
    }

    getReciever(confirmation:ConfirmationListElement){
        return confirmation.recieverName+" "+confirmation.recieverSurname+" | "+confirmation.receiver;
    }

    delete(structure: ConfirmationListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('confirmation.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.confirmationService.deleteReport(String(structure.id)).subscribe(resp => {
                        this.getReports()
                        this.translate.get('success.confirmation.delete').subscribe(x=>{
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }
}
