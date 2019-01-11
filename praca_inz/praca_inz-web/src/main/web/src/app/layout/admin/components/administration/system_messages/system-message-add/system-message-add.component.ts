import {Component, Input, OnInit} from '@angular/core';
import {StructureAddElement} from "../../../../../../models/structure-elements";
import {CompanyService} from "../../company/company.service";
import {Router} from "@angular/router";
import {SystemMessageAddElement} from "../../../../../../models/system-message-add-element";
import {SystemMessageService} from "../../../../../main-page/system-message.service";

@Component({
  selector: 'app-system-message-add',
  templateUrl: './system-message-add.component.html',
  styleUrls: ['./system-message-add.component.scss']
})
export class SystemMessageAddComponent implements OnInit {

    @Input() systemMessageAddElement: SystemMessageAddElement= new SystemMessageAddElement();

    constructor(
        private systemMessageService:SystemMessageService,
        private router:Router
    ) {}

    ngOnInit() {}

    messageAdd(){
        this.systemMessageService.createSystemMessage(this.systemMessageAddElement).subscribe(resp => {
            this.router.navigateByUrl('/admin/system/messages');
        });
    }

    clear() {
        this.systemMessageAddElement=new SystemMessageAddElement();
    }
}
