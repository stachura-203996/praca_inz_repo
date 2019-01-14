import {Component, OnInit} from '@angular/core';
import {ChangeRequestStatusElement, RequestListElement} from "../../../models/request-elements";
import {TranslateService} from "@ngx-translate/core";
import {RequestService} from "../../employee-management/request.service";
import {Configuration} from "../../../app.constants";
import {Router} from "@angular/router";

@Component({
    selector: 'app-warehouse-request-list',
    templateUrl: './warehouse-request-list.component.html',
    styleUrls: ['./warehouse-request-list.component.scss']
})
export class WarehouseRequestListComponent implements OnInit {

    employeesRequests: RequestListElement[];
    warehousesRequests: RequestListElement[];
    otherWarehousesRequests: RequestListElement[];
    changeRequestStatusElement: ChangeRequestStatusElement = new ChangeRequestStatusElement();

    TRANSFER_REQUEST: string = "TRANSFER_REQUEST";

    constructor(private requestService: RequestService,
                private translate: TranslateService,
                private configuration: Configuration,
                private router: Router
    ) {
    }

    ngOnInit() {
        this.getRequests();
    }

    getRequests() {
        this.requestService.getAllRequestsFromOtherUsers().subscribe(requests => {
            this.employeesRequests = requests
        });
    }


    cancel(request: RequestListElement) {
        this.changeRequestStatusElement.id = request.id;
        this.changeRequestStatusElement.version = request.version;
        this.changeRequestStatusElement.status = this.configuration.REQUEST_STATUS_CANCELED;
        this.requestService.changeRequestStatus(this.changeRequestStatusElement).subscribe(rep => {
            this.getRequests();
        });
    }


    viewPage(request: RequestListElement) {
        this.router.navigateByUrl('/page/devices/request/warehouse/view/' + request.id);
    }

}
