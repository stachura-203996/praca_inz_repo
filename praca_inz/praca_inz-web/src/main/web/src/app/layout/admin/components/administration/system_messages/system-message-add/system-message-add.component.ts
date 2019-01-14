import {Component, Input, OnInit} from '@angular/core';
import {StructureAddElement} from "../../../../../../models/structure-elements";
import {CompanyService} from "../../company/company.service";
import {Router} from "@angular/router";
import {SystemMessageAddElement} from "../../../../../../models/system-message-add-element";
import {SystemMessageService} from "../../../../../main-page/system-message.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../../../../shared/services/message.service";

@Component({
    selector: 'app-system-message-add',
    templateUrl: './system-message-add.component.html',
    styleUrls: ['./system-message-add.component.scss']
})
export class SystemMessageAddComponent implements OnInit {

    @Input() systemMessageAddElement: SystemMessageAddElement = new SystemMessageAddElement();

    constructor(
        private systemMessageService: SystemMessageService,
        private translate: TranslateService,
        private messageService: MessageService,
        private router: Router
    ) {
    }

    ngOnInit() {
    }

    messageAdd() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('system.message.add').subscribe(x => entity = x);
        this.translate.get('confirm.add').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);

        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.systemMessageService.createSystemMessage(this.systemMessageAddElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/system/messages');
                    }, error => {

                        this.translate.get('unknown.error').subscribe(x => {
                            this.messageService.error(x);
                        })

                    });
                }
            });
    }

    clear() {
        this.systemMessageAddElement = new SystemMessageAddElement();
    }
}
