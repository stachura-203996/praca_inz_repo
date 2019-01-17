import {Component, Input, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {TransferRequestAddElement} from "../../../../models/request-elements";
import {RequestService} from "../../../employee-management/request.service";
import {WarehouseListElement} from "../../../../models/warehouse-elements";
import {WarehouseService} from "../../../warehouse-management/warehouse.service";
import {StructureListElement} from "../../../../models/structure-elements";
import {MessageService} from "../../../../shared/services/message.service";
import {Configuration} from "../../../../app.constants";

@Component({
  selector: 'app-transfer-request-add',
  templateUrl: './transfer-request-add.component.html',
  styleUrls: ['./transfer-request-add.component.scss']
})
export class TransferRequestAddComponent implements OnInit {

    @Input() transferRequestAddElement: TransferRequestAddElement= new TransferRequestAddElement;


    selectedOption: string;

    warehouses = new Map<string, number>();

    constructor(
        private route: ActivatedRoute,
        private warehouseService:WarehouseService,
        private requestService:RequestService,
        private translate:TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
        private router:Router) {

    }

    ngOnInit() {
        this.getWarehouses();
    }

    getWarehouses(){
        var id= this.route.snapshot.paramMap.get('id');
        this.warehouseService.getAllForTransferRequest(Number(id)).subscribe((response: WarehouseListElement[]) => {
            this.warehouses = response.reduce(function (companyMap, company) {
                if (company.id) {
                    companyMap.set(company.name, company.id)
                }
                return companyMap;
            }, this.warehouses);
        });
    }

    transferRequestAdd(){
        var entity: string;
        var message: string;
        var yes: string;
        var no: string;

        this.translate.get('transfer.request.add').subscribe(x => entity = x);
        this.translate.get('confirm.add').subscribe(x => message = x);
        this.translate.get('yes').subscribe(x => yes = x);
        this.translate.get('no').subscribe(x => no = x);


        this.messageService
            .confirm(entity, message, yes, no)
            .subscribe(confirmed => {
                if (confirmed) {
                    this.transferRequestAddElement.recieverWarehouseId = this.warehouses.get(this.selectedOption);
                    this.transferRequestAddElement.deviceId = this.route.snapshot.paramMap.get('id');
                    this.requestService.createTransferRequest(this.transferRequestAddElement).subscribe(resp => {
                        this.router.navigateByUrl('/employees/requests');
                        this.translate.get('success.device.transfer.add').subscribe(x => {
                            this.messageService.success(x)
                        })
                    });
                }
            });
    }

    clear() {
        this.transferRequestAddElement=new TransferRequestAddElement();
    }
}
