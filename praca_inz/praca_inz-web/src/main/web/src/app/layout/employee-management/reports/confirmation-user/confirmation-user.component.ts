import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ConfirmationListElement} from "../../../../models/confirmation-elements";
import {ConfirmationService} from "../../confirmation.service";
import {MessageService} from "../../../../shared/services/message.service";
import {Configuration} from "../../../../app.constants";

@Component({
    selector: 'app-confirmation',
    templateUrl: './confirmation-user.component.html',
    styleUrls: ['./confirmation-user.component.scss']
})
export class ConfirmationUserComponent implements OnInit {

    public deletedFilter = false;
    confirmations: ConfirmationListElement[];
    otherConfirmations: ConfirmationListElement[];


    constructor(
        private confirmationService: ConfirmationService,
        private messageService: MessageService,
        private configuration: Configuration,
        private translate: TranslateService) {}

    ngOnInit() {
        this.getSendedConfirmationsForUser();
        this.getRecivedConfirmationsForUser();
    }

    getSendedConfirmationsForUser() {
        this.confirmationService.getAllForUser().subscribe(confirmationListElement => {
            this.confirmations = confirmationListElement
        });
    }

    getRecivedConfirmationsForUser() {
        this.confirmationService.getAllFromOthers().subscribe(confirmationListElement => {
            this.otherConfirmations = confirmationListElement
        });
    }

    disableBySender(structure: ConfirmationListElement) {
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
                    this.confirmationService.disableBySender(String(structure.id)).subscribe(resp => {
                        this.getSendedConfirmationsForUser();
                        this.getRecivedConfirmationsForUser();
                        this.translate.get('success.confirmation.delete').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }

    getSender(confirmation:ConfirmationListElement){
        return confirmation.senderName+" "+confirmation.senderSurname+" | "+confirmation.sender;
    }

    getReciever(confirmation:ConfirmationListElement){
        return confirmation.recieverName+" "+confirmation.recieverSurname+" | "+confirmation.receiver;
    }

    disableByReciever(structure: ConfirmationListElement) {

                    var entity: string;
                    var message: string;
                    var yes: string;
                    var no: string;

                    this.translate.get('department.add').subscribe(x => entity = x);
                    this.translate.get('confirm.add').subscribe(x => message = x);
                    this.translate.get('yes').subscribe(x => yes = x);
                    this.translate.get('no').subscribe(x => no = x);


                    this.messageService
                        .confirm(entity, message, yes, no)
                        .subscribe(confirmed => {
                            if (confirmed) {
                                    this.confirmationService.disableByReciever(String(structure.id)).subscribe(resp => {
                                        this.getSendedConfirmationsForUser();
                                        this.getRecivedConfirmationsForUser();
                                        this.translate.get('success.confirmation.delete').subscribe(x => {
                                            this.messageService.success(x)
                                        })
                                    });
                            }
                        });
    }
}
