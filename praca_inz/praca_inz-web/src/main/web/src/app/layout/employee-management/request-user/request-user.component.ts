import { Component, OnInit } from '@angular/core';
import {RequestListElement} from "../../../models/request-elements";

import {TranslateService} from "@ngx-translate/core";
import {StructureListElement} from "../../../models/structure-elements";
import {RequestService} from "../request.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-request-user',
  templateUrl: './request-user.component.html',
  styleUrls: ['./request-user.component.scss']
})
export class RequestUserComponent implements OnInit {


    yourRequest: RequestListElement[];
    employeesRequest:RequestListElement[];

    DEVICE_REQUEST: string = "DEVICE_REQUEST";
    TRANSFER_REQUEST: string = "TRANSFER_REQUEST";
    DELIVERY_REQUEST: string = "DELIVERY_REQUEST";
    SHIPMENT_REQUEST: string = "SHIPMENT_REQUEST";

    constructor(private router:Router,private requestService : RequestService,
                private translate:TranslateService,

    ) {}

    ngOnInit() {
        // this.filterUsers(null);
        this.getRequests();
    }

    getRequests(){
        this.requestService.getAllRequestsForLoggedUser().subscribe(requests=> {this.yourRequest=requests});
        this.requestService.getAllRequestsForManager().subscribe(requests=> {this.employeesRequest=requests});
    }


    cancel(request: RequestListElement) {
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

    reject(request: RequestListElement){

    }

    accept(request: RequestListElement){

    }

    viewPage(request:RequestListElement){
        switch(request.type) {
            case this.DEVICE_REQUEST: {
                this.router.navigateByUrl('/page/devices/request/view/'+request.id);
                break;
            }
            case this.DELIVERY_REQUEST: {
                this.router.navigateByUrl('/page/warehouses/delivery/request/view/'+request.id);
                break;
            }
            case this.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/page/devices/request/transfer/view/'+request.id);
                break;
            }
            case this.SHIPMENT_REQUEST: {
                this.router.navigateByUrl('/page/warehouses/shipment/request/view/'+request.id);
                break;
            }
        }
    }

    editPage(request:RequestListElement){
        switch(request.type) {
            case this.DEVICE_REQUEST: {
                this.router.navigateByUrl('/page/devices/request/edit/'+request.id);
                break;
            }
            case this.DELIVERY_REQUEST: {
                this.router.navigateByUrl('/page/warehouses/delivery/request/edit/'+request.id);
                break;
            }
            case this.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/page/devices/transfer/request/edit/'+request.id);
                break;
            }
            case this.SHIPMENT_REQUEST: {
                this.router.navigateByUrl('/page/warehouses/shipment/request/edit/'+request.id);
                break;
            }
        }
    }
}
