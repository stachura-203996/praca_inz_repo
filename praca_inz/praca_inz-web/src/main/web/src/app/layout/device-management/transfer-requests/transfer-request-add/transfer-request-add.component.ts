import {Component, Input, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {TransferRequestAddElement} from "../../../../models/request-elements";
import {RequestService} from "../../../employee-management/request.service";
import {WarehouseListElement} from "../../../../models/warehouse-elements";
import {WarehouseService} from "../../../warehouse-management/warehouse.service";

@Component({
  selector: 'app-transfer-request-add',
  templateUrl: './transfer-request-add.component.html',
  styleUrls: ['./transfer-request-add.component.scss']
})
export class TransferRequestAddComponent implements OnInit {

    @Input() transferRequestAddElement: TransferRequestAddElement= new TransferRequestAddElement;

    warehouses: WarehouseListElement[];
    selectedOption: string;

    constructor(private route: ActivatedRoute,private warehouseService:WarehouseService,private requestService:RequestService,private translate:TranslateService,private router:Router) {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        this.getWarehouses();
    }

    getWarehouses(){
        this.warehouseService.getAllForTransferRequest().subscribe(companyListElement=> {this.warehouses=companyListElement});
    }

    transferRequestAdd(){
       this.transferRequestAddElement.recieverWarehouseId=this.warehouses.find(x=>x.name==this.selectedOption).id;
        this.transferRequestAddElement.deviceId = this.route.snapshot.paramMap.get('id');
        this.requestService.createTransferRequest(this.transferRequestAddElement).subscribe(resp => {
            this.router.navigateByUrl('/employees/requests');
        });
    }

    clear() {
        this.transferRequestAddElement=new TransferRequestAddElement();
    }

}
