import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {WarehouseListElement} from "../../../../../../models/warehouse-elements";
import {UserService} from "../../../administration/user-management/user.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";

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
        private userService: UserService,
        private translate: TranslateService,
        private configuration: Configuration,
        private messageService: MessageService
    ) {
    }

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
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('warehouse.delete').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);

        if(warehouse.devicesNumber==0) {

            this.messageService
                .confirm(entity, message, yes, no)
                .subscribe(confirmed => {
                    if (confirmed) {
                        this.warehouseService.deleteWarehouse(String(warehouse.id)).subscribe(resp => {
                            this.getWarehouses()
                            this.translate.get('success.warehouse.delete').subscribe(x => {
                                this.messageService.success(x)
                            })
                        });
                    }
                });
        } else {
            this.translate.get("warehouse.delete.used").subscribe(x=>
            this.messageService.error(x)
            );
        }
    }

    getUserInfo(warehouse: WarehouseListElement) {
        return warehouse.userName + " " + warehouse.userSurname + " | " + warehouse.username;
    }

}
