import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ConfirmationViewElement} from "../../../../models/confirmation-elements";
import {ConfirmationService} from "../../confirmation.service";


@Component({
  selector: 'app-confirmation-view',
  templateUrl: './confirmation-view.component.html',
  styleUrls: ['./confirmation-view.component.scss']
})
export class ConfirmationViewComponent implements OnInit {

    confirmation: ConfirmationViewElement;

    constructor(
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
    ) {}

    ngOnInit() {
        this.getCompany();

    }

    getCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.confirmationService.getReportView(id).subscribe(x => this.confirmation = x);
    }

    getSender(){
        return this.confirmation.senderName+" "+this.confirmation.senderSurname+" | "+this.confirmation.sender;
    }

    getReciever(){
        return this.confirmation.recieverName+" "+this.confirmation.recieverSurname+" | "+this.confirmation.receiver;
    }
}
