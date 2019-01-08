import { Component, OnInit } from '@angular/core';
import {TransferListElement} from "../../../models/transfer-list-element";
import {DeviceService} from "../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {DeliveryListElement} from "../../../models/warehouse-elements";
import {WarehouseService} from "../warehouse.service";

@Component({
  selector: 'app-delivery-list',
  templateUrl: './delivery-list.component.html',
  styleUrls: ['./delivery-list.component.scss']
})
export class DeliveryListComponent implements OnInit {

    deliveries: DeliveryListElement[];

    constructor(private warehouseService : WarehouseService,
                private translate:TranslateService) {
    }

    ngOnInit() {
        this.getDeliveriesForWarehouseman()
    }

    getDeliveriesForWarehouseman(){
        this.warehouseService.getAllDeliveriesForWarehouse().subscribe(deliveryListElement=> {this.deliveries=deliveryListElement});
    }

    filterDeliveries(searchText: string) {
        this.warehouseService.getAllDeliveriesForWarehouse().subscribe(transfers => {
            if (!transfers) {
                this.deliveries = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.deliveries = transfers;
            }

            searchText = searchText.toLowerCase();
            this.deliveries = transfers.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }
}
