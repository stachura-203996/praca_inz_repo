import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {WarehouseService} from "../../../../../warehouse-management/warehouse.service";
import {TranslateService} from "@ngx-translate/core";
import {WarehouseListElement} from "../../../../../../models/warehouse-elements";
import {TransferAddElement} from "../../../../../../models/transfer-add-element";
import {DeviceService} from "../../../../../device-management/device.service";

@Component({
  selector: 'app-device-transfer',
  templateUrl: './device-transfer.component.html',
  styleUrls: ['./device-transfer.component.scss']
})
export class DeviceTransferComponent implements OnInit {

    @Input() transferAddElement: TransferAddElement= new TransferAddElement();


    selectedOption: string;

    warehouses = new Map<string, number>();

    constructor(
        private route: ActivatedRoute,
        private warehouseService:WarehouseService,
        private deviceService:DeviceService,
        private translate:TranslateService,private router:Router
    ) {

    }

    ngOnInit() {
        this.getWarehouses();
    }

    getWarehouses(){
        this.warehouseService.getAllForTransferRequest().subscribe((response: WarehouseListElement[]) => {
            this.warehouses = response.reduce(function (companyMap, company) {
                if (company.id) {
                    companyMap.set(company.name, company.id)
                }
                return companyMap;
            }, this.warehouses);
        });
    }

    transferRequestAdd(){
        this.transferAddElement.recieverWarehouseId = this.warehouses.get(this.selectedOption);
        this.transferAddElement.deviceId = this.route.snapshot.paramMap.get('id');
        this.deviceService.createTransfer(this.transferAddElement).subscribe(resp => {
            this.router.navigateByUrl('/ui/page/admin/transfers');
        });
    }

    clear() {
        this.transferAddElement=new TransferAddElement();
    }
}
