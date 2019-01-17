import {Component, OnInit} from '@angular/core';
import {CompanyService} from "../company.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StructureEditElement} from "../../../../../../models/structure-elements";
import {TranslateService} from "@ngx-translate/core";
import {DepartmentService} from "../../../structure-management/department/department.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";


@Component({
    selector: 'app-company-edit',
    templateUrl: './company-edit.component.html',
    styleUrls: ['./company-edit.component.scss']
})
export class CompanyEditComponent implements OnInit {

    structureEditElement: StructureEditElement;


    constructor(
        private route: ActivatedRoute,
        private companyService: CompanyService,
        private messageService: MessageService,
        private translate: TranslateService,
        private departmentService: DepartmentService,
        private configuration: Configuration,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.getCompany();
    }

    getCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.companyService.getCompanyEdit(id).subscribe(x => {
            this.structureEditElement = x
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

    companyUpdate() {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('company.edit').subscribe(x => entity = x);
        this.translate.get('confirm.edit').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.companyService.updateCompany(this.structureEditElement).subscribe(resp => {
                        this.router.navigateByUrl('/admin/companies');
                        this.translate.get('success.company.edit').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }

    clear() {
        this.structureEditElement = new StructureEditElement;
    }
}
