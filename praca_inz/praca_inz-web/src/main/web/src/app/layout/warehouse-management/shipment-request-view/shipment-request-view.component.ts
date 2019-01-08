import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RequestService} from "../../employee-management/request.service";
import {RequestViewElement} from "../../../models/request-elements";

@Component({
    selector: 'app-shipment-request-view',
    templateUrl: './shipment-request-view.component.html',
    styleUrls: ['./shipment-request-view.component.scss']
})
export class ShipmentRequestViewComponent implements OnInit {

    request: RequestViewElement;

    constructor(
        private route: ActivatedRoute,
        private requestService: RequestService,
    ) {
    }

    ngOnInit() {
        this.getShipmentRequest();

    }

    getShipmentRequest() {
        const id = this.route.snapshot.paramMap.get('id');
        this.requestService.getRequestView(id).subscribe(x => this.request = x);
    }

    accept() {

    }

    reject() {

    }


}
