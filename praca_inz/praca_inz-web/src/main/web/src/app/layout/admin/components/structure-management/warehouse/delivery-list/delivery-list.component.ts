import {Component, OnInit} from '@angular/core';
import {DeliveryListElement} from "../../../../../../models/warehouse-elements";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";

@Component({
    selector: 'app-delivery-list',
    templateUrl: './delivery-list.component.html',
    styleUrls: ['./delivery-list.component.scss']
})
export class DeliveryListComponent implements OnInit {

    deliveries: DeliveryListElement[];


    constructor(
        private warehouseService: WarehouseService,
        private userService: UserService,
        private translate: TranslateService) {
    }

    ngOnInit() {

        this.getDeliveries()

    }

    getDeliveries() {
            this.warehouseService.getAllDeliveries().subscribe(deliveryListElement => {
                this.deliveries = deliveryListElement
            });
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

    getUser(deliver:DeliveryListElement){

    }

    addDevice(device:DeliveryListElement){

    }
}
