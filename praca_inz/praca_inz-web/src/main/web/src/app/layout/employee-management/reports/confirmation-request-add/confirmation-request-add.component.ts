import {Component, Input, OnInit} from '@angular/core';
import {ConfirmationAddElement} from "../../../../models/confirmation-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../admin/components/administration/user-management/user.service";
import {ConfirmationService} from "../../confirmation.service";
import {TranslateService} from "@ngx-translate/core";
import {UserListElement} from "../../../../models/user-list-element";
import {RequestViewElement} from "../../../../models/request-elements";
import {RequestService} from "../../request.service";
import {MessageService} from "../../../../shared/services/message.service";
import {Configuration} from "../../../../app.constants";

@Component({
    selector: 'app-confirmation-request-add',
    templateUrl: './confirmation-request-add.component.html',
    styleUrls: ['./confirmation-request-add.component.scss']
})
export class ConfirmationRequestAddComponent implements OnInit {

    @Input() confirmationAddElement: ConfirmationAddElement = new ConfirmationAddElement();

    request: RequestViewElement;
    recivers = new Map<string, number>();
    selectedOption: string;

    constructor(
        private route: ActivatedRoute,
        private userService: UserService,
        private confirmationService: ConfirmationService,
        private requestService: RequestService,
        private translate: TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.getRecievers();
        this.getRequest();
    }

    getRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x => {
            this.request = x
        });
    }

    getRecievers() {
        this.userService.getAll().subscribe((response: UserListElement[]) => {
            this.recivers = response.reduce(function (companyMap, company) {
                if (company.id) {
                    companyMap.set(company.username, company.id)
                }
                return companyMap;
            }, this.recivers);
        });

    }

    confirmationAdd() {
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
                    this.confirmationAddElement.reciever = this.recivers.get(this.request.username);
                    this.confirmationAddElement.title = this.request.title;
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
        this.confirmationAddElement = new ConfirmationAddElement();
    }
}
