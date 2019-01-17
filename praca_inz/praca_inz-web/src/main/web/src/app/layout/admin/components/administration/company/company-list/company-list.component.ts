import {Component, OnInit} from '@angular/core';

import {TranslateService} from "@ngx-translate/core";
import {CompanyService} from "../company.service";
import {StructureListElement} from "../../../../../../models/structure-elements";
import {MessageService} from "../../../../../../shared/services/message.service";
import {Configuration} from "../../../../../../app.constants";


@Component({
    selector: 'app-company-list',
    templateUrl: './company-list.component.html',
    styleUrls: ['./company-list.component.scss']
})
export class CompanyListComponent implements OnInit {

    public deletedFilter = false;
    companies: StructureListElement[];

    constructor(
        private companyService: CompanyService,
        private translate: TranslateService,
        private messageService:MessageService,
        private configuration:Configuration
        ) {
    }

    ngOnInit() {
        this.filterCompanies(null);
    }

    getAddress(company: StructureListElement): string {
        if (company.flatNumber == null || company.flatNumber === "0") {
            return (company.street + ' ' + company.buildingNumber);
        } else {
            return (company.street + ' ' + company.buildingNumber + ' / ' + company.flatNumber);
        }
    }

    filterCompanies(searchText: string) {
        this.companyService.getAll().subscribe(copmanies => {
            if (!copmanies) {
                this.companies = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.companies = copmanies;
            }

            searchText = searchText.toLowerCase();
            this.companies = copmanies.filter(it => {
                const range = it.name + ' ' + it.street + ' ' + it.city + ' ' + it.description + ' ' + it.flatNumber + ' ' + it.buildingNumber;
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    delete(structure: StructureListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('comapny.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.companyService.deleteCompany(String(structure.id)).subscribe(resp => {
                        this.filterCompanies(null)
                        this.translate.get('success.company.delete').subscribe(x=>{
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }
}
