import {Component, OnInit} from '@angular/core';
import {ExternalTransferListElement} from "../../../../../../models/warehouse-elements";
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
    selector: 'app-externalTransfery-list',
    templateUrl: './external-transfer-list.component.html',
    styleUrls: ['./external-transfer-list.component.scss']
})
export class ExternalTransferListComponent implements OnInit {

    externalTransfers: ExternalTransferListElement[];


    constructor(
        private warehouseService: WarehouseService,
        private deviceService:DeviceService,
        private userService: UserService,
        private configuration: Configuration,
        private messageService: MessageService,
        private translate: TranslateService) {
    }

    ngOnInit() {
        this.getExternalTransfers()
    }

    getExternalTransfers() {
            this.warehouseService.getAllDeliveries().subscribe(externalTransferListElement => {
                this.externalTransfers = externalTransferListElement
            });
    }

    filterDeliveries(searchText: string) {
            this.warehouseService.getAllDeliveriesForWarehouse().subscribe(transfers => {
                if (!transfers) {
                    this.externalTransfers = [];
                    return;
                }
                if (!searchText || searchText.length < 2) {
                    this.externalTransfers = transfers;
                }

                searchText = searchText.toLowerCase();
                this.externalTransfers = transfers.filter(it => {
                    const range = it.toString();
                    const ok = range.toLowerCase().includes(searchText);
                    return ok;
                });
            });
    }

    confirm(externalTransfer: ExternalTransferListElement) {
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('externalTransfery.confirm').subscribe(x => entity = x);
        this.translate.get('confirm.delete').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    var device: DeviceAddElement = new DeviceAddElement();
                    device.warehouseId = externalTransfer.warehouseId;
                    device.serialNumber = externalTransfer.serialNumber;
                    device.deviceModelId = externalTransfer.deviceModelId;
                    device.companyId = externalTransfer.companyId;
                    this.warehouseService.confirmDelivery(externalTransfer.id).subscribe(rep => {
                        this.deviceService.createDevice(device).subscribe(rep => {
                            this.filterDeliveries(null);
                            this.translate.get('success.externalTransfery.confirm').subscribe(x => {
                                this.messageService.success(x)
                            })
                        });
                    });
                }
            });
    }

}
