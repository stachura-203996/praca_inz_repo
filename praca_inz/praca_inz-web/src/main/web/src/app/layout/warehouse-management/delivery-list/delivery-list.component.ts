import { Component, OnInit } from '@angular/core';
import {TransferListElement} from "../../../models/transfer-list-element";
import {DeviceService} from "../../device-management/device.service";
import {TranslateService} from "@ngx-translate/core";
import {DeliveryListElement} from "../../../models/warehouse-elements";
import {WarehouseService} from "../warehouse.service";
import {DeviceAddElement} from "../../../models/device-elements";

@Component({
  selector: 'app-delivery-list',
  templateUrl: './delivery-list.component.html',
  styleUrls: ['./delivery-list.component.scss']
})
export class DeliveryListComponent implements OnInit {

    deliveries: DeliveryListElement[];

    constructor(
        private warehouseService : WarehouseService,
                private deviceService:DeviceService,
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

    confirm(deliver:DeliveryListElement){
        var device:DeviceAddElement=new DeviceAddElement();
        device.warehouseId=deliver.warehouseId;
        device.serialNumber=deliver.serialNumber;
        device.deviceModelId=deliver.deviceModelId;
        device.companyId=deliver.companyId;
        this.deviceService.createDevice(device).subscribe();
    }
}
