import {Component, OnInit} from '@angular/core';
import {StructureListElement} from "../../../../../../models/structure-elements";

import {TranslateService} from "@ngx-translate/core";
import {RequestListElement} from "../../../../../../models/request-elements";

import {Router} from "@angular/router";
import {RequestService} from "../../../../../employee-management/request.service";

@Component({
    selector: 'app-request-list',
    templateUrl: './request-list.component.html',
    styleUrls: ['./request-list.component.scss']
})
export class RequestListComponent implements OnInit {

    deviceRequests: RequestListElement[];
    transferRequests: RequestListElement[];
    deliveryRequests: RequestListElement[];
    shipmentRequests: RequestListElement[];

    DEVICE_REQUEST: string = "DEVICE_REQUEST";
    TRANSFER_REQUEST: string = "TRANSFER_REQUEST";
    DELIVERY_REQUEST: string = "DELIVERY_REQUEST";
    SHIPMENT_REQUEST: string = "SHIPMENT_REQUEST";

    constructor(private requestService: RequestService, private translate: TranslateService,private router:Router) {
        this.translate.addLangs(['en','pl']);
        this.translate.setDefaultLang('pl');
        const browserLang = this.translate.getBrowserLang();
        this.translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
    }

    ngOnInit() {
        // this.filterUsers(null);
        this.getRequests();
    }

    getRequests() {
        this.requestService.getAllRequests(this.DEVICE_REQUEST).subscribe(requests => {
            this.deviceRequests = requests
        });
        this.requestService.getAllRequests(this.TRANSFER_REQUEST).subscribe(requests => {
            this.transferRequests = requests
        });
        this.requestService.getAllRequests(this.DELIVERY_REQUEST).subscribe(requests => {
            this.deliveryRequests = requests
        });
        this.requestService.getAllRequests(this.SHIPMENT_REQUEST).subscribe(requests => {
            this.shipmentRequests = requests
        });
    }

    getAddress(office: StructureListElement): string {
        if (office.flatNumber == null || office.flatNumber === "0") {
            return (office.street + ' ' + office.buildingNumber);
        } else {
            return (office.street + ' ' + office.buildingNumber + ' / ' + office.flatNumber);
        }
    }

    filterUsers(searchText: string) {
        // this.userService.getAllNotificationsForUser().subscribe(users => {
        //     if (!users) {
        //         this.users = [];
        //         return;
        //     }
        //     if (!searchText || searchText.length < 2) {
        //         if (this.notVerifiedFilter) {
        //             this.users = users.filter(it => {
        //                 return it.verified === !this.notVerifiedFilter;
        //             });
        //         } else {
        //             this.users = users;
        //         }
        //         return;
        //     }
        //
        //     searchText = searchText.toLowerCase();
        //     this.users = users.filter(it => {
        //         const fullname = it.name + ' ' + it.surname;
        //         const ok = fullname.toLowerCase().includes(searchText);
        //         if (!this.notVerifiedFilter) {
        //             return ok;
        //         } else {
        //             return ok && it.verified === !this.notVerifiedFilter;
        //         }
        //     });
        // });
    }

    viewPage(request:RequestListElement){
        switch(request.type) {
            case this.DEVICE_REQUEST: {
                this.router.navigateByUrl('/devices/request/view/'+request.id);
                break;
            }
            case this.DELIVERY_REQUEST: {
                this.router.navigateByUrl('/warehouses/delivery/request/view/'+request.id);
                break;
            }
            case this.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/devices/transfer/view/'+request.id);
                break;
            }
            case this.SHIPMENT_REQUEST: {
                this.router.navigateByUrl('/warehouses/shipment/request/view/'+request.id);
                break;
            }
        }
    }

    editPage(request:RequestListElement){
        switch(request.type) {
            case this.DEVICE_REQUEST: {
                this.router.navigateByUrl('/devices/request/edit/'+request.id);
                break;
            }
            case this.DELIVERY_REQUEST: {
                this.router.navigateByUrl('/warehouses/delivery/request/edit/'+request.id);
                break;
            }
            case this.TRANSFER_REQUEST: {
                this.router.navigateByUrl('/devices/transfer/edit/'+request.id);
                break;
            }
            case this.SHIPMENT_REQUEST: {
                this.router.navigateByUrl('/warehouses/shipment/request/edit/'+request.id);
                break;
            }
        }
    }

    cancel(structure: RequestListElement) {
        this.requestService.cancelRequest(structure).subscribe(resp => {
            this.getRequests();
        });
    }


    delete(structure: RequestListElement) {
        this.requestService.deleteRequest(String(structure.id)).subscribe(resp => {
            this.getRequests()
        });
    }

}
