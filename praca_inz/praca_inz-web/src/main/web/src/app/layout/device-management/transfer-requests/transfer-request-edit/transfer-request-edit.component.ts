import {Component, Input, OnInit} from '@angular/core';
import {TransferRequestAddElement, TransferRequestEditElement} from "../../../../models/request-elements";
import {WarehouseListElement} from "../../../../models/warehouse-elements";
import {ActivatedRoute, Router} from "@angular/router";
import {WarehouseService} from "../../../warehouse-management/warehouse.service";
import {RequestService} from "../../../employee-management/request.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-transfer-request-edit',
  templateUrl: './transfer-request-edit.component.html',
  styleUrls: ['./transfer-request-edit.component.scss']
})
export class TransferRequestEditComponent implements OnInit {

   transferRequestEditElement: TransferRequestEditElement;

    warehouses: WarehouseListElement[];
    selectedOption: string;

    constructor(private route: ActivatedRoute,private warehouseService:WarehouseService,private requestService:RequestService,private translate:TranslateService,private router:Router) {

    }

    ngOnInit() {
        this.getWarehouses();
        this.getTransferRequest();
    }

    getTransferRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getTransferRequestEdit(id).subscribe(x=>this.transferRequestEditElement=x);
    }

    getWarehouses(){
        this.warehouseService.getAllForTransferRequest().subscribe(companyListElement=> {this.warehouses=companyListElement});
    }

    transferRequestUpdate(){
        this.transferRequestEditElement.recieverWarehouseId=this.warehouses.find(x=>x.name==this.selectedOption).id;
        this.transferRequestEditElement.deviceId = this.route.snapshot.paramMap.get('id');
        this.requestService.updateTransferRequest(this.transferRequestEditElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.transferRequestEditElement=new TransferRequestEditElement();
    }

}
