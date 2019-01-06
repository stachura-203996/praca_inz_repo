import { Component, OnInit } from '@angular/core';
import {RequestListElement} from "../../../models/request-elements";

import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../models/structure-elements";
import {RequestService} from "../../employee-management/request.service";

@Component({
  selector: 'app-warehouse-request-list',
  templateUrl: './warehouse-request-list.component.html',
  styleUrls: ['./warehouse-request-list.component.scss']
})
export class WarehouseRequestListComponent implements OnInit {

    employeesRequests: RequestListElement[];
    warehousesRequests:RequestListElement[];
    otherWarehousesRequests:RequestListElement[];

    TRANSFER_REQUEST:string="TRANSFER_REQUEST";

    constructor(private requestService : RequestService,
                private translate:TranslateService,

    ) {}

    ngOnInit() {
        // this.filterUsers(null);
        this.getRequests();
    }

    getRequests(){
        this.requestService.getAllRequestsForWarehouseman().subscribe(requests=> {this.warehousesRequests=requests});
        this.requestService.getAllRequestsFromOtherWarehouses().subscribe(requests=> {this.otherWarehousesRequests=requests});
        this.requestService.getAllRequestsFromOtherUsers().subscribe(requests=> {this.employeesRequests=requests});
    }





    cancel(structure: StructureListElement) {
        // const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        // modalRef.componentInstance.user = user;
        // modalRef.result.then(
        //     result => {
        //         // Left blank intentionally, nothing to do here
        //     },
        //     reason => {
        //         // Left blank intentionally, nothing to do here
        //     }
        // );
    }

    delete(structure: StructureListElement) {
        // const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        // modalRef.componentInstance.user = user;
        // modalRef.result.then(
        //     result => {
        //         // Left blank intentionally, nothing to do here
        //     },
        //     reason => {
        //         // Left blank intentionally, nothing to do here
        //     }
        // );
    }

}
