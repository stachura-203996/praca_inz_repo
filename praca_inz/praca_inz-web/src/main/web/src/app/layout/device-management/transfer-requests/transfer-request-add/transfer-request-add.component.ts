import {Component, Input, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {TransferRequestAddElement} from "../../../../models/request-elements";
import {RequestService} from "../../../employee-management/request.service";
import {WarehouseListElement} from "../../../../models/warehouse-elements";
import {WarehouseService} from "../../../warehouse-management/warehouse.service";
import {StructureListElement} from "../../../../models/structure-elements";

@Component({
  selector: 'app-transfer-request-add',
  templateUrl: './transfer-request-add.component.html',
  styleUrls: ['./transfer-request-add.component.scss']
})
export class TransferRequestAddComponent implements OnInit {

    @Input() transferRequestAddElement: TransferRequestAddElement= new TransferRequestAddElement;


    selectedOption: string;

    warehouses = new Map<string, number>();

    constructor(private route: ActivatedRoute,private warehouseService:WarehouseService,private requestService:RequestService,private translate:TranslateService,private router:Router) {

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
        this.transferRequestAddElement.recieverWarehouseId = this.warehouses.get(this.selectedOption);
        this.transferRequestAddElement.deviceId = this.route.snapshot.paramMap.get('id');
        this.requestService.createTransferRequest(this.transferRequestAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.transferRequestAddElement=new TransferRequestAddElement();
    }
}
