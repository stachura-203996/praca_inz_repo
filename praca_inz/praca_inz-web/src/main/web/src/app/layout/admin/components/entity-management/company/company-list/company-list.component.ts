import { Component, OnInit } from '@angular/core';

import {TranslateService} from "@ngx-translate/core";
import {CompanyService} from "../company.service";
import {StructureListElement} from "../../models/structure-list-element";
import {AccountLevel} from "../../../administration/user-management/models/account-level";


@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.scss']
})
export class CompanyListComponent implements OnInit {

    public notVerifiedFilter = false;
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
        this.getCompanies();
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
                    this.companies = companies;
            }

            searchText = searchText.toLowerCase();
            this.companies = companies.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }


    delete(structure: StructureListElement) {
        // const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        // modalRef.componentInstance.user = user;
        // modalRef.result.then(
        //     result => {
        //         // Left blank intentionally, nothing to do here
        //     },
        //     reason => {
        //         // Left blank intentionally, nothing to do here
        //     }
        // );
    }
}
