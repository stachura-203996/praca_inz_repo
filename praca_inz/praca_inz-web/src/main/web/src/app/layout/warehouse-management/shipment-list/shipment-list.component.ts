import { Component, OnInit } from '@angular/core';
import {DeliveryListElement, ShipmentListElement} from "../../../models/warehouse-elements";
import {WarehouseService} from "../warehouse.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-shipment-list',
  templateUrl: './shipment-list.component.html',
  styleUrls: ['./shipment-list.component.scss']
})
export class ShipmentListComponent implements OnInit {

    shipments: ShipmentListElement[];

    constructor(private warehouseService : WarehouseService,
                private translate:TranslateService) {
    }

    ngOnInit() {
        this.getShipmentsForWarehouseman()
    }

    getShipmentsForWarehouseman(){
        this.warehouseService.getAllShipmentsForWarehouse().subscribe(deliveryListElement=> {this.shipments=deliveryListElement});
    }

    filterShipments(searchText: string) {
        this.warehouseService.getAllShipmentsForWarehouse().subscribe(shipments => {
            if (!shipments) {
                this.shipments = [];
                return;
            }
            if (!searchText || searchText.length < 2) {
                this.shipments = shipments;
            }

            searchText = searchText.toLowerCase();
            this.shipments = shipments.filter(it => {
                const range = it.toString();
                const ok = range.toLowerCase().includes(searchText);
                return ok;
            });
        });
    }
}
