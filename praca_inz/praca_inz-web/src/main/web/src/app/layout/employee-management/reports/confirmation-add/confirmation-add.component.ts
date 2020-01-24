import {Component, Input, OnInit} from '@angular/core';

import {ActivatedRoute, Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";
import {ConfirmationAddElement} from "../../../../models/confirmation-elements";
import {ConfirmationService} from "../../confirmation.service";
import {UserListElement} from "../../../../models/user-list-element";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {MessageService} from "../../../../shared/services/message.service";
import {Configuration} from "../../../../app.constants";

@Component({
  selector: 'app-confirmation-add',
  templateUrl: './confirmation-add.component.html',
  styleUrls: ['./confirmation-add.component.scss']
})
export class ConfirmationAddComponent implements OnInit {

    @Input() confirmationAddElement: ConfirmationAddElement= new ConfirmationAddElement();

     recivers=new Map<string, number>();
     selectedOption: string;

    constructor(
        private route: ActivatedRoute,
        private userService:UserService,
        private confirmationService:ConfirmationService,
        private translate:TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router:Router) {}

    ngOnInit() {
        this.getRecievers();
    }

    getRecievers() {
        this.userService.getAllForReport().subscribe((response: UserListElement[]) => {
            this.recivers = response.reduce(function(userMap, user){
                if(user.id){
                    userMap.set(user.name+" "+user.surname+" | "+user.username,user.id)
                }
                return userMap;
            },this.recivers);
        });

    }

    confirmationAdd(){
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('confirmation.add').subscribe(x => entity = x);
        this.translate.get('confirm.add').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                        this.confirmationAddElement.reciever = this.recivers.get(this.selectedOption);
                        this.confirmationService.createReport(this.confirmationAddElement).subscribe(resp => {
                            this.router.navigateByUrl('/employees/confirmations');
                            this.translate.get('success.confirmation.add').subscribe(x => {
                                this.messageService.success(x)
                            })
                        });
                }
            });
    }

    clear() {
        this.confirmationAddElement=new ConfirmationAddElement();
    }
}
