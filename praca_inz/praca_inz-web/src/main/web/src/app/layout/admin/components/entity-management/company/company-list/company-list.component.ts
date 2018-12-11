import { Component, OnInit } from '@angular/core';
import {AccountLevel} from "../../../../../user-management/models/account-level";
import {TranslateService} from "@ngx-translate/core";
import {CompanyService} from "../company.service";
import {StructureListElement} from "../../models/structure-list-element";
import {UserListComponent} from "../../../../../user-management/components/user-list/user-list.component";

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.scss']
})
export class CompanyListComponent implements OnInit {

    public notVerifiedFilter = false;
    companies: StructureListElement[];
    selectedUserAccessLevels: AccountLevel[];

    constructor(private companyService : CompanyService,
                private translate:TranslateService,

    ) {

        this.translate.addLangs(['en','pl','de']);
        this.translate.setDefaultLang('en');
        const browserLang = this.translate.getBrowserLang();// private messageService: MessageService,
        // private i18nService: I18nServiceerLang();
        this.translate.use(browserLang.match(/en|pl|de/) ? browserLang : 'pl');
    }

    ngOnInit() {
        // this.filterUsers(null);
        this.getUsers();
    }

    getUsers(){
        this.companyService.getAll().subscribe(companyListElement=> {this.companies=companyListElement});
    }

    getAddress(company:StructureListElement): string {
        if (company.flatNumber== null || company.flatNumber === "0") {
            return (company.street + ' ' + company.buildingNumber);
        } else {
            return (company.street + ' ' + company.buildingNumber + ' / ' + company.flatNumber);
        }
    }

    filterUsers(searchText: string) {
        // this.userService.getAll().subscribe(users => {
        //     if (!users) {
        //         this.users = [];
        //         return;
        //     }
        //     if (!searchText || searchText.length < 2) {
        //         if (this.notVerifiedFilter) {
        //             this.users = users.filter(it => {
        //                 return it.verified === !this.notVerifiedFilter;
        //             });
        //         } else {
        //             this.users = users;
        //         }
        //         return;
        //     }
        //
        //     searchText = searchText.toLowerCase();
        //     this.users = users.filter(it => {
        //         const fullname = it.name + ' ' + it.surname;
        //         const ok = fullname.toLowerCase().includes(searchText);
        //         if (!this.notVerifiedFilter) {
        //             return ok;
        //         } else {
        //             return ok && it.verified === !this.notVerifiedFilter;
        //         }
        //     });
        // });
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
