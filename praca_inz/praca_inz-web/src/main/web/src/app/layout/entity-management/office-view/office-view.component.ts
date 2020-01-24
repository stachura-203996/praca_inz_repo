import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DepartmentService} from "../../admin/components/structure-management/department/department.service";
import {OfficeService} from "../../admin/components/structure-management/office/office.service";
import {DeviceService} from "../../device-management/device.service";
import {StructureListElement, StructureViewElement} from "../../../models/structure-elements";
import {DeviceListElement} from "../../../models/device-elements";
import {WarehouseListElement} from "../../../models/warehouse-elements";
import {WarehouseService} from "../../warehouse-management/warehouse.service";
import {UserRoles} from "../../../models/user-roles";
import {UserService} from "../../admin/components/administration/user-management/user.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../../../shared/services/message.service";
import {Configuration} from "../../../app.constants";


@Component({
  selector: 'app-office-view',
  templateUrl: './office-view.component.html',
  styleUrls: ['./office-view.component.scss']
})
export class OfficeViewComponent implements OnInit {

    office: StructureViewElement;
    warehouses:WarehouseListElement[];
    devices: DeviceListElement[];
    roles: UserRoles;

    constructor(
        private route: ActivatedRoute,
        private warehouseService: WarehouseService,
        private departmentService: DepartmentService,
        private officeService: OfficeService,
        private userService:UserService,
        private deviceService: DeviceService,
        private translate: TranslateService,
        private messageService: MessageService,
        private configuration: Configuration,
    ) {}

    ngOnInit() {
        this.getOffice();
        this.getDevicesForOffice();
        this.getDevicesForOffice();
        this.getWarehouseForOffice();
        this.getLoggedUserRoles();
    }

    getOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.officeService.getOfficeView(id).subscribe(x =>{ this.office = x});
    }

    getLoggedUserRoles() {
        this.userService.getLoggedUserRoles().subscribe(x => {this.roles = x});
    }

    getDevicesForOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.deviceService.getAllDevicesForDepartment(id).subscribe(deviceListElement => {
            this.devices = deviceListElement
        });
    }

    getWarehouseForOffice() {
        const id = this.route.snapshot.paramMap.get('id');
        this.warehouseService.getAllForOffice(id).subscribe(warehouseListElement => {
            this.warehouses = warehouseListElement
        });
    }

    getAddress(): string {
        if (this.office.flatNumber == null || this.office.flatNumber === "0") {
            return (this.office.street + ' ' + this.office.buildingNumber);
        } else {
            return (this.office.street + ' ' + this.office.buildingNumber + ' / ' + this.office.flatNumber);
        }
    }

}
