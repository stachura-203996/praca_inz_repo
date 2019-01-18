import {Component, OnInit} from '@angular/core';
import {DeliveryListElement} from "../../../../../../models/warehouse-elements";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {UserRoles} from "../../../../../../models/user-roles";
import {LoggedUser} from "../../../../../../models/logged-user";
import {UserService} from "../../../administration/user-management/user.service";
import {DeviceAddElement} from "../../../../../../models/device-elements";
import {DeviceService} from "../../../../../device-management/device.service";
import {Configuration} from "../../../../../../app.constants";
import {MessageService} from "../../../../../../shared/services/message.service";

@Component({
    selector: 'app-delivery-list',
    templateUrl: './delivery-list.component.html',
    styleUrls: ['./delivery-list.component.scss']
})
export class DeliveryListComponent implements OnInit {

    deliveries: DeliveryListElement[];


    constructor(
        private warehouseService: WarehouseService,
        private deviceService:DeviceService,
        private userService: UserService,
        private configuration: Configuration,
        private messageService: MessageService,
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

    confirm(deliver: DeliveryListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('delivery.confirm').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    var device: DeviceAddElement = new DeviceAddElement();
                    device.warehouseId = deliver.warehouseId;
                    device.serialNumber = deliver.serialNumber;
                    device.deviceModelId = deliver.deviceModelId;
                    device.companyId = deliver.companyId;
                    this.warehouseService.confirmDelivery(deliver.id).subscribe(rep => {
                        this.deviceService.createDevice(device).subscribe(rep => {
                            this.filterDeliveries(null);
                            this.translate.get('success.delivery.confirm').subscribe(x => {
                                this.messageService.success(x)
                            })
                        });
                    });
                }
            });
    }

}
