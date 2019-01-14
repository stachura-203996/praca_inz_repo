import {Component, OnInit} from '@angular/core';
import {StructureListElement} from "../../../../../../models/structure-elements";
import {OfficeService} from "../../office/office.service";
import {TranslateService} from "@ngx-translate/core";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {WarehouseListElement} from "../../../../../../models/warehouse-elements";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
    selector: 'app-warehouse-list',
    templateUrl: './warehouse-list.component.html',
    styleUrls: ['./warehouse-list.component.scss']
})
export class WarehouseListComponent implements OnInit {

    public deletedFilter = false;
    warehouses: WarehouseListElement[];


    constructor(
        private warehouseService: WarehouseService,
                private userService:UserService,
                private translate: TranslateService,
    ) {    }

    ngOnInit() {
        this.getWarehouses();
    }

    getWarehouses() {

            this.warehouseService.getAll().subscribe(warehouseListElement => {
                this.warehouses = warehouseListElement
            });
          }

    filterWarehouses(searchText: string) {
            this.warehouseService.getAll().subscribe(warehouses => {
                if (!warehouses) {
                    this.warehouses = [];
                    return;
                }
                if (!searchText || searchText.length < 2) {
                    if (this.deletedFilter) {
                        this.warehouses = warehouses.filter(it => {
                            return it.deleted === !this.deletedFilter;
                        });
                    } else {
                        this.warehouses = warehouses;
                    }
                    return;
                }

                searchText = searchText.toLowerCase();
                this.warehouses = warehouses.filter(it => {
                    const range = it.name + ' ' + it.devicesNumber + ' ' + it.username + ' ' + it.officeName;
                    const ok = range.toLowerCase().includes(searchText);
                    if (!this.deletedFilter) {
                        return ok;
                    } else {
                        return ok && it.deleted === !this.deletedFilter;
                    }
                });
            });

    }

    delete(warehouse: WarehouseListElement) {
        this.warehouseService.deleteWarehouse(String(warehouse.id)).subscribe(resp => {
            this.getWarehouses()
        });
    }

    getUserInfo(warehouse:WarehouseListElement){
        return warehouse.userName+" "+warehouse.userSurname+" | "+warehouse.username;
    }

}
