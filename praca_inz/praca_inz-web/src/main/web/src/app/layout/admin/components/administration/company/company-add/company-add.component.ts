import {Component, Input, OnInit} from '@angular/core';
import {CompanyService} from "../company.service";
import {Router} from "@angular/router";
import {StructureAddElement} from "../../../../../../models/structure-elements";
import {Configuration} from "../../../../../../app.constants";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../../../../shared/services/message.service";


@Component({
    selector: 'app-company-add',
    templateUrl: './company-add.component.html',
    styleUrls: ['./company-add.component.scss']
})
export class CompanyAddComponent implements OnInit {

    @Input() structureAddElement: StructureAddElement = new StructureAddElement;

    constructor(
        private companyService: CompanyService,
        private configuration: Configuration,
        private translate: TranslateService,
        private messageService: MessageService,
        private router: Router,
    ) {
    }

    ngOnInit() {
    }

    companyAdd() {
        var entity:string;
        var message:string;
        var yes:string;
        var no:string;

        this.translate.get('comapny.add').subscribe(x=>entity=x);
        this.translate.get('confirm.add').subscribe(x=>message=x);
        this.translate.get('yes').subscribe(x=>yes=x);
        this.translate.get('no').subscribe(x=>no=x);


        this.messageService
            .confirm(entity,message,yes,no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.companyService.createCompany(this.structureAddElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/companies');
                        this.translate.get('success.company.add').subscribe(x=>{
                            this.messageService.success(x)
                        })
                    }, error => {
                        console.log(error.message);
                        if (error.message === this.configuration.ERROR_COMPANY_NAME_TAKEN) {
                            this.translate.get('comapny.name.taken.error').subscribe(x => {
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

    clear() {
        this.structureAddElement = new StructureAddElement();
    }

}
