import { Component, OnInit } from '@angular/core';

import {TranslateService} from "@ngx-translate/core";
import {CompanyService} from "../company.service";
import {StructureListElement} from "../../../../../../models/structure-elements";


@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.scss']
})
export class CompanyListComponent implements OnInit {

    public deletedFilter = false;
    companies: StructureListElement[];

    constructor(private companyService : CompanyService,
                private translate:TranslateService,
    ) {

        // this.translate.addLangs(['en','pl','de']);
        // this.translate.setDefaultLang('en');
        // const browserLang = this.translate.getBrowserLang();// private messageService: MessageService,
        // // private i18nService: I18nServiceerLang();
        // this.translate.use(browserLang.match(/en|pl|de/) ? browserLang : 'pl');
    }

    ngOnInit() {
        // this.filterUsers(null);
        this.filterCompanies(null);
    }

    getCompanies(){
        this.companyService.getAll().subscribe(companyListElement=> {this.companies=companyListElement});
    }

    getAddress(company:StructureListElement): string {
        if (company.flatNumber== null || company.flatNumber === "0") {
            return (company.street + ' ' + company.buildingNumber);
        } else {
            return (company.street + ' ' + company.buildingNumber + ' / ' + company.flatNumber);
        }
    }

    filterCompanies(searchText: string) {
        this.companyService.getAll().subscribe(companies => {
            if (!companies) {
                this.companies = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                if (this.deletedFilter) {
                    this.companies = companies.filter(it => {
                        return it.deleted === !this.deletedFilter;
                    });
                } else {
                    this.companies = companies;
                }
                return;
            }

            searchText = searchText.toLowerCase();
            this.companies = companies.filter(it => {
                const range = it.name+' '+it.companyName+' '+it.departmentName+' '+it.description+' '+it.city+' '+it.street+' '+it.zipCode;
                const ok = range.toLowerCase().includes(searchText);
                if (!this.deletedFilter) {
                    return ok;
                } else {
                    return ok && it.deleted === !this.deletedFilter;
                }
            });
        });
    }

    delete(structure: StructureListElement) {
        this.companyService.deleteCompany(String(structure.id)).subscribe(resp => {
            this.getCompanies()
        });
    }
}
