import { Component, OnInit } from '@angular/core';
import {DepartmentService} from "../department.service";
import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../models/structure-list-element";

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.scss']
})
export class DepartmentListComponent implements OnInit {

   departments: StructureListElement[];

    constructor(private departmentService : DepartmentService,
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
        this.departmentService.getAll().subscribe(departmentListElement=> {this.departments=departmentListElement});
    }

    getAddress(department:StructureListElement): string {
        if (department.flatNumber== null || department.flatNumber === "0") {
            return (department.street + ' ' + department.buildingNumber);
        } else {
            return (department.street + ' ' + department.buildingNumber + ' / ' + department.flatNumber);
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
