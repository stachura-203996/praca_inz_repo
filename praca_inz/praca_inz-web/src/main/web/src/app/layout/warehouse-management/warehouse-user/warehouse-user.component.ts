import {Component, OnInit} from '@angular/core';
import {WarehouseListElement} from "../../../models/warehouse-elements";
import {WarehouseService} from "../warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../models/structure-elements";

@Component({
    selector: 'app-warehouse-list',
    templateUrl: './warehouse-user.component.html',
    styleUrls: ['./warehouse-user.component.scss']
})
export class WarehouseUserComponent implements OnInit {

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
        this.getWarehouses();
    }

    getWarehouses() {
        this.warehouseService.getAllForWarehouseman().subscribe(warehouseListElement => {
            this.warehouses = warehouseListElement
        });
    }


    filterWarehouses(searchText: string) {
        this.warehouseService.getAllForWarehouseman().subscribe(warehouses => {
            if (!warehouses) {
                this.warehouses = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.warehouses = warehouses;
            }

            searchText = searchText.toLowerCase();
            this.warehouses = warehouses.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }

    getUserInfo(warehouse:WarehouseListElement){
        return warehouse.userName+" "+warehouse.userSurname+" | "+warehouse.username;
    }
   
}
