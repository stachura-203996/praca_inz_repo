import {Component, OnInit} from '@angular/core';
import {StructureListElement} from "../../../../../../models/structure-elements";
import {OfficeService} from "../../office/office.service";
import {TranslateService} from "@ngx-translate/core";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {WarehouseListElement} from "../../../../../../models/warehouse-elements";

@Component({
    selector: 'app-warehouse-list',
    templateUrl: './warehouse-list.component.html',
    styleUrls: ['./warehouse-list.component.scss']
})
export class WarehouseListComponent implements OnInit {

    warehouses: WarehouseListElement[];

    constructor(private warehouseService: WarehouseService,
                private translate: TranslateService,
    ) {
        this.translate.addLangs(['en', 'pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        // this.filterUsers(null);
        this.getUsers();
    }

    getUsers() {
        this.warehouseService.getAll().subscribe(warehouseListElement => {
            this.warehouses = warehouseListElement
        });
    }


    filterWarehouses(searchText: string) {
        // this.userService.getAllNotificationsForUser().subscribe(users => {
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
